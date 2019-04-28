## HashMap源码解析

[查看代码解析](https://mp.weixin.qq.com/s/vRvMvNktoDSQKMMlnj5T0g)

### 知识点

- HashMap的默认容量是16，当链表容量达到8时**先判断HashMap的容量是否扩容到64，如果未扩容到64则先扩容，
如果扩容到64则将链表转化为红黑树，进行resize操作时，若桶中数量少于6则从树转成链表
- key的求哈希方法：计算key的hash值并让哈希结果的高位与地位异或，提高随机性
- 在指定初始化大小为N时，会取大于N的2的整数幂次方的最小值，例如指定大小时10，则实际初始化的大小是16
- 扩容总共分为两大类：首次扩容和再次扩容，首次扩容又分为两部分：未指定大小扩容和指定大小扩容，
当未指定大小时，将容量扩容为16，阈值为16*0.75=12;当指定扩容时，新容量等于初始化时指定的阈值大小。
再次扩容容量和阈值都增加一倍，但当容量已经达到最大值时，阈值也会增加大最大值
- 链表重新分配：将老的链表一分为二，一个放到原来位置，
另一个放到原来位置加上原来阈值的新位置。
分配方法：计算其key的hash并与旧的容量进行按位与，结果为0老位置，否则新位置。
- 负载因子
    - 设置较小，扩容速度快，链表长度变短，增删改操作变快，空间换时间
    - 设置较大，容量变大，膨胀率变高，链表边吃，查找速度变慢，时间换空间
- new HashMap();完成后，如果没有put操作，是不会分配存储空间的，而是在新增元素时通过扩容的方式初始化大小。
- 在添加元素时，如果key存在，且value为空时会更新值
- 使用红黑树，查询效率比普通树快，修改新增效率比平衡二叉树快

### 代码块

HashCode方法为拿到key的hash值后与其无符号右移16位取与。

通过移位和异或运算，可以让 hash 变得更复杂，进而影响 hash 的分布性。

```
// 获取hash值
static final int hash(Object key) {
    int h;
    // 拿到key的hash值后与其五符号右移16位取与
    // 通过这种方式，让高位数据与低位数据进行异或，以此加大低位信息的随机性，变相的让高位数据参与到计算中。
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

每一个节点都是由key,value,hashCode值和nextNode组成。

```
transient Node<K,V>[] table;
static class Node<K,V> implements java.util.Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;
}
```

对于指定HashMap的大小，则将N减一，并与N按位与，得到大于等于N的二进制数即：
取大于N的且是2的整数幂次方的最小的数
```
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```

新增元素

1. 当桶数组 table 为空时，**通过扩容的方式初始化 table（new HashMap();完成后，如果没有put操作，是不会分配存储空间的）**

2. 查找要插入的键值对是否已经存在，存在的话根据条件判断是否用新值替换旧值

3. 如果不存在，则将键值对链入链表中，并根据链表长度决定是否将链表转为红黑树

4. 判断键值对数量是否大于阈值，大于的话则进行扩容操作


扩容

resize()整体步骤：

1. 计算新桶数组的容量 newCap 和新阈值 newThr

2. 根据计算出的 newCap 创建新的桶数组，桶数组 table 也是在这里进行初始化的

3. 将键值对节点重新映射到新的桶数组里。如果节点是 TreeNode 类型，则需要拆分红黑树。如果是普通节点，则节点按原顺序进行分组。

总结起来，一共有三种扩容方式：

1. **使用默认构造方法初始化HashMap。因此第一次扩容的容量为默认值DEFAULT_INITIAL_CAPACITY也就是16。
同时threshold = DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR = 12（HashMap在一开始初始化的时候会返回一个空的table，并且thershold为0）**。

2. 指定初始容量的构造方法初始化HashMap。那么从下面源码可以看到**初始容量会等于threshold**，接着threshold = 当前的容量（threshold） * DEFAULT_LOAD_FACTOR。

3. HashMap不是第一次扩容。如果HashMap已经扩容过的话，那么**每次table的容量以及threshold量为原有的两倍**。

4. **如果HashTable的容量已经达到最大值，则不进行扩容，且将阈值也设置成最大值**

5. 链表重新分配：遍历连的每一个元素，计算其key的hash并与旧的容量进行按位与，
如果结果为0则分配到老的桶位置，如果不为0则分配到老的节点加老的容量位置。

```
if ((e.hash & oldCap) == 0) {
   newTab[j] = loHead;
else{
   newTab[j + oldCap] = hiHead;
}
```


细心点的人会很好奇，为什么要判断loadFactor为0呢？

loadFactor小数位为 0，整数位可被2整除且大于等于8时，在某次计算中就可能会导致 newThr 溢出归零。

loadFactor 负载因子
对于 HashMap 来说，负载因子是一个很重要的参数，该参数反应了 HashMap 桶数组的使用情况。
通过调节负载因子，可使 HashMap 时间和空间复杂度上有不同的表现。

当我们调低负载因子时，HashMap 所能容纳的键值对数量变少。
扩容时，重新将键值对存储新的桶数组里，键的键之间产生的碰撞会下降，链表长度变短。
此时，HashMap 的增删改查等操作的效率将会变高，这里是典型的拿空间换时间。

相反，如果增加负载因子（负载因子可以大于1），HashMap 所能容纳的键值对数量变多，空间利用率高，但碰撞率也高。
这意味着链表长度变长，效率也随之降低，这种情况是拿时间换空间。至于负载因子怎么调节，这个看使用场景了。

1. JDK1.7是基于数组+单链表实现（为什么不用双链表）
首先，用链表是为了解决hash冲突。

单链表能实现为什么要用双链表呢?(双链表需要更大的存储空间)

2. 为什么要用红黑树，而不用平衡二叉树？
插入效率比平衡二叉树高，查询效率比普通二叉树高。所以选择性能相对折中的红黑树。

3. 重写对象的Equals方法时，要重写hashCode方法，为什么？跟HashMap有什么关系？
equals与hashcode间的关系:

如果两个对象相同（即用equals比较返回true），那么它们的hashCode值一定要相同；

如果两个对象的hashCode相同，它们并不一定相同(即用equals比较返回false)

因为在 HashMap 的链表结构中遍历判断的时候，特定情况下重写的 equals 方法比较对象是否相等的业务逻辑比较复杂，循环下来更是影响查找效率。
所以这里把 hashcode 的判断放在前面，只要 hashcode 不相等就玩儿完，不用再去调用复杂的 equals 了。很多程度地提升 HashMap 的使用效率。

所以重写 hashcode 方法是为了让我们能够正常使用 HashMap 等集合类，因为 HashMap 判断对象是否相等既要比较 hashcode 又要使用 equals 比较。而这样的实现是为了提高 HashMap 的效率。
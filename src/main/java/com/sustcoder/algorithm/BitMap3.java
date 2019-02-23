package com.sustcoder.algorithm;

public class BitMap3 {

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        // System.out.println("totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));
        BitMap bitMap2=new BitMap(Integer.MAX_VALUE); // 占用内存256M,可最多存放：2的31次方减一个数字，
        // for (int i = 0; i <Integer.MAX_VALUE ; i++) {
        //     bitMap2.setZero(i);
        //     if(bitMap2.getN(i)==1){
        //         System.out.println(i+"is 1");
        //     }
        // }


        // System.out.println(Integer.MAX_VALUE);
        // System.out.println("totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));

        //   int[] BIT_VALUE_ONE = {0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
        //         0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
        //         0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
        //         0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000};
        //
        // int[] BIT_VALUE_ZERO = {0xFFFFFFFE,0xFFFFFFFD,0xFFFFFFFB,0xFFFFFFF7,0xFFFFFFEF,0xFFFFFFDF,0xFFFFFFBF,
        //         0xFFFFFF7F,0xFFFFFEFF,0xFFFFFDFF,0xFFFFFBFF,0xFFFFF7FF,0xFFFFEFFF,0xFFFFDFFF,0xFFFFBFFF,
        //         0xFFFF7FFF,0xFFFEFFFF,0xFFFDFFFF,0xFFFBFFFF,0xFFF7FFFF,0xFFEFFFFF,0xFFDFFFFF,0xFFBFFFFF,
        //         0xFF7FFFFF,0xFEFFFFFF,0xFDFFFFFF,0xFBFFFFFF,0xF7FFFFFF,0xEFFFFFFF,0xDFFFFFFF,0xBFFFFFFF,0x7FFFFFFF};
        // for (int i = 0; i <BIT_VALUE_ONE.length ; i++) {
        //     System.out.println(BIT_VALUE_ONE[i]&BIT_VALUE_ZERO[i]);
        // }
        // System.out.println(b2h("100"));
        // System.out.println(b2h("111"));
        //
        // String strF="{";
        // for (int i = 0; i <BIT_VALUE_ONE.length ; i++) {
        //     String f=b2h(Integer.toBinaryString(~BIT_VALUE_ONE[i]));
        //     strF+=f+",";
        // }
        // System.out.println(strF);
         





        // BitSet bitSet=new BitSet(100);
        // bitSet.set(1);
        // bitSet.get(1);

        // Runtime rt = Runtime.getRuntime();
        // System.out.println("当前 Java 虚拟机中占用内存量：" + (rt.totalMemory() - rt.freeMemory())/1000/1000 + "M");
        // toLH(7);
        // byte[] b=new byte[7];
        // System.out.println(5>>3);
        // System.out.println(-5>>3);
        // System.out.println(5>>>3);
        // System.out.println(-5>>>3);
        // System.out.println(1<<31);
        // System.out.println((1<<31)-1);
        // // 若最高的几位为0则不输出这几位，从为1的那一位开始输出
        // System.out.println(Integer.toBinaryString(10));
        // System.out.println(Integer.toBinaryString(-10));
        // int[] a = new int[100000000];
        // a[10000000]=1;
        // boolean[] c = new boolean[100000000];
        //
        // //创建一个具有10000000位的bitset　初始所有位的值为false
        // java.util.BitSet bitSet = new java.util.BitSet(2147483647    );
        // //将指定位的值设为true
        // bitSet.set(9999, true);
        // //输出指定位的值
        // System.out.println(bitSet.get(9999));
        // System.out.println(bitSet.get(9998));
        //




        // BitMap3 bitMap = new BitMap3(923337203685447780L );
        // BitMap3 bitMap = new BitMap3(123 );
        // bitMap.setBit(3);
        // System.out.println(bitMap.getBit(3));


        // for (int i = 0; i <66 ; i++) {
        //     System.out.println("i="+i+","+(i&31)+","+(i>>5));
        // }




        // System.out.println(Integer.toBinaryString(0x00000001));
        // System.out.println(Integer.toBinaryString(0x00000002));
        // // 如果你写一个0x80；那么会把这0，1位串在内存中铺开，并且是32位的一个int，不够32位，则前面补0。
        // System.out.println(Integer.toBinaryString(0x20));
        // System.out.println(Integer.toString(0x20));
        // System.out.println(Integer.toBinaryString(0x30));
        // System.out.println(Integer.toString(0x30));
        // System.out.println(Integer.toBinaryString(0x40));
        // System.out.println(Integer.toString(0x40));

        // int a = 0x2f;//小写十六进制（等价于0x002f）
        // System.out.println(Integer.toBinaryString(a));
        // System.out.println(Integer.toBinaryString(0x00000000));
        // System.out.println(Integer.toBinaryString(0x2));
        // System.out.println(Integer.toBinaryString(0x02));
        // System.out.println(Integer.toBinaryString(0x02));
        //
        // int b = 0x2F;//大写十六进制
        // System.out.println(Integer.toBinaryString(b));
        // int f=0x2;
        // System.out.println(Integer.toBinaryString(f));
        //
        // int c = 10;//标准十进制
        // System.out.println(Integer.toBinaryString(c));
        //
        // int d = 010;//以零开头，表示八进制
        // System.out.println(Integer.toBinaryString(d));

        //   String[] BIT_VALUE = { "00000001", "00000002", "00000004", "00000008", "00000010", "00000020",
        //         "00000040", "00000080", "00000100", "00000200", "00000400", "00000800", "00001000", "00002000", "00004000",
        //         "00008000", "00010000", "00020000", "00040000", "00080000", "00100000", "00200000", "00400000", "00800000",
        //         "01000000", "02000000", "04000000", "08000000", "10000000", "20000000", "40000000" };
        // // System.out.println(Integer.valueOf("40000000",16).toString());
        //
        // for (int i = 0; i <BIT_VALUE.length ; i++) {
        //     System.out.println(Integer.valueOf(BIT_VALUE[i],16).toString());
        // }
        // int temp=0;
        // for (int i = 1; i < 32; i++) {
        //     System.out.println(Math.pow(new Double(2),new Double(i)));
        // }

    }
    static String[] hexStr = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F" };

    public static String b2h(String binary) {
        // 这里还可以做些判断，比如传进来的数字是否都是0和1
        // System.out.println(binary);
        int length = binary.length();
        int temp = length % 4;
        // 每四位2进制数字对应一位16进制数字
        // 补足4位
        if (temp != 0) {
            for (int i = 0; i < 4 - temp; i++) {
                binary = "0" + binary;
            }
        }
        // 重新计算长度
        length = binary.length();
        StringBuilder sb = new StringBuilder("0x");
        // 每4个二进制数为一组进行计算
        for (int i = 0; i < length / 4; i++) {
            int num = 0;
            // 将4个二进制数转成整数
            for (int j = i * 4; j < i * 4 + 4; j++) {
                num <<= 1;// 左移
                num |= (binary.charAt(j) - '0');// 或运算
            }
            // 直接找到该整数对应的16进制，这里不用switch来做
            sb.append(hexStr[num]);
            // 这里如果要用switch case来做，大概是这个样子
            // switch(num){
            // case 0:
            // sb.append('0');
            // break;
            // case 1:
            // ...
            // case 15:
            // sb.append('F');
            // break;
            // }
        }
        return sb.toString();
    }

    /**
     * int中32位只能表示一个数，而用BitMap可以表示32一个数:
     * int[] bitMap:
     * bitMap[0]:可以表示数字0~31 比如表示0：00000000 00000000 00000000 00000000
     * 比如表示1 : 00000000 00000000 00000000 00000001
     * bitMap[1]:可以表示数字32~63
     * bitMap[2]:可以表示数字64~95
     * ……
     * 因此要将一个数插入到bitMap中要进过三个步骤：
     * 1)找到所在bitMap中的index也就是bitMap数组下标
     * 比如我们要在第64一个位置上插入一个数据（也就是插入63）
     * index = 63 >> 5 = 1，也就是说63应该插入到bitMap[1]中
     * 2)找到63在bitMap[1]中的偏移位置
     * offset = 63 & 31 = 31说明63在bitMap[1]中32位的最高位
     * 3)将最高位设为1
     * ---------------------
     * 作者：黑暗的笑
     * 来源：CSDN
     * 原文：https://blog.csdn.net/xia744510124/article/details/51509285
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    /** 插入数的最大长度，比如100，那么允许插入bitsMap中的最大数为99 */
    private long length;
    private static int[] bitsMap;
    private static final int[] BIT_VALUE = { 0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
            0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
            0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000 };
    public BitMap3(long length) {
        this.length = length;
        // 根据长度算出，所需数组大小
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }
    /**
     * 根据长度获取数据 比如输入63，那么实际上是确定数62是否在bitsMap中
     *
     * @return index 数的长度
     * @return 1:代表数在其中 0:代表
     */
    public int getBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        int intData = (int) bitsMap[(int) ((index - 1) >> 5)];  // 32-1=31=0111111>>5 则 index=0
        return ((intData & BIT_VALUE[(int) ((index - 1) & 31)])) >>> ((index - 1) & 31);
    }
    /**
     * @param index
     *            要被设置的值为index - 1
     */
    public void setBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        // 求出该index - 1所在bitMap的下标
        int belowIndex = (int) ((index - 1) >> 5);
        System.out.println("index="+index+",  belowIndex ="+belowIndex);
        // 求出该值的偏移量(求余)
        int offset = (int) ((index - 1) & 31);
        System.out.println("offset="+offset);
        int inData = bitsMap[belowIndex];
        System.out.println("inData="+inData);
        System.out.println("inData | BIT_VALUE[offset]="+Integer.toBinaryString((inData | BIT_VALUE[offset])));
        bitsMap[belowIndex] = inData | BIT_VALUE[offset];
        System.out.println("bitsMap[belowIndex] ="+bitsMap[belowIndex] );
    }



}
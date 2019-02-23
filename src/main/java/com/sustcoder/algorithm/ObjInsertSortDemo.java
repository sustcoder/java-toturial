package com.sustcoder.algorithm;


/**
 * <p>Description: 插入排序
 *  1、从第一个元素开始，该元素可以认为已经被排序。
 *  2、取出下一个元素，在已经排序的元素序列中从后向前扫描
 *  3、如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置，将新元素插入到该位置中
 * 5、复步骤2
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 18:45 2018/3/19
 */
public class ObjInsertSortDemo {
    public static  <T extends Comparable<? super T>> void insertSort(T[] ObjArray){
        for (int i = 1; i <ObjArray.length ; i++) {
            T tmp=ObjArray[i];
            int j;
            // 取已经排好序的每一个元素和当前元素进行比较
            // 如果此元素大于当前元素，则将此元素后移一位
            for (j=i ; j>0  && tmp.compareTo(ObjArray[j-1]) <0 ; j-- ) {
                ObjArray[j]=ObjArray[j-1];
            }
            // 最终将tmp元素插入到移植后空的位置
            ObjArray[j]=tmp;
        }
    }
}

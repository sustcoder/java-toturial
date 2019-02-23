package com.sustcoder.algorithm;

/**
 * <p>Description:简单选择排序
 * 思路： 每一趟从待排序的数据元素中选择最小（或最大）的一个元素作为首元素
 * 设置一个变量min，每一次比较仅存储较小元素的数组下标，当轮循环结束之后，
 * 那这个变量存储的就是当前最小元素的下标，此时再执行交换操作即可。
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 17:36 2018/3/19
 */
public class SelectSortDemo {
    public static void  selectSort(int[] numbers){
        for (int i = 0; i < numbers.length; i++) {
            int min=i; // 记录最新元素下标，默认是当前元素
            for (int j = i; j <numbers.length ; j++) {
                if(numbers[min]>numbers[j]){
                    min=j;// 筛选出最小元素下标
                }
            }
            // 交换当前元素和最小元素的值
            int tmp=numbers[i];
            numbers[i]=numbers[min];
            numbers[min]=tmp;
        }
    }
}

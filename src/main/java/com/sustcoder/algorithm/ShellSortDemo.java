package com.sustcoder.algorithm;

/**
 * <p>Description: 希尔排序
 * 希尔排序算法是直接插入排序算法的一种改进，减少了其复制的次数，速度要快很多
 * 思路人：希尔排序会先将数据分组，每一组进行插入排序。
 * 分组方式：设置间隔(步长)是len/2 ,len/4,len/8...1
 * <p>
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 19:33 2018/3/19
 */
public class ShellSortDemo {
    public static void shellSort(int[] numbers) {
        int len = numbers.length;
        for (int d = len / 2; d > 0; d /= 2) {
            for (int i = 0; i < len; i += d) {
                int tmp = numbers[i];
                int j = i;
                for (; j > 0 && tmp < numbers[j - d]; j -= d) {
                    numbers[j] = numbers[j - d];
                }
                numbers[j] = tmp;
            }
        }
    }
}

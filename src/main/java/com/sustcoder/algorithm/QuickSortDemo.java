package com.sustcoder.algorithm;

/**
 * <p>Description: 快速排序
 *  先找到一个中间数将数组切割为两部分，左部分比该数小，右部分比该数大
 *  然后对两个部分再进行递归。
 *  思路： 指定一个中轴数，在中轴的左边找大于此数的数，在中轴的右边找小于次数的数，交换两数的位置
 *      中轴数作为一个临时变量。
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 15:06 2018/3/19
 */
public class QuickSortDemo {
    public static void quickSort(int[] numbsers,int low,int high){
        if(low<high) {
            // 用数组的low位置的元素作为中间元素
            int i = low, j = high, x = numbsers[low];

            while ( i < j ){

                // 1、从右到左找小于x的元素，并将其和x元素交换位置
                // 1.1、找出此元素右边比他小的元素的下标
                while (i < j && numbsers[j] >= x) {
                    j--;
                }
                // 1.2、将此元素放到低位（x元素所在位置）
                if (i < j) {
                    numbsers[i++] = numbsers[j];
                }

                // 2、从左到右找大于x的元素，将其和x元素交换位置
                // 2.1、找此元素左边的比他大的元素的下标
                while (i < j && numbsers[i] < x) {
                    i++;
                }

                // 2.2、将此元素放到高位( j元素所在位置 )
                if (i < j) {
                    numbsers[j--] = numbsers[i];
                }
            }

            numbsers[i] = x;// //中轴记录到尾, 此时i为中轴的位置
            // 进行递归调用
            quickSort(numbsers, low, i - 1);
            quickSort(numbsers, i + 1, high);
        }
    }
}

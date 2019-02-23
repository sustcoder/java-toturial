package com.sustcoder.algorithm;

/**
 * <p>Description: 二分法
 * 使用场景： 当需要确定在一个数组中某个元素的位置或者此元素是否存在。
 * 二分法前提：在一个升序或者降序排列的数组中
 * 二分法思想：在一个按照升序排列的数组或集合中，首先判断数组的最中间的元素大于还是小于要查找的值，
 *             如果中间的元素大于查找的值，说明想要查找的值在这个数组的前半部分，
 *             反之想要查找的值在这个数组的后半部分， 然后继续取出这个前半部分数组的中间元素 与要查找的值作比较，如此反复下去，直到找到为止。
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 10:14 2018/3/19
 */
public class BinarySearchDemo {
    public static int binarySearch(int[] array,int low,int high,int target){
        while (low <= high){
            int mid=(low+high)/2;
            if(target < array[mid]){
                high=mid-1;
            }else if(target>array[mid]){
                low=mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}

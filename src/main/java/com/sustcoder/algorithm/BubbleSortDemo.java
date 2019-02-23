package com.sustcoder.algorithm;

/**
 * <p>Description: 冒泡排序法
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 12:30 2018/3/19
 */
public class BubbleSortDemo {
    public static void bubbleSort(int[] numbers){
        int bubbleTimes=numbers.length-1;// 两两比较时，比较的总次数是数组长度减一
        for (int i = 0; i < bubbleTimes ; i++) {
            // 最大的已在之前冒出，所以内循环只要遍历length - 1 - i
            // 在一轮比较完成后，最大或者最小的数会冒泡到最顶部，新的数就不需要和此数进行比较
            int compareTimes= bubbleTimes-i;
            for (int j = 0; j < compareTimes ; j++) {
                if(numbers[j]>numbers[j+1]){
                    int temp=numbers[j+1];
                    numbers[j+1]=numbers[j];
                    numbers[j]=temp;
                }
            }
        }
    }
}

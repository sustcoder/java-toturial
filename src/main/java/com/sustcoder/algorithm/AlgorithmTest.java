package com.sustcoder.algorithm;

import static com.sustcoder.algorithm.RadixSort.radixSort;


/**
 * <p>Description: 测试类
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 17:42 2018/3/19
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        int[] numbers={34568,55,848,1358,8};
        // bubbleSort(numbers);
        // selectSort(numbers);
        // insertSort(numbers);
        // shellSort(numbers);
        radixSort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}

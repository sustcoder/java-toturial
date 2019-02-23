package com.sustcoder.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 基数排序法
 *    思路： 对数字的每一位按照从小到达大进行排序，并将结构放入到一个二维数组中，
 *    最终将二维数组展开变成一维即可
 *    步骤：
 *      1、确定排序的次数times（确定最大数字，然后再计算出最大数字的位数）
 *      2、建立10个有序队列，将每一位数字比对的结果按号入座
 *      3、进行times此分配与收集
 *          3.1、分配数组元素：遍历数组，将其放到对应的队列中
 *          3.2、将队列展开成数组。
 *
 * <p>Version:v1.0
 * <p>Author:liyanzhao
 * <p>Date: 20:21 2018/3/19
 */
public class RadixSort {
    public static void radixSort(int[] numbers){

        // 1、找出最大数
        int max=numbers[0];
        for (int i = 1; i <numbers.length ; i++) {
            max=Math.max(max,numbers[i]);
        }
        // 2、找出最大数有几位
        int time=0;
        while ( max > 0){
            max /=10 ;
            time++;
        }
        // 3、建立10个二维队列
        List<ArrayList> queue=new ArrayList<ArrayList>(10);
        for (int i = 0; i <10 ; i++) {
            queue.add(new ArrayList<Integer>());
        }
        // 4、进行time此分割与收集
        for (int i = 0; i <time ; i++) {
            // 4.1、分配数组元素
            for (int j = 0; j < numbers.length; j++) {
                // 得到数字time+1位上的数字，例如23，time=0时，对应的数字是3；
                int x= numbers[j]%(int)Math.pow(10,i+1)/(int)Math.pow(10,i);
                ArrayList listX=queue.get(x);
                listX.add(numbers[j]);
                queue.set(x,listX);
            }
            // 4.2 收集队列元素
            int count=0;
            for (int j = 0; j < 10 ; j++) {
                while (queue.get(j).size()>0){
                    ArrayList<Integer> list=queue.get(j);
                    numbers[count++] =list.get(0);
                    list.remove(0);// 使用完毕后将数组清空
                }
            }
        }

    }
}

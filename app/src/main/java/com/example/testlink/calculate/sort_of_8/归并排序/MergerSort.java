package com.example.testlink.calculate.sort_of_8.归并排序;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/8/11 10:27
 * Use by
 * <p>
 * 思路步骤：
 * 1.分成若干子序列。
 * 2.合并(排序)子序列
 * <p>
 * 总结：通过递归将排序序列分成若干字序列，分别对子序列排序，最后合并成一个有序序列。
 * 要点：子序列边界问题，特别是在合并2个子序列时，不处理好边界容易引发数组越界。
 */
public class MergerSort {

    private static void print(int[] split) {
        print("分割线", split);
    }

    private static void print(String tag, int[] split) {
        for (int item : split) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("--" + tag + "-------------");
    }

    /**
     * [45, 5, 89, 7, 4, 12, 3, 15, 23, 56]
     *
     * [45,5], [[89],[7,4]]   [12,3], [[15],[23,56]]
     *
     * [45,5,89,7,4]  [12,3,15,23,56]
     */

    public static void main(String[] args) {
        int[] a = new int[]{45, 5, 89, 7, 4, 12, 3, 15, 23,56};
        int[] split = split(a);
        print(split);
    }

    /**
     * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，
     * 再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     */
    public static int[] split(int[] nums) {
        //递归终止条件
        if (nums.length < 2) return nums;

        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid); // 复制范围: 包前不包后。
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        //递归分割序列
        return merger(split(left), split(right));
    }

    /**
     * 合并，注意边界问题
     *
     * @param left
     * @param right
     */
    static int[] merger(int[] left, int[] right) {
        /**
         * 合并2个序列
         */
        int[] newArr = new int[left.length + right.length];

        for (int i = 0, j = 0, index = 0; index < newArr.length; index++) {

            if (i >= left.length && j < right.length) {// 左序列已全部遍历，只剩下右序列
                newArr[index] = right[j++];

            } else if (i < left.length && j >= right.length) {// 右序列已全部遍历，只剩下左序列
                newArr[index] = left[i++];

            } else if (left[i] < right[j]) {
                newArr[index] = left[i++];

            } else /*if (left[i] > right[j])*/ { // 相等情况与大于情况没有区别
                newArr[index] = right[j++];
            }
        }
        print("left", left);
        print("right", right);
        print("排序后", newArr);
        return newArr;
    }

}

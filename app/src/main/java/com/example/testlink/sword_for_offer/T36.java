package com.example.testlink.sword_for_offer;

import android.util.Log;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/8/10 17:06
 * Use by 逆序对的个数
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5 -> [7,5],[7,6],[7,4],[5,4],[6,4]
 */
public class T36 {

    public static void main(String[] args) {
        int[] a = new int[]{45, 5, 89, 7, 4, 12, 3, 15, 23, 56};

        System.out.println(solve(a));
    }

    public static int solve(int[] nums) {

        if (nums == null || nums.length <= 0) return 0;

        int c = split(nums, 0, nums.length - 1, new int[nums.length]);

        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
        return c;
    }

    public static int split(int[] nums, int start, int end, int[] temp) {

        if (start == end) return 0;
        int mid = (start + end) / 2;
        int left = split(nums, start, mid, temp);
        int right = split(nums, mid + 1, end, temp);

        //合并两个序列
        int merger = merger(nums, start, mid, end, temp);

        return left + right + merger;
    }


    /**
     * 合并2个序列  [0,1] [2,3]    [4,5] [6,7]
     */

    /**
     * @param nums  排序后存放容器,nums[start...mid]，nums[mid+1...end]都是有序的。
     * @param start
     * @param mid
     * @param end
     * @param temp  存放待排序的容器
     * @return
     */
    static int merger(int[] nums, int start, int mid, int end, int[] temp) {
        int count = 0;

        //为temp赋值(当次需要排序的数)
        for (int i = start; i <= end; i++) {
            temp[i] = nums[i];
        }

        for (int index = start, i = start, j = mid + 1; index <= end; index++) {


            if (i >= mid + 1 && j <= end) { //只剩下右边
                nums[index] = temp[j++];
            } else if (i < mid + 1 && j > end) {// 只剩下左边
                nums[index] = temp[i++];

            } else if (temp[i] > temp[j]) {
                nums[index] = temp[j++];

                //另一种统计方式：统计与temp[j]组成的逆序对，以temp[j]为参照
                // temp[i]都大于了temp[j],那么对于left子序列，temp[i]后面的都大于temp[j]。
                // 这里统计数量用优先确定temp[j]组成的逆序对比先确定temp[i]组成的逆序对要简单。如果要优先
                //确定temp[i]而去统计temp[j]的个数时对于右边子序列(temp[j]所在)，每次只统计一个(当前的这
                // 个temp[j])，那么这个数前面的没有统计到；而又不能每次都统计tmp[j]以及前面的个数(会产生重
                // 复统计)，对比统计左边(即temp[i]所在的序列)更加复杂
                count += mid - i + 1;

            } else {
                nums[index] = temp[i++];
            }
        }

        return count;
    }

    /**
     * [45, 5, 89, 7, 4, 12, 3, 15, 23, 56]
     *
     * [[45,5],[89]],  [7,4]   [[12,3],[15]],  [23,56]
     *
     * [[5,45],[89]],  [7,4]   [[3,12],[15]],  [23,56]
     *
     * [5,45,89],[4,7]  [3,12,15],[23,56]
     *
     * [4,5,7,89,90]  [3,12,15,23,56]
     *
     *
     *-------------------------------------------------
     *
     *  [5,89,90,100,101] [2,3,15,11,20,33]
     *
     */
}

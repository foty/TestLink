package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/8/25 10:08
 * Use by 两个乘积最小的数
 * 描述： 给定一组递增排序数组和一个数字s。在数组中找出和为s的2个数。如果有多对数字和等
 * 于s，取乘积最小的俩个数。
 */
public class T42 {

    /**
     * 思路： 因为是递增数组，用俩个指针分别定义在头尾，俩数和为s时即为答案的俩数。头尾指针
     * 间隔越大，俩数乘积越小。
     *
     * @param arr
     * @param target
     * @return
     */
    public int[] solve(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int p = 0, q = arr.length - 1;

        while (p < q) {

            if (arr[p] + arr[q] == target) {
                int[] ars = new int[2];
                ars[0] = arr[p];
                ars[1] = arr[q];
                return ars;
            }
            if (arr[p] + arr[q] < target) {
                p++;
            }
            if (arr[p] + arr[q] > target) {
                q--;
            }
        }

        return new int[0];
    }
}

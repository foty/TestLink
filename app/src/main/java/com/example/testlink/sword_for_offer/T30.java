package com.example.testlink.sword_for_offer;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/7/17 15:44
 * Use by 最小的K个数。
 */
public class T30 {

    /**
     * 排序。将数组排序成有序数组后，从前面取出K个数即可
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] solve(int[] arr, int k) {
        int[] result = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    /**
     * 使用堆(待完善)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] solve2(int[] arr, int k) {
        // TODO: 2020/7/17  现在还不了解堆。以后在探索第二种方式
        return new int[k];
    }

}

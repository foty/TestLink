package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/6 17:08
 * Use by 求旋转数组的最小数字
 * <p>
 */
public class T8 {

    /**
     * 问题：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
     * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     */


    /**
     * 假设没有重复元素
     * 解法1
     * @param arr
     */
    private int fun1(int[] arr) {

        /**
         *
         * 根据题意，传入数组为递增排序数组的旋转数组，并且还没有没有重复元素。旋转数组也是排序数组。可以考虑二分法。
         * 需要考虑的有，数组重复，递增排序数组。
         *
         * tip: 排序数组的查找问题首先考虑使用 二分法 解决。
         */

        if (arr == null || arr.length <= 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        //表示这是一个递增数组，开头即是最小值。
        if (arr[0] < arr[arr.length - 1]) {
            return arr[0];
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 恰好处于中间的转折点(情况1：4,5,6,7,1,2,3)
            if (arr[mid] > arr[mid + 1]) {
                return arr[mid + 1];
            }
            // 恰好处于中间的转折点(情况2：5,6,7,1,2,3,4)
            if (arr[mid - 1] > arr[mid]) {
                return arr[mid];
            }

            if (arr[0] > arr[mid]) {//第一个数字居然大于中间值，那么转折点(最小值)在前半部分,缩小查找区间
                right = mid - 1;
            } else if (arr[0] < arr[mid]) {
                left = mid + 1;
            } else { //
                left++;
            }
        }
        return -1;
    }

    /**
     * 存在重复元素的解法
     *
     * @param arr
     * @return
     */
    private static int fun2(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] < arr[arr.length - 1]) {
            return arr[0];
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[right] > arr[mid]) {  // 这里需要注意使用二分法 一定是要用中间值比较右边值，不能用左边值比较中间值。
                right = mid;
            } else if (arr[right] < arr[mid]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return arr[left];
    }

    /**
     * 无论存不存在重复元素都可以使用的解法。
     * 因为是排序旋转数组，必然有从大到小的转折。循环遍历找出转折即是答案。若没有，则这个是递增排序数组
     *
     * @param arr
     * @return
     */
    private static int fun3(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return arr[i + 1];
            }
        }
        return arr[0];
    }
}

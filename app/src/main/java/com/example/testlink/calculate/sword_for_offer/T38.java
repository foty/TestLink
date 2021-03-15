package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/8/14 9:41
 * Use by 某个数出现在数组中出现次数
 */
public class T38 {

    /**
     * 统计一个数字在排序数组中出现的次数。
     * 思路1：
     * 最简单的方法，一遍循环过去，相等就数量加1.时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * 思路2：因为是排序数组可以利用二分查找统计。
     */

    /**
     * @param nums
     * @param k
     * @return
     */
    public int solve(int[] nums, int k) {

        if (nums == null || nums.length <= 0) return 0;

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) count++;
        }
        return count;
    }
}

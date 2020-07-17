package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/7/17 15:50
 * Use by 连续子数组的最大和。
 * <p>
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * 例子:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class T31 {

    public static void main(String[] args) {
        int solve = solve(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(solve);
    }

    /**
     * 思路: 动态规划。
     * 每个数依次相加求和。如果和反而小于相加的这个数，那么这个数之前的数之和与后面任何数都会小于这个数
     * 与后面的数之和。故可以放弃前面的所有数，另起这个数为开始。
     *
     * @param nums
     * @return
     */
    public static int solve(int[] nums) {
//        int sum = 0;  // sum初始化不能为0，负数比0更小。
        int sum = nums[0];
        int dex = 0;

        for (int i = 0; i < nums.length; i++) {
            dex = dex + nums[i];
            if (dex < nums[i]) {
                dex = nums[i];
            }
//            dex = nums[i] >0 ? dex + nums[i] : nums[i];
            sum = Math.max(sum, dex);

            System.out.println(dex);
        }
        return sum;
    }

}

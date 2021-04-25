package com.example.testlink.calculate.dp;

/**
 * Create by lxx
 * Date : 2021/4/25 10:43
 * Use by dp金典问题
 */
public class DpIssue {

    public static void main(String[] args) {
        int[] ints = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        p53(ints);
    }


    /**
     * 最大子序和
     */
    public static int p53(int[] nums) {
        /**
         * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
         * 示例 1：
         *
         * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
         * 输出：6
         * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
         */

        /**
         * dp思路：
         *
         * 1、定义dp数组：
         * 要找到最大和的连续子数组，可以分解成多个子问题。在数组nums中，某元素下标为i，如果知道了前i-1个子数组的最大和，
         * 那么前i个子数组的最大和 = sum(i-1) + nums[i]。注意这里的最大和sum(i)是对应前i个数的最大和，也就是数sum(i)
         * 不一定是最终答案，因为有可能sum(i-1) > sum(i)。
         * dp[i] 表示前i个数的最大子序和。
         *
         * 2、确定base case：
         * base case为第一个元素。dp[0] = nums[0]
         *
         * 3、边界条件
         * 数组nums的长度
         *
         * 4、转移方程
         * 很明显，dp[i] = max(dp[i-1]+ nums[i],nums[i])。为什么是在2个数中选择最大的呢？因为每个元素也
         * 能单独成为子数组，如果dp[i-1]加上nums[i]反而比自身小了，那么完全可以脱离dp[i-1],自己独立一个子序列
         * 出来。可能会有疑问，为什么不再跟dp[i-1]比较，如果dp[i-1]比上面2个数都大的话，不用dp[i-1]。这里要注意
         * 这个dp[i]针对的是前i个数，那么它的子序和只能是前i个数加上第i数的和，或者i独立一个作为子序和。如果使用
         * dp[i-1]的话就更像是直接求出最终答案，而dp[i]却不是最终答案。最终答案还需要通过比较取出最大的那个数(一开
         * 始我也进入这个误区，实际就是对dp数组的定义不够明确)。
         *
         * 5、状态压缩
         * 所有涉及只与dp[i]、dp[i-1]相关，可以使用2个变量保存，替换dp数组，空间由O(n):数组大小为n -> O(1)。
         */

        // 标准dp(未优化空间)

        int[] dp = new int[nums.length];
        dp[0] = nums[0]; // 题目声明 nums.length >= 1
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        // 优化空间
        int pre, aft;
        pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            aft = Math.max(pre + nums[i], nums[i]);
            pre = aft;
            max = Math.max(max, aft);
        }
//        执行用时：1 ms, 在所有 Java 提交中击败了 94.84% 的用户
//        内存消耗：38.5 MB, 在所有 Java 提交中击败了 41.95% 的用户
        return max;
    }
}

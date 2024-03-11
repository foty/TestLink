package com.example.testlink.calculate.dailyari.other;

public class NumArray303 {

    /**
     * 给定一个整数数组nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     * <p>
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点
     * （也就是 sum(nums[i], nums[i + 1], ... , nums[j])）。
     * <p>
     * 如：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     * <p>
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     *
     * <p>
     * 0 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     * 0 <= i <= j < nums.length
     * 最多调用 104 次 sumRange 方法
     */


    private int[] nums;
    private int[] numsSum; //保存和

    public NumArray303(int[] nums) {
        //1
        this.nums = nums;

        //2
        numsSum = new int[nums.length];
        if (nums.length == 0) return;
        //第一个
        numsSum[0] = nums[0];
        for (int i = 1; i < numsSum.length; i++) {
            numsSum[i] = nums[i] + numsSum[i - 1];
        }

        for (int i = 0; i < numsSum.length; i++) {
            System.out.print(numsSum[i]+"  ");
        }
        System.out.println();
    }

    public int sumRange(int i, int j) {
        if (nums.length == 0) return 0;
        int result = 0;
        for (int k = i; k <= j; k++) {
            result += nums[k];
        }
        return result;
    }

//    执行用时：100 ms, 在所有 Java 提交中击败了5.25%的用户
//    内存消耗：41.2 MB, 在所有 Java 提交中击败了73.48%的用户


    /**
     * 因为会重复调用sumRange()方法，不得不考虑效率问题。每次调用sumRange()都走一遍for循环明显效率是最低的。结合题目，
     * 求得的是一个区间的值。可以先将从0到这个下标位置(包括)的和统计出来，然后再做减法，求出需要的区间和即可。而且这一步可以放在
     * 构造时候完成。这样就将每次的循环变成了一次循环。
     */


    public int sumRange2(int i, int j) {
        if (i == 0) return numsSum[j]; // 初始位置=0，所求和刚好对应和数组下标。
        return numsSum[j] - numsSum[i-1];
    }

//    执行用时：9 ms, 在所有 Java 提交中击败了99.83%的用户
//    内存消耗：41.4 MB, 在所有 Java 提交中击败了34.78%的用户
}
package com.example.testlink.dailyari;

class NumArray {

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

    public NumArray(int[] nums) {
        this.nums = nums;
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
}
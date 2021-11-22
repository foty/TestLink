package com.example.testlink.calculate.dailyari;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create by lxx
 * Date : 2021/11/22 11:22
 * Use by
 */
class Subject384 {

    /**
     * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
     * 实现 Solution class:
     * Solution(int[] nums) 使用整数数组 nums 初始化对象
     * int[] reset() 重设数组到它的初始状态并返回
     * int[] shuffle() 返回数组随机打乱后的结果
     *
     * 示例：
     * 输入
     * ["Solution", "shuffle", "reset", "shuffle"]
     * [[[1, 2, 3]], [], [], []]
     * 输出
     * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
     *
     * 解释
     * Solution solution = new Solution([1, 2, 3]);
     * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
     * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
     * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
     *
     * 提示：
     * 1 <= nums.length <= 200
     * -106 <= nums[i] <= 106
     * nums 中的所有元素都是 唯一的
     * 最多可以调用 5 * 104 次 reset 和 shuffle
     */

    /**
     * 思路：解决关键点就是如何让每种情况出现的概率一致。处理方案是随机下标。通过随机数获取一个下标，选择对应数字
     * 后，对现有的未选择数组重新获取随机下标。这样对于每一种排序出现的情况的概率都是相同的。
     */

    int[] nums;
    int[] original;

    public Subject384(int[] nums) {
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        int[] shuffled = new int[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            list.add(nums[i]);
        }
        // 打乱数组，使用随机下标实现。
        Random random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            int j = random.nextInt(list.size());
            shuffled[i] = list.remove(j);
        }
        System.arraycopy(shuffled, 0, nums, 0, nums.length);
        return nums;
    }
}

package com.example.testlink.calculate.sword_for_offer;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/8/28 16:00
 * Use by 扑克牌中的顺子
 * 描述：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 */
public class T45 {

    /**
     * 思路： 若是顺子，则每俩个数的差值为1。0位任意数。那么可以判断这个数组的一个总差值
     * 跟0的数量比较，如果0的数量小于差值和，则不是顺子。
     * 注意：5张牌可能出现重复牌。若有重复牌，则一定不是顺子。
     *
     * @param nums
     * @return
     */
    public boolean solve(int[] nums) {
        int zore = 0;
        int value = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                zore++;
                continue;
            }
            //判断重复
            if (nums[i + 1] == nums[i]) {
                return false;
            }
            value += nums[i + 1] - nums[i] - 1;
        }
        return value <= zore;
    }
}

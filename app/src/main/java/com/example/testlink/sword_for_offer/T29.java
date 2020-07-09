package com.example.testlink.sword_for_offer;

import java.util.HashMap;
import java.util.Set;

/**
 * Create by lxx
 * Date : 2020/7/9 15:59
 * Use by 数组中超过一半的数字
 */
public class T29 {

    /**
     * 假如这个数字一定存在
     *
     * @param nums
     * @return
     */
    public int solve(int[] nums) {

        // 解法1
        // Arrays.sort(nums);
        // return nums[nums.length/2];


        //解法2
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i : nums) {
//            if (!map.containsKey(i)) {
//                map.put(i, 1);
//            } else {
//                map.put(i, map.get(i) + 1);
//            }
//        }
//
//        int more = 0;
//        for (Integer key : map.keySet()) {
//            more = map.get(key) > more ? map.get(key) : more;
//        }
//        return more;


        //解法3(摩尔投票，最优解,效率最高)
        /**
         * 摩尔投票(基于某元素数量超过总数的一半)：
         * 票数和：
         * 由于众数出现的次数超过数组长度的一半；若记众数的票数为+1 ，非众数的票数为−1，则一定有所有数字的票数和 > 0。
         *
         * 正负抵消(核心思想)：
         * 设数组 nums 中的众数为x，数组长度为 n 。若 nums 的前a个数字的 票数和=0，则数组后(n-a)个数字的
         * 票数和一定仍>0，（即后 (n-a)个数字的众数仍为x）。
         *
         */
        int more = 0;
        int count = 0;
        for (int i : nums) {
            if (count == 0) {
                more = i;
            }
            if (i == more) {
                count++;
            } else {
                count--;
            }
        }
        return more;
    }

    /**
     * 假如不一定存在这个数。
     * <p>
     * 分析：此时排序法已经不适用这一场景了。
     * 统计法、摩尔投票法需要增加一个长度判断。
     *
     * @param nums
     * @return
     */
    public int solve1(int[] nums) {
        //解法2
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i : nums) {
//            if (!map.containsKey(i)) {
//                map.put(i, 1);
//            } else {
//                map.put(i, map.get(i) + 1);
//            }
//        }
//
//        for (Integer key : map.keySet()) {
//            if (map.get(key) > nums.length / 2) {
//                return key;
//            }
//        }
//        return 0;


        //解法3(摩尔投票，最优解,效率最高)
        int more = 0;
        int count = 0;
        for (int i : nums) {
            if (count == 0) {
                more = i;
            }
            if (i == more) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int i : nums) {
            if (i == more) {
                count++;
            }
        }
        return count > nums.length / 2 ? more : 0;
    }
}

package com.example.testlink.sword_for_offer;

import java.util.HashMap;

/**
 * Create by lxx
 * Date : 2020/9/16 11:00
 * Use by 找出重复的数。
 * 描述： 在一个长度为n的数组里，所有的数字都在0到n-1的范围内，找出数组中任意一个重复的数字。
 */
public class T50 {

    //解法1
    public int solve(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                return a[i];
            } else {
                map.put(a[i], i);
            }
        }
        return -1;
    }

    /**
     * 解法2 原地置换(一个萝卜一个坑)。
     *
     * 思路：
     * 仔细读题“在一个长度为n的数组里，所有的数字都在0到n-1的范围内”，这意味着如果没有重复数字话，
     * 将这个数组排序后将是按照0,1,2,3...,n-1 的顺序排列，刚好下标对应数字。因此，可以对数字按
     * 照i=a[i]的思路进行置换排序。如果出现置换的两个数相等时，即是重复数字。
     *
     * @param a
     * @return
     */
    public int solve2(int[] a) {
        int tem;
        for (int i = 0; i < a.length; i++) {

            //当i != a[i]时，因为不能知道数字i在数组的那个位置(a[?] = i),所以只能优先处理a[i],
            // 就是 a[a[i]] = a[i]。所以将a[i] 与a[a[i]]调换位置。但是为了保证最终的i=a[i]结
            // 果所以内层需要使用while循环。
            while (i != a[i]) {
                if (a[i] == a[a[i]]) {
                    return a[i];
                }
                tem = a[i];
                a[tem] = tem;
                a[i] = a[tem];
            }
        }
        return -1;
    }

}

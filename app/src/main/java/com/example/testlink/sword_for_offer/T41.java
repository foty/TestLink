package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/8/24 10:07
 * Use by 整数序列的查找
 * 描述： 输入一个正整数，输出所有和为此数的连续正整数序列(至少含有2个数)。序列内数
 * 从小到大排列，不同序列以首字母从小到大排列。
 */
public class T41 {

    public static void main(String[] args) {

        int[][] solve = solve(100);

        for (int i = 0; i < solve.length; i++) {
            for (int j = 0; j < solve[i].length; j++) {
                System.out.print(solve[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 思路：滑动窗口/双指针法。理由：连续整数序列。
     * 固定一个窗口大小，如果窗口整数和比目标值小，窗口左边不动，右边移动一位，如果整数和大于目标值，
     * 固定右边，左边往前面移动，直到整数和不大于目标值。
     *
     * @param target
     * @return
     */
    public static int[][] solve(int target) {

        List<int[]> list = new ArrayList<>();
        int s = 1;
        int sum = 0;
        for (int i = 1; i < target; i++) {

            sum += i;
            while (sum > target) {
                sum = sum - s;
                s++;
            }
            if (sum == target) {
                int[] ar = new int[i - s + 1];
                for (int j = s; j <= i; j++) {
                    ar[j - s] = j;
                }
                list.add(ar);
            }
        }
        int[][] result = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     执行用时：3 ms, 在所有 Java 提交中击败了86.94%的用户
     内存消耗：37.8 MB, 在所有 Java 提交中击败了14.72%的用户
     */

}

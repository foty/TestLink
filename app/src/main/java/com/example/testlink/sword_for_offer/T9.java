package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/7 17:04
 * Use by 斐波那契数列系列
 * <p>
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 */
public class T9 {

    /**
     * 问题1：斐波那契数列-输出斐波那契数列的第n项。
     */

    /**
     * 解法1 递归。但是这种效率极其低下。不考虑。
     *
     * @param n
     * @return
     */
    private int problem1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return problem1(n - 1) + problem1(n - 2);
    }

    /**
     * 问题1，解法2-循环法
     *
     * @param n
     * @return
     */
    private int problem2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int result = 0;
        //倒数第一项
        int bFirst = 1;
        //倒数第二项
        int bSecond = 0;
        for (int i = 2; i <= n; i++) {
            result = bFirst + bSecond;

            bFirst = result;
            bSecond = bFirst;
        }
        return result;
    }


    /**
     * 问题2 ：青蛙掉台阶问题: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     */
}

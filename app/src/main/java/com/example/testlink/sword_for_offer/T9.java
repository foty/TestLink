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
     * 问题1，解法2-循环法(动态规划)
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

            bSecond = bFirst;
            bFirst = result;
        }
        return result;
    }


    /**
     * 问题2 ：青蛙掉台阶问题: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     */

    /**
     * 思路: 斐波那契数列思想。
     * <p>
     * 分析:青蛙一次可以跳上1级台阶或者跳上2级台阶。那么最后一次跳法只有2种:跳上2个台阶或者跳上1个台阶。假设f(n)表示为求跳上n级台阶的
     * 跳法。那么本题变相成：青蛙跳上n级台阶跳法 = 跳上n-1级台阶跳法 + 跳上n-2级台阶跳法。即f(n)= f(n-1)+f(n-2)。同样f(n-1)又可以变成
     * f(n-1)=f(n-2)+f(n-3),...,f(3)=f(1)+f(0)。在最终结果确定后，每一步骤都可以由前一步或2步递推而来的，都可以使用斐波那契数列思想来解答。
     * 关键就是怎么将题目转换到斐波那契数列思想中来。最后注意起始值。
     *
     * @param n
     * @return
     */
    public int problem3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int bf = 2;
        int bs = 1;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = bf + bs;
            bs = bf;
            bf = sum;
        }
        return sum;
    }

    /**
     * 问题3：
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     */

    /**
     * 思路：斐波那契数列思想。
     * <p>
     * 自己规定2*1的小矩形是宽为2，高为1。那么小矩形横放宽为2，竖放宽为1。那么使用2*1的小矩形无重叠地覆盖一个2*n的大矩形它完全被覆盖时
     * 最后操作为使用1个2*1小矩形(横)完全覆盖或者2个2*1小矩形(竖)完全覆盖。这是必然确定的。以f(n)作为此题覆盖的总方法，那么有:
     * f(n) = f(n-1) + f(n-2)。最终转换成斐波那契数列思想。最后注意起始值。
     *
     * @param n
     * @return
     */
    public int problem4(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int sum = 0;
        int bf = 2;
        int bs = 1;
        for (int i = 3; i <= n; i++) {
            sum = bf + bs;
            bs = bf;
            bf = sum;
        }
        return sum;
    }

    /**
     * 问题4 ： 如果青蛙一次可以跳上1级台阶，也可以跳上2级台阶，也可以跳上3级台阶，也可以跳上n级台阶。问青蛙跳上n级台阶有几种方法。
     */

    /**
     * 同样根据斐波那契数列思想可以得出:
     * f(n)= f(n-1)+f(n-2)+f(n-3)+...+f(1)+f(0); 同样的有:
     * f(n-1)= f(n-2)+f(n-3)+...+f(1)+f(0);
     * f(n-2)= f(n-3)+...+f(1)+f(0);
     * ....
     * 将第二等式低缓到第一等式为:
     * f(n) = 2 x (f(n-2)+f(n-3)+...+f(1)+f(0));
     * 类推 f(n-2),f(n-3),f(n-..)则有:
     * f(n) = 2 x 2 x(f(n-3)+...+f(1)+f(0));
     * f(n) = 2 x 2 x 2 x(f(n-4)+...+f(1)+f(0));
     * 最终为:
     * f(n) = 2 x 2 x ...x 2 x(f(1)+f(0)) == 2^n-1 x (f(1)+f(0))。
     * 而且当n = 1时，只有一种方法。如果f(0) = 0的话则 f(n) = 2^n-1. 如果f(0) = 1,则f(n) = 2^n-1 + 1。
     *
     * @param n
     * @return
     */
    private int problem5(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int sum = 1;
        for (int i = 1; i < n; i++) {
            sum = sum * 2;
        }
        return sum;
    }
}

package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/9 10:12
 * Use by 数值的整数次方
 * <p>
 */
public class T11 {

    /**
     * 问题：实现函数double Power(double base, int exponent)，求x的n次方。不得使用库函数，
     * 同时不需要考虑大数问题
     *
     */

    /**
     * 传统方法实现
     * 此方法逻辑上没有问题，但是提交到刷题网站上会导致超出时间限制。
     *
     * @param x
     * @param n
     * @return
     */
    public double solve(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        double sum = 1;
        if (n < 0) {
            for (int i = 0; i < -n; i++) {
                sum = sum * 1 / x;
            }
        } else {
            for (int i = 0; i < n; i++) {
                sum = sum * x;
            }
        }
        return sum;
    }


    /**
     * 递归折半相乘法。
     * 相比较以每个x相乘，折半递归效率更高。
     * 但是要注意n=奇数时,n/2时是少乘以一个n的，需要补乘上去。
     *
     * @param x
     * @param n
     * @return
     */
    public double solve2(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        double half = solve2(x, n / 2);
        // 判断奇数偶数返回
        double mod = solve2(x, n % 2);
        return half * half * mod;
    }


    /**
     * 快速幂算法初阶版(缩指数)。递归的另一种形式(思想都是一致的--降幂)。
     * 剖析：现有求一个 3^6的值。3^6 = 3*3*3*3*3*3 = 9*9*9 = 9^3 = 81^1*9
     * 从开始的指数为6，降到3，最后降到1，极大程度上缩短了计算次数。
     *
     * @param x
     * @param n
     * @return
     */
    private double solve3(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        //因此当 n = -2147483648,时执行 n = -n,会因越界而赋值出错。
        // 解决方法是先将n存入long变量，后面long变量操作即可
        long l = n;
        if (l < 0) {
            x = 1 / x;
            l = -l;
        }
        double result = 1;
        while (l > 0) {
            /*if (l % 2 == 0) {
                l = l / 2; // 指数缩小一倍
                x = x * x; // 底数增大一倍
            } else {
                l--; //因为是奇数，先自减去1变成偶数,(但其实这里减不减都不影响 l/2的计算)。后面需要多乘一个x。
                result = result * x;
                l = l / 2;
                x = x * x;
            }*/

            //以上代码可以优化为
            if (l % 2 == 1) {//因为是奇数，先自减去1变成偶数。后面需要多乘一个x。
                result = result * x;
            }
            l = l / 2; // 指数缩小一倍
            x = x * x; // 底数增大一倍
        }
        return result;
    }


    /**
     * 快速幂算法的进阶版--(操作二进制)
     *
     * @param x
     * @param n
     * @return
     */
    private double solve4(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        //因为当 n = -2147483648,时执行 n = -n,会因越界而赋值出错。
        // 解决方法是先将n存入long变量，后面long变量操作即可
        long l = n;
        if (l < 0) {
            x = 1 / x;
            l = -l;
        }
        double result = 1;
        while (l > 0) {
            if ((l&1)== 1) { //将%运算替换为&运算,效率更高
                result = result * x;
            }
            l >>= 1;
            x = x * x;
        }
        return result;
    }
}

package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/5/9 15:17
 * Use by 打印从1到最大的n位数。
 */
public class T12 {

    /**
     * 问题:输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
     * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     */

    public static void main(String[] args) {
        solve2(2);
    }

    /**
     * 默认数组内元素在integer范围内情况下
     *
     * @param n
     */
    private static int[] solve(int n) {
        double pow = Math.pow(10, n);
        int max = (int) pow - 1;
        int[] res = new int[max];
        for (int i = 1; i <= max; i++) {
            res[i] = i;
        }
        return res;
    }

    /**
     * 大数据，不考虑界限问题
     *
     * @param n
     */
    private static void solve2(int n) {
        if (n<= 0){
            System.out.println("n <= 0");
            return;
        }
        int[] ints = new int[n];
        boolean end = false;
        while (!end) {
            for (int i = ints.length - 1; i >= 0; i--) {
                ints[i] = ints[i] + 1;
                if (ints[i] > 9) {
                    //进位
                    if (i == 0) {
                        //数组内最后数也大于9，范围内所有数字已经打印
                        end = true;
                    } else {
                        ints[i] = 0;
                    }
                } else {
                    print(ints);
                    break;
                }
            }
        }
    }

    private static void print(int[] ints) {
        StringBuilder sb = new StringBuilder();
        boolean notAdd = true;
        for (int i = 0; i < ints.length; i++) {
//            System.out.print(ints[i]);
            if (ints[i] != 0) {
                notAdd = false;
            }
            if (!notAdd) {
                sb.append(ints[i]);
            }
        }

        System.out.print(sb.toString());
        System.out.println();
    }
}

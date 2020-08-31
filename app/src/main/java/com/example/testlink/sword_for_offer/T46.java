package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/8/31 14:34
 * Use by 圆圈中最后剩下的数。
 * 描述： 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此
 * 最后剩下的数字是3。
 */
public class T46 {

    public static void main(String[] args) {
        // 10 17 2
        System.out.println(solve(5, 3));
    }


    /**
     * 0 1 2 3 4
     *
     *
     * @param n
     * @param m
     * @return
     */
    public static int solve(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = 0;
        while (list.size() != 1) {

            //直接找到要删除的那个位置。
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }
}

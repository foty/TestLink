package com.example.testlink.sword_for_offer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Create by lxx
 * Date : 2020/7/7 14:13
 * Use by 打印字符中所有字符的排列
 */
public class T28 {

    public static void main(String[] args) {
        String[] abcs = solve("abcde");
        System.out.println(17 * 7 + 1);
        System.out.println(abcs.length);

        for (int i = 0; i < abcs.length; i++) {
            System.out.println(abcs[i]);
        }
    }


//    执行用时：26 ms, 在所有 Java 提交中击败了41.40%的用户
//    内存消耗：44.3 MB, 在所有 Java 提交中击败了100.00%的用户

    /**
     * 思路： 递归。
     * 容易忽略的点：字符重复 --> (去重)
     *
     * @param s
     * @return
     */
    public static String[] solve(String s) {
        if (null == s || s.length() <= 0) {
            return new String[0];
        }

        if (s.length() == 1) {
            String[] sirs = new String[1];
            sirs[0] = s;
            return sirs;
        }

        Set<String> list = new HashSet<>();
        reSwap(0, s, list);

        return list.toArray(new String[0]);
    }

    /**
     * 从里层到外层输出结果。
     *
     * @param index
     * @param s
     * @param list
     */
    private static void reSwap(int index, String s, Set<String> list) {
        char[] chars = s.toCharArray();
        if (index < s.length() - 2) {  //终止递归条件(进入到了字符串最后2位时，交换位置)
            reSwap(index + 1, s, list);

            //交换一个位置后，循环交换后面每个位置的字符。
            for (int i = index + 1; i < chars.length; i++) {
                swap(list, chars, index, i);
            }

        } else {
            list.add(s);
            swap(list, chars, index, index + 1);
        }
    }

    private static void swap(Set<String> list, char[] chars, int start, int end) {
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;

        if (start < chars.length - 2) {// 交换位置后，后续是否需要递归
            reSwap(start + 1, String.valueOf(chars), list);
        } else {
            list.add(String.valueOf(chars));
        }
    }


    List<String> res = new LinkedList<>();
    char[] c;

    /**
     * 第二种解法(最完美解法),原理与第一种是一样的。
     * <p>
     * 如何交换?交换后添加到list?去重?
     *
     * @param s
     */
    private String[] solve2(String s) {

        c = s.toCharArray();
        reSolve2(0);
        return res.toArray(new String[res.size()]);
    }

    private void reSolve2(int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue; // 重复，因此剪枝

            set.add(c[i]);
            swap(i, x); // 交换，将 c[i] 固定在第 x 位
            reSolve2(x + 1); // 开启固定第 x + 1 位字符
            swap(i, x); // 恢复交换
        }
    }

    /**
     * 单纯交换
     * @param a
     * @param b
     */
    private void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

}

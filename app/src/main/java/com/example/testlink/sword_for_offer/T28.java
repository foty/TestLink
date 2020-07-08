package com.example.testlink.sword_for_offer;

import java.util.HashSet;
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
}

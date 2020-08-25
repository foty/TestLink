package com.example.testlink.sword_for_offer;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/8/25 10:39
 * Use by 反转字符串
 * 描述： 输入一句英文句子，反转句子中单词的顺序，但是单词内的句子顺序不变。
 */
public class T43 {

    public static void main(String[] args) {
        System.out.println(solve("a d f fd fdfd io 454 klk klk"));
    }


    /**
     * aaa bbb ccc  => ccc bbb aaa
     * abc def ghi  => ghi def abc
     *
     * 思路： 普通的字符串反转。
     */
    public static String solve(String s) {
        System.out.println(s);
        if (s == null || s.length() <= 0 || !s.contains(" ")) return s;

        String[] split = s.split(" ");

        int p = 0, q = split.length - 1;
        while (p < q) {
            String temp = split[p];
            split[p] = split[q];
            split[q] = temp;
            p++;
            q--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            if (i != split.length -1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }


}

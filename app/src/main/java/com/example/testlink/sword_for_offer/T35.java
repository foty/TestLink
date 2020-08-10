package com.example.testlink.sword_for_offer;

import java.util.HashMap;

/**
 * Create by lxx
 * Date : 2020/8/7 16:54
 * Use by 第一个出现一次的字符。
 */
public class T35 {

    /**
     * 思路： 2遍循环遍历，找出第一个数量为1的字符返回即可。
     * @param s
     * @return
     */
    public char solve(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
}

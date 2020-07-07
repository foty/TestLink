package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

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
//        System.out.println(count);
        for (int i = 0; i < abcs.length; i++) {
            System.out.println(abcs[i]);
        }
    }


    public static String[] solve(String s) {
//        if (null == s || s.length() <= 0) {
//            return new String[0];
//        }
//
//        if (s.length() == 1) {
//            String[] sirs = new String[1];
//            sirs[0] = s;
//            return sirs;
//        }


        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {

            } else {
                char temp = chars[0];
                chars[0] = chars[i];
                chars[i] = temp;
            }
            reSwap(1, s, list);
        }


        String[] sss = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sss[i] = list.get(i);
        }
        return sss;
    }

    static int count;


    private static void reSwap(int index, String s, List<String> list) {
        char[] chars = s.toCharArray();
        if (index < s.length() - 2) {
            reSwap(index + 1, s, list);
            swap2(list, chars, index, index + 1);

        } else {
            list.add(s);
            swap2(list, chars, index, index + 1);
        }
    }

    private static void swap(List<String> list, char[] chars, int start) {

        char temp = chars[start];
        chars[start] = chars[start + 1];
        chars[start + 1] = temp;
        count++;
        list.add(String.valueOf(chars));
    }

    private static void swap2(List<String> list, char[] chars, int start, int end) {
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;

        if (start < chars.length - 2) {
            reSwap(start + 1, String.valueOf(chars), list);
        } else {
            list.add(String.valueOf(chars));
        }
    }

    /**
     * ["abcde","abced",
     * "abdce","abdec","abecd","abedc",
     * "acbde","acbed","acdbe","acdeb","acebd","acedb",
     * "adbce","adbec","adcbe","adceb","adebc","adecb",
     * "aebcd","aebdc","aecbd","aecdb","aedbc","aedcb",
     *
     *
     *                         "bacde","baced","badce","badec",
     * "baecd","baedc","bcade","bcaed","bcdae","bcdea","bcead",
     * "bceda","bdace","bdaec","bdcae","bdcea","bdeac","bdeca",
     * "beacd","beadc","becad","becda","bedac","bedca","cabde",
     * "cabed","cadbe","cadeb","caebd","caedb","cbade","cbaed",
     * "cbdae","cbdea","cbead","cbeda","cdabe","cdaeb","cdbae",
     * "cdbea","cdeab","cdeba","ceabd","ceadb","cebad","cebda",
     * "cedab","cedba","dabce","dabec","dacbe","daceb","daebc",
     * "daecb","dbace","dbaec","dbcae","dbcea","dbeac","dbeca",
     * "dcabe","dcaeb","dcbae","dcbea","dceab","dceba","deabc",
     * "deacb","debac","debca","decab","decba","eabcd","eabdc",
     * "eacbd","eacdb","eadbc","eadcb","ebacd","ebadc","ebcad",
     * "ebcda","ebdac","ebdca","ecabd","ecadb","ecbad","ecbda","
     * ecdab","ecdba","edabc","edacb","edbac","edbca","edcab",
     * "edcba"]
     */
}

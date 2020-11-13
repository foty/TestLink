package com.example.testlink.calculate.labuladongs;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/11/9 16:07
 * Use by 公众号：labuladong 题录
 */
public class Topics {

    public static void main(String[] args) {
        List<String> list = solve2(3);
        for (String s : list) {
            System.out.println(s + "  ");
        }
    }

    /**
     * 不同的二叉搜索树 II
     */
    public void solve1() {

    }

    /**
     * 面试题-括号
     * <p>
     * 给出一个正整数n，返回包含n对括号的合法括号序列。
     * 如n = 3，
     * 返回["((()))","(()())","(())()","()(())","()()()"]
     * <p>
     * 分析：
     * 已知: n对括号。求：n对括号的合法组合序列。对于这种没有一个明确的规则或者顺序来进行组合排列，只要求了合法或
     * 非法状态。可以尝试构造出一组规则，按照某种顺序来执行。例如本题，只是说明了括号的合法序列，但是我们不知道该
     * 从哪里开始，按照什么顺序来排列与组合。因此需要自己构造出这种规则顺序。我们假设所有的合法序列都是有左边合法
     * 括号序列+右边括号合法序列构成的。即：V = S1+S2；由于括号对数是一定的，左边序列包含多少，相应右边括号序列
     * 数量由总数减去左边的括号对数。
     */
    public static List<String> solve2(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0) {
            list.add("");
            return list;
        }
        if (n == 1) {
            list.add("()");
            return list;
        }

        for (int i = 0; i < n; i++) {
            List<String> list1 = solve2(i);
            for (int j = 0; j < list1.size(); j++) {

                List<String> list2 = solve2(n - i - 1);
                for (int k = 0; k < list2.size(); k++) {

                    list.add("(" + list1.get(j) + ")" + list2.get(k));
                }
            }
        }
        return list;
    }
}

package com.example.testlink.sword_for_offer;

import java.util.HashMap;

/**
 * Create by lxx
 * Date : 2020/9/27 14:16
 * Use by 表示字符的字符串
 * 描述：请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、
 * "-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、
 * "+-5"及"12e+5.4"都不是。
 */
public class T53 {

    public static void main(String[] args) {
        System.out.println(isNumber("3.     "));
    }

    /**
     * 有限状态机的思想实现。定义出所有状态，根据输入的字符切换为下一种状态。有限状态机的伪代码一般为：
     * <p>
     * 状态-->转移状态-->目标状态。
     * 此题输入的字符类型有：
     * 空格，数字，正负号，小数点，幂符号。
     * 根据能表示数字的字符组成部分可以定义出所有状态：
     * <p>
     * 开始   0
     * 符号   1
     * 数字   2
     * 小数点 3
     * 数字   4
     * 幂符号 5
     * 符号   6
     * 数字   7
     * 结束   8
     * 小数点前面没有其他字符 9 (没有考虑到，补上)。
     * 在所有状态都表述出来后，只需要根据当前输入的字符，转换到下一种状态即可。
     */
    public static boolean isNumber(String s) {
        //表示状态：key表示当前的一个状态，value表示允许接受的下一个字符，如果没有则是不允许，流程结束
        HashMap<Integer, HashMap<Chars, Integer>> statues = new HashMap<>();

        //初始状态:
        HashMap<Chars, Integer> start = new HashMap<>();
        start.put(Chars.SPACE, 0);// 空格
        start.put(Chars.SIGN, 1);//符号
        start.put(Chars.NUMBER, 2);//数字
        start.put(Chars.DOT, 9); //小数点前面没有其他字符
        statues.put(0, start);

        //符号
        HashMap<Chars, Integer> sin = new HashMap<>();
        sin.put(Chars.NUMBER, 2);
        sin.put(Chars.DOT, 9);
        statues.put(1, sin);

        //数字(整数部分)
        HashMap<Chars, Integer> num = new HashMap<>();
        num.put(Chars.NUMBER, 2);//数字
        num.put(Chars.DOT, 3); //小数
        num.put(Chars.POWER, 5); // 3e03
        num.put(Chars.SPACE, 8); //结束。这是数字: 123
        statues.put(2, num);

        //小数点 .
        HashMap<Chars, Integer> dot = new HashMap<>();
        dot.put(Chars.NUMBER, 4);
        dot.put(Chars.POWER, 5);
        dot.put(Chars.SPACE, 8); //结束。是数字。如 5. ==> 5.0
        statues.put(3, dot);

        //前面没有其他数字的小数点 . (忽略了这种情况)
        HashMap<Chars, Integer> dot2 = new HashMap<>();
        dot2.put(Chars.NUMBER, 4);
        statues.put(9, dot2);

        //数字(小数部分的)
        HashMap<Chars, Integer> dec = new HashMap<>();
        dec.put(Chars.NUMBER, 4);  //小数部分的数字
        dec.put(Chars.POWER, 5);  //幂符号 1.e08
        dec.put(Chars.SPACE, 8); //结束。是数字: 1.5
        statues.put(4, dec);

        //幂符号 e
        HashMap<Chars, Integer> pow = new HashMap<>();
        pow.put(Chars.SIGN, 6);
        pow.put(Chars.NUMBER, 7);
        statues.put(5, pow);

        //指数幂后面的符号
        HashMap<Chars, Integer> powSing = new HashMap<>();
        powSing.put(Chars.NUMBER, 7);
        statues.put(6, powSing);

        //指数幂后面的数字
        HashMap<Chars, Integer> powNum = new HashMap<>();
        powNum.put(Chars.NUMBER, 7);
        powNum.put(Chars.SPACE, 8); //结束，是数字。11.1-e45
        statues.put(7, powNum);

        //最后的空格
        HashMap<Chars, Integer> end = new HashMap<>();
        end.put(Chars.SPACE, 8); //结束。后面空格可以忽略。
        statues.put(8, end);

        //初始化状态
        int cStatue = 0;
        for (int i = 0; i < s.length(); i++) {
            Chars cs = getChars(s.charAt(i));
            // 获取当前状态下允许接受的字符范围，如果当前字符不在这个范围，处理结束。
            if (statues.get(cStatue).containsKey(cs)) {
                //切换状态。
                cStatue = statues.get(cStatue).get(cs);
            } else {
                return false;
            }
        }
        return cStatue == 2 || cStatue == 3
                || cStatue == 4 || cStatue == 7
                || cStatue == 8;
    }


    enum Chars {
        SPACE, //空格
        SIGN, // 符号 +/-
        NUMBER, // 数字
        DOT,  // 小数点
        POWER, // 幂符号 e/E
        ERROR  //非法
    }

    private static Chars getChars(char c) {
        if (c >= '0' && c <= '9') {
            return Chars.NUMBER;
        } else if (c == ' ') {
            return Chars.SPACE;
        } else if (c == '+' || c == '-') {
            return Chars.SIGN;
        } else if (c == '.') {
            return Chars.DOT;
        } else if (c == 'e' || c == 'E') {
            return Chars.POWER;
        } else {
            return Chars.ERROR;
        }
    }
}

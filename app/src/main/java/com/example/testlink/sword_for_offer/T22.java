package com.example.testlink.sword_for_offer;

import java.util.Stack;

/**
 * Create by lxx
 * Date : 2020/6/15 10:17
 * Use by 判断一个栈是否是另一栈的弹出序列。
 */
public class T22 {

    /**
     * 判断一个出栈序列是否是另一个栈的弹出序列。其实就是模拟这个入栈序列的出栈。如果是的话，那么就一定
     * 有一种出栈方式得到比较的出栈序列。
     * 实现: 创建一个辅助栈F(用来模拟入栈与出栈)。对比入栈序列，例如:A:[1,2,3,4,5]。那么F同样以这个顺序入栈。
     * 但是每次入栈后要考虑是否会马上执行出栈操作，所以需要拿到出栈序列进行比较。例如出栈序列 B:[4,5,3,2,1]。很明显
     * 它是到4这个元素入栈后，马上执行了出栈，然后元素5入栈，出栈，随后剩余元素依次出栈。
     *
     * 关键: 1、首先要明白可以借助一个辅助栈去模拟入栈，出栈。2、出栈序列的结果并不是一次性结果，而是最终结果。(意思是
     * 出栈序列不是一次性全部出栈的结果，而是多次的出栈的结果。所以需要在入栈后对比出栈序列，是否需要出栈)
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean solve(int[] pushed, int[] popped) {
        Stack<Integer> temp = new Stack<>();

        for (int i = 0; i < pushed.length; i++) {
            //对辅助栈入栈
            temp.push(pushed[i]);
            int popIndex = 0;
            //判断是否到了出栈时机(对比出栈序列)，可能是连续出栈一部分后在重新入栈，所以需要循环比较出栈序列。
            while (!temp.isEmpty() && temp.peek().equals(popped[popIndex])) {
                temp.pop();
                popIndex ++;
            }
        }
        return temp.isEmpty();
    }
}

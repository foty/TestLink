package com.example.testlink.sword_for_offer;

import java.util.Stack;

/**
 * Create by lxx
 * Date : 2020/5/6 16:58
 * Use by 使用2个栈实现队列，完成pop与push操作
 */
public class T7 {

    public static void main(String[] args) {

    }

    class Queues {
        private Stack<Integer> stack;

        public Queues() {
            stack = new Stack<>();
        }

        /**
         * @param i
         */
        public void push(int i) {
            stack.push(i);
        }

        public int pop() {
            if (stack.isEmpty()) return -1;
            return stack.pop();
        }
    }
}

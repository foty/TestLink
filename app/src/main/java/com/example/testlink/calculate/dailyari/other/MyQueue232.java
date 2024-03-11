package com.example.testlink.calculate.dailyari.other;

import java.util.Stack;

public class MyQueue232 {

    private Stack<Integer> saveStack;
    private Stack<Integer> stack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue232() {
        stack = new Stack<>();
        saveStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        saveStack.push(x);

//        while (!saveStack.isEmpty()) { // 倒换的步骤不能放在push操作，除非它只操作一次，否则stack的数据顺序会乱。
//            stack.push(saveStack.pop());
//        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (stack.isEmpty()) { // 只有当新栈的数据全部出队完，开始下一次数据倒置。
            while (!saveStack.isEmpty()) {
                stack.push(saveStack.pop());
            }
        }
        return stack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stack.isEmpty()){
            while (!saveStack.isEmpty()) {
                stack.push(saveStack.pop());
            }
        }
        return stack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack.isEmpty() && saveStack.isEmpty();
    }
}

//执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗：36.1 MB, 在所有 Java 提交中击败了90.31%的用户

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
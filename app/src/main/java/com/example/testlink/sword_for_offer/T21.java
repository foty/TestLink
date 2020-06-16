package com.example.testlink.sword_for_offer;

import java.util.Stack;

/**
 * Create by lxx
 * Date : 2020/6/11 16:00
 * <p>
 * Use by 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)
 */
public class T21 {

    private Stack<Integer> stackA, stackB;

    public T21() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {
        stackA.push(x);

        if (stackB.isEmpty()) {
            stackB.push(x);
        } else {
            if (stackB.peek() >= x) { //注意一定要是>=,否则会导致栈B调用peek()时栈B内元素为空
                stackB.push(x);
            }
        }
    }

    public void pop() {
        Integer pop = stackA.pop();
        if (!stackB.isEmpty() && pop.equals(stackB.peek())) {
            stackB.pop();
        }
    }

    public int top() {
        return stackA.peek();
    }

    public int min() {
        if (!stackB.isEmpty()) {
            return stackB.peek();
        }
        return -1;
    }
}

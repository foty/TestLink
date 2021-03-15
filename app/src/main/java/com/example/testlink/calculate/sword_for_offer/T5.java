package com.example.testlink.calculate.sword_for_offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Create by lxx
 * Date : 2020/4/10 16:40
 * Use by 从尾到头打印链表
 * 描述: 输入一个链表，从尾到头打印链表每个节点的值。
 */
public class T5 {

    public void solution(ListNode node) {
        /**
         * 从尾到头顺序打印，可借助栈这种数据结构(先进后出)，或者顺序便后后在反转
         */
        ArrayList<Integer> list = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();

        ListNode headNode = node.head;
        //第一遍遍历，加入到栈
        while (headNode != null) {
            stack.push(headNode);
            headNode = headNode.next;
        }
        //遍历到栈
        while (!stack.isEmpty()) {
            list.add(stack.pop().val);
        }

    }
}

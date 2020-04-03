package com.example.testlink.data_structure.stack;

/**
 * Created by lxx on 2019/4/28.
 * Use by
 */

public class LinkedStack {
    public int size;
    private Node head;

    public LinkedStack() {
        head = null;
    }

    /**
     * 元素入栈(使用头插法)
     *
     * @param data
     */
    public void push(String data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * 元素出栈(使用头删法)
     *
     * @return
     */
    public String pop() {
        if (isEmpty()) return null;
        String s = head.data;
        head = head.next;
        size--;
        return s;
    }

    /**
     * 返回栈顶元素
     *
     * @return
     */
    public String peek() {
        if (isEmpty()) return null;
        return head.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static class Node {
        String data;    //以String为例
        Node next;

        public Node(String data) {
            this.data = data;
        }
    }
}

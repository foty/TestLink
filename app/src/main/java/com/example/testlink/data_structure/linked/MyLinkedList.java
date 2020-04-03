package com.example.testlink.data_structure.linked;

import android.util.Log;

/**
 * Created by lxx on 2019/4/19.
 * Use by
 */

public class MyLinkedList {

    private Node headNode;  //链表节点对象
    private int size = 0;   //链表容量大小

    public MyLinkedList() {
        headNode = null;
    }

    public int getSize() {
        return size;
    }

    /**
     * 在链表尾部添加节点
     *
     * @param data
     */
    public void insertAtTail(String data) {
        Node newNode = new Node(data);
        if (size == 0) {
            headNode = newNode;
        } else {
            Node node = headNode;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        size++;
    }

    /**
     * 在链表头部添加节点
     *
     * @param data
     */
    public void insertAtHead(String data) {
        Node newNode = new Node(data);
        newNode.next = headNode;
        headNode = newNode;
        size++;
    }

    /**
     * 指定位置增加节点
     *
     * @param data
     * @param index 要大于0
     */
    public boolean insertAtPosition(String data, int index) {
        if (index < 0) {
            return false;
        }
        Node newNode = new Node(data);
        Node preNode = headNode;
        Node curNode = headNode;
        int curIndex = 1;

        while (curNode != null) {
            if (index == curIndex) {
                preNode.next = newNode;
                newNode.next = curNode;
                size++;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            curIndex++;
        }
        return false;
    }

    /**
     * 删除头节点
     */
    public boolean deleteHead() {
        if (size <= 0) {
            return true;
        }
        Node node = headNode;
        if (node.next != null) {
            headNode = node.next;
            size--;
            return true;
        } else {
            headNode = null;
            size--;
            return true;
        }
    }

    /**
     * 删除指定位置的节点
     *
     * @param index
     */
    public boolean delete(int index) {
        if (index <= 0 || index > size) {
            return false;
        }

        Node preNode = headNode;
        Node curNode = headNode;
        int curIndex = 1;

        while (curNode != null) {
            if (index == curIndex) {
                preNode.next = curNode.next;
                size--;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            curIndex++;
        }
        return false;
    }

    /**
     * 输出节点内容。
     */
    public void out() {
        Node node = headNode;
        int index = 1;
        while (node != null || index <= size) {
            Log.d("out", "outPutLinkedList: " + node.data);
            node = node.next;
            index++;
        }
        Log.d("out", "outPutLinkedList: " + "----------这是一条严谨的分割线--------");
    }

    public static class Node {
        Node next;       //指针域，指向下一个节点
        String data;    //数据域，这里统一数据类型为string

        public Node(String data) {
            this.data = data;
        }
    }

    /**
     * @param node
     */
    public void out(Node node) {
        int index = 1;
        while (node != null || index <= size) {
            Log.d("lxx", "outPutLinkedList: " + node.data);
            node = node.next;
            index++;
        }
        Log.d("lxx", "outPutLinkedList: " + "-------------------这是一条严谨的分割线---------------------");
    }


    /**
     * 链表反转。
     *
     * @return
     */
    public Node reverse() {
        if (size <= 1) {
            return null;
        }

        Node p = null;
        Node n = headNode;
        while (n != null) {
            Log.d("lxx", "reverse: " + n.data);
            Node next = n.next;
            n.next = p;
            p = n;
            if (next == null) {
                return n;
            }
            n = next;
        }
        return null;
    }
}

package com.example.testlink.data_structure.linked;

import android.util.Log;

/**
 * Created by lxx on 2019/4/20.
 * Use by
 */

public class DuplexingLinkedList {

    public static class Node {
        Node next;       //指针域，指向下一个节点
        Node pre;        //指针域，指向上一个节点
        String data;     //数据域，这里统一数据类型为string

        Node(String data) {
            this.data = data;
        }
    }

    private Node headNode;  //头节点
    private Node tailNode;  //尾节点
    private int size;    // 链表容量大小

    public DuplexingLinkedList() {
        headNode = null;
        tailNode = null;
    }

    /**
     * 头插法
     *
     * @param data
     */
    public void insertAtHead(String data) {
        Node newNode = new Node(data);
        if (size == 0) {
            headNode = newNode;
            tailNode = newNode;
        } else {
            newNode.next = headNode;
            headNode.pre = newNode;
            headNode = newNode;
        }
        size++;
    }

    /**
     * 尾插法
     *
     * @param data
     */
    public void insertAtTail(String data) {
        Node newNode = new Node(data);
        if (size == 0) {
            headNode = newNode;
            tailNode = newNode;

        } else {
            tailNode.next = newNode;
            newNode.pre = tailNode;
            tailNode = newNode;
        }
        size++;
    }

    /**
     * @param data
     * @param index 这里定义以1开始，要大于0。
     * @return
     */
    public boolean insertAtPosition(String data, int index) {
        if (index <= 0) {
            return false;
        }
        if (index == 1) {
            insertAtHead(data);
            return true;
        } else if (index == size) {
            insertAtTail(data);
            return true;
        } else {
            //双向链表，可以选择从头节点开始遍历，也可以选择尾节点开始遍历。这里使用尾节点吧，头节点就跟单向链表一致的。
            Node newNode = new Node(data);
            Node c = tailNode; //当前节点。
            int curIndex = size;

            while (c != null) {
                if (index == curIndex) {
                    newNode.pre = c.pre;
                    newNode.next = c;
                    c.pre.next = newNode;
                    c.pre = newNode;
                    size++;
                    return true;
                }
                c = c.pre;
                curIndex--;
            }
        }

        return false;
    }

    /**
     * 删除头节点
     *
     * @return
     */
    public boolean deleteHead() {
        if (size == 0) {
            return false;
        }
        if (headNode.next != null) {
            headNode = headNode.next;
            headNode.pre = null;
            size--;
        } else {
            headNode = null;
            tailNode = null;
            size--;
        }
        return true;
    }

    /**
     * 删除尾节点
     *
     * @return
     */
    public boolean deleteTail() {
        if (size == 0) {
            return false;
        }
        if (tailNode.pre != null) {
            tailNode = tailNode.pre;
            tailNode.next = null;
            size--;
        } else {
            headNode = null;
            tailNode = null;
            size--;
        }
        return true;
    }

    /**
     * 删除指定位置节点
     *
     * @param index
     * @return
     */
    public boolean delete(int index) {
        if (index <= 0) {
            return false;
        }
        if (index == 1) {
            return deleteHead();
        } else if (index == size) {
            return deleteTail();
        } else {
            //倒序遍历删除。
            Node c = tailNode; //当前节点。
            int curIndex = size;

            while (c != null) {
                if (index == curIndex) {
                    c.next.pre = c.pre;
                    c.pre.next = c.next;
                    size--;
                    return true;
                }
                c = c.pre;
                curIndex--;
            }
        }

        return false;
    }


    /**
     * 顺序遍历
     */
    public void out() {
        Node node = headNode;
        int index = 1;
        while (node != null || index <= size) {
            Log.d("xx", "outPutLinkedList: " + node.data);
            node = node.next;
            index++;
        }
        Log.d("xx", "outPutLinkedList: " + "-------------------这是一条严谨的分割线---------------------");
    }

    /**
     * 倒序遍历
     */
    public void outForAfter() {
        Node node = tailNode;
        int index = size;
        while (node != null || index > 0) {
            Log.d("xx", "outPutLinkedList: " + node.data);
            node = node.pre;
            index--;
        }
        Log.d("xx", "outPutLinkedList: " + "-------------------这是一条严谨的分割线---------------------");
    }

}

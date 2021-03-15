package com.example.testlink.calculate.sword_for_offer;

import java.util.HashMap;

/**
 * Create by lxx
 * Date : 2020/6/29 15:07
 * Use by 复制复制链表
 */
public class T26 {
    /**
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，
     * 每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     *
     * 解法： 借助map筛选已经copy过的节点。
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        HashMap<Node, Node> hashMap = new HashMap<>();

        Node newHead = new Node(head.val);
        hashMap.put(head, newHead);

        if (head.random != null) {
            Node node = hashMap.get(head.random);
            if (node == null) { //新的 原链表的随机一个节点
                node = new Node(head.random.val);
                hashMap.put(head.random, node); // 保存
                newHead.random = node;
            } else { //指向自己(hashMap目前只添加了一个元素)
                newHead.random = newHead;
            }
        }

        Node tempNode = newHead;

        while (head.next != null) {
            Node node = hashMap.get(head.next);
            if (node == null) {
                node = new Node(head.next.val);
                hashMap.put(head.next, node);
            }
            tempNode.next = node;
            if (head.next.random != null) {
                Node rNode = hashMap.get(head.next.random);
                if (rNode == null) {
                    rNode = new Node(head.next.random.val);
                    hashMap.put(head.next.random, rNode);
                }
                tempNode.next.random = rNode;
            }

            head = head.next;
            tempNode = tempNode.next;
        }

        /**
         * 1 2 3 4 5 6 7
         *
         */
        return newHead;
    }
}

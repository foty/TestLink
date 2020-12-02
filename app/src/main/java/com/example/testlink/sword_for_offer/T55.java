package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/10/23 16:03
 * Use by 在一个排序的链表中，存在重复的节点，要求删除链表中的重复节点，重复的节点不保留。
 */
public class T55 {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(1);
        ListNode node7 = new ListNode(1);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode newNode = solve(head);

        while (newNode != null) {
            System.out.print(newNode.val + " ");
            newNode = newNode.next;
        }
    }

    public static ListNode solve(ListNode nodeHead) {
        if (nodeHead == null) return null;

        ListNode lastNode = nodeHead;
        ListNode curNode = nodeHead;
        while (curNode != null && curNode.next != null) {

            if (curNode.val == curNode.next.val) {
                //循环删除相同的节点
                while (curNode.next != null && curNode.val == curNode.next.val) {
                    curNode.next = curNode.next.next;
                }
                //删除第一个相同节点
                lastNode.next = curNode.next;
                if (curNode.next != null) {
                    lastNode = curNode.next;
                    curNode = lastNode.next;
                }
            } else {
                lastNode = curNode;
                curNode = curNode.next;
            }
        }
        return nodeHead;
    }
}

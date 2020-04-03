package com.example.testlink.calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/3/23 16:38
 * Use by
 */
public class LinksCalculates {
    public static void main(String[] args) {

    }

    /**
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     */
    public static ListNode problem876(ListNode head) {
        //解法1
//        List<ListNode> list = new ArrayList();
//        ListNode temp = head;
//        int size = 0;
//        while (temp != null) {
//            list.add(temp);
//            temp = temp.next;
//            size++;
//        }
//        int mid = size / 2;
//        return list.get(mid);
//        执行用时 :0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗 :36.5 MB, 在所有 Java 提交中击败了 5.05% 的用户

        //解法2
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        int c = 0;
        temp = head;
        while (c < size / 2) {
            temp = temp.next;
            c++;
        }
        return temp;
//        执行用时 :0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗 :37 MB, 在所有 Java 提交中击败了 5.05% 的用户
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}

package com.example.testlink.calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/3/23 16:38
 * Use by
 */
public class LinksCalculates {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {

    }

    /**
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     */
    public ListNode problem876(ListNode head) {
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

    /**
     * 判断是否为回文链表
     * 思路1：保存链表下来，通过数组或list再处理。
     * 思路2：反转后半链表对比。(注意链表结构被破坏)
     *
     * @param head
     * @return
     */
    public boolean problem234(ListNode head) {
        if (head == null) return true;

        // 1.
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < list.size() / 2; i++) {
            // 这里如果使用 ==，用例 [-129,-129]将不会通过。
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                return false;
            }
        }
//        执行用时：5 ms, 在所有 Java 提交中击败了 11.97% 的用户
//        内存消耗：42 MB, 在所有 Java 提交中击败了 43.42% 的用户


        //2.

        return true;
    }

}

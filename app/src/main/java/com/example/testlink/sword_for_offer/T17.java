package com.example.testlink.sword_for_offer;


/**
 * Create by lxx
 * Date : 2020/5/25 9:39
 * Use by 合并2个有序列表
 */
public class T17 {

    /**
     * 循环访问l1与l2的每一个节点，对比他们的值，值小的节点为合并列表当前的节点，后续使用
     * 此节点的下一个节点与当次比较的另一个节点比较。
     * 注意的是：两个链表的长度可能不一样，最后要判断一次。
     *
     * @param node1
     * @param node2
     */
    private ListNode solve(ListNode node1, ListNode node2) {
        ListNode temp = new ListNode(-1);
        ListNode first = temp;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                temp.next = node1;
                node1 = node1.next;
            } else {
                temp.next = node2;
                node2 = node2.next;
            }
            temp = temp.next;
        }
        //
        temp.next = node1 == null ? node2 : node1;
        return first.next;
    }
}

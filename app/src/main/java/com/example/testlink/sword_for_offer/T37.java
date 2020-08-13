package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/8/12 15:35
 * Use by 两个链表的第一个公共节点
 */
public class T37 {


    /**
     * 思路: 先求出两个链表的长度，让2个链表保持在同一个起跑线上(长的先跑)，最后剩下相同长度互相比较。
     * 误区: 公共交点是指地址值也相同，而不是只是内容相同。
     *
     * @param n1
     * @param n2
     * @return
     */
    public ListNode sovle(ListNode n1, ListNode n2) {
        if (n1 == null || n2 == null) return null;
        ListNode temph1 = n1;
        ListNode temph2 = n2;
        int nc1 = 0, nc2 = 0;

        while (temph1 != null) {
            temph1 = temph1.next;
            nc1++;
        }

        while (temph2 != null) {
            temph2 = temph2.next;
            nc2++;
        }

        if (nc1 > nc2) {
            while (n1 != null) {
                if (nc1 == nc2) {
                    if (n1 == n2) {
                        return n1;
                    }
                    n2 = n2.next;
                } else {
                    nc1--;
                }
                n1 = n1.next;
            }
        } else if (nc1 < nc2) {

            while (n2 != null) {
                if (nc1 == nc2) {
                    if (n1 == n2) {
                        return n1;
                    }
                    n1 = n1.next;
                } else {
                    nc2--;
                }
                n2 = n2.next;
            }
        } else {
            while (n1 != null) {
                if (n1 == n2) {
                    return n1;
                }
                n1 = n1.next;
                n2 = n2.next;
            }
        }
        return null;
    }


    /**
     * 解法2：浪漫解法????。
     * 解法分析： 分别从2个链表的头结点开始比较，每次都指向下一个节点，先到达尾结点的链表则
     * 将下一个节点指向头结点。以此递推，最终会有一次两个链表剩余节点相同。再比较是否相等
     * 即可。注意即时没有相同的节点，因为会存在剩余节点相同，所以最后有null = null。所以不会
     * 存在死循环。(效率比先计算长度，最后让2个链表在剩余相同节点的实现要低些。)
     *
     * @param n1
     * @param n2
     * @return
     */
    public ListNode sovle2(ListNode n1, ListNode n2) {
        if (n1 == null || n2 == null) return null;

        ListNode tn1 = n1;
        ListNode tn2 = n2;

        while (tn1 != tn2) {
            tn1 = tn1 == null ? n1 : tn1.next;
            tn2 = tn2 == null ? n2 : tn2.next;
        }
        return tn1;
    }
}

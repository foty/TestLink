package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/8/14 10:16
 * Use by 二叉树的深度
 */
public class T39 {


    /**
     * 思路: 递归计算。
     *
     * @param root
     * @return
     */

    public int solve(TreeNode root) {

        if (root == null) {
            return 0;
        }
        //思路1
//        countTree(root, 1);
//        return 0;

        //思路2(都是递归。一行代码实现)
        return Math.max(solve(root.left), solve(root.right)) + 1;

        //相比(countTree())之下，一行代码实现只是空间复杂度变低了一点

//        执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：39.8 MB, 在所有 Java 提交中击败了 33.20% 的用户
    }

    public int countTree(TreeNode root, int deep) {
        int left = deep, right = deep;
        if (root.left != null) {
            left = countTree(root.left, ++left);
        }
        if (root.right != null) {
            right = countTree(root.right, ++right);
        }

        return left > right ? left : right;
    }

//    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
//    内存消耗：39.9 MB, 在所有 Java 提交中击败了20.61%的用户
}

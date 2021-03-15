package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/12/3 16:59
 * Use by 对称的二叉树
 * 描述:用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class T57 {

    /**
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * 思路:递归求解。
     */

    public boolean solve(TreeNode root) {
        if (root == null) return true;

        return same(root.left,root.right);
    }

    public boolean same(TreeNode left, TreeNode right) {
        if (left == null && right == null) return  true;
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return same(left.left, right.right) && same(left.right, right.left);
    }
}

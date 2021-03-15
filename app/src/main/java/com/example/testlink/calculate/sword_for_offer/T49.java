package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/9/15 15:19
 * Use by 树中两个节点的最近公共祖先
 * 描述： 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先(一个节点也可以是它自己的祖先).
 * <p>
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class T49 {

    /**
     * 思路分析：这里是求最近公共祖先，我的理解就是从root节点分别到q、p节点路径过程中最后一个
     * 相同的节点。一开始误解了题意“深度尽可能大”以为是要最远的那个，如果是这样那么这个节点必须一定为root，
     * 就没有解答的必要了。后来理解了这个“深度尽可能大”其实就是公共祖先节点距离q、p尽可能近。题意需要注意
     * 的是p、q本身也可以作为祖先节点。
     *
     * 很容易想到的几种特殊的情况就是，
     * 1: p、q节点分别作为祖先节点，则答案为p、q;
     * 2: p、q在分别根节点的左右子树中，答案为根节点(root);
     * 3: p、q分别为某一节点(x)的左右子节点，答案为 x;
     * 4: ....
     *
     * 根据以上情况来看，可以通过对p、q节点在树中位置来求出最近公共节点。使用后续遍历(左-右-根)(递归)的方式
     * 分别找出p、q节点的位置并往上返回p、q，找不到则返回null。当某个节点x的左右俩边结果都不是null时，x就是
     * p、q节点的最近公共祖先。如果最终是根节点下左右节点一边找到了p或q节点，而另一边却是null，那么只有一种可
     * 能，p或q就是祖先节点，返回p或q本身即可。
     *
     * 以根-左-右为一颗数(数的基本组成)来讲最终结果则有下面情况：
     * 1、左边或右边能找到p或q节点(只能找到其中一个节点)；
     * 2、左右2边能找到p、q节点(找齐了2个节点)；
     * 3、左右2边找不到p、q节点(一个节点也找不到)；
     *
     * 上面情况对应的结果就是：
     * 1 = 返回p或q节点(找到了p、q节点，往上返回，用于判断另一个节点的位置)；
     * 2 = 返回根节点(这个根节点其实是当前p、q的父节点。也就是题目答案，因为是递归，所以还要把x节点往上返回。)
     * 3 = null (p、q节点都不在这个树上，返回null)。
     *
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode solve(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) return root;

        TreeNode left = solve(root.left, p, q);
        TreeNode right = solve(root.right, p, q);

        // 左右都为null，p、q不在这个子树上。
        if (left == null && right == null) return null;
        // 一边w为null，只找到一个节点，返回这个节点，用来判断公共祖先节点。
        if (left == null) return right;
        if (right == null) return left;
        // 左右都不为null，那么root就是p、q的最近祖先节点，返回这个节点。
        return root;
    }
}

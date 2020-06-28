package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/6/28 14:13
 * Use by 二叉树中和为某一值的路径
 */
public class T25 {

    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     * <p>
     * 思路: 遍历 + 回溯(节点路径回退)。
     */

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> tempList = new ArrayList<>();
//    int sums;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        solve(root, sum);
        return result;
    }

    private void solve(TreeNode root, int sum) {
        if (root == null) return;
//        sums = sums + root.val;
        tempList.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            result.add(new ArrayList(tempList));
        } else {
            pathSum(root.left, sum - root.val);
            pathSum(root.right, sum - root.val);
        }
        //保存数据回溯。
//        sums -= tempList.get(tempList.size() - 1);
        tempList.remove(tempList.size() - 1);

    }


    //拓展：给定一个二叉树与一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条节点上的所有路径值和等于
    //目标值。

    private List<Integer> list = new ArrayList<>();

    private boolean solve2(TreeNode root, int target) {
        if (root == null) return false;
        list.add(root.val);
        if (target == root.val && root.left == null && root.right == null) {
            return true;
        }
        if (solve2(root.left, target - root.val)) {
            return true;
        }
        if (solve2(root.right, target - root.val)) {
            return true;
        }

        list.remove(list.size() - 1);

        return false;
    }
}

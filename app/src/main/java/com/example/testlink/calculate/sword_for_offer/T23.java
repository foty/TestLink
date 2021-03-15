package com.example.testlink.calculate.sword_for_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by lxx
 * Date : 2020/6/15 11:31
 * Use by 层序遍历二叉树(广度优先搜索 bfs)
 */
public class T23 {

    /**
     * 对树的层序遍历，其实就是广度优先搜索(BFS)。此外前序，中序，后续遍历是属于深度优先搜索(dfs)范畴。
     * 曾经尝试直接遍历，将每层节点遍历(没每到访问节点，就连同它的左右节点一起访问了)。但是后来发现，一旦树的深度
     * 超过了2，即根节点和它的左右连个节点外，便无法再访问到其他节点的子节点了。如果使用递归就变成了dfs。
     *
     * 解决: 引入队列这种结构(先进先出)。原理:将跟节点入队列。队列随后进入循环当中，每次从队列取出一个节点访问，同
     * 时将该节点的左右节点入队(如果存在的话)。以根节点为例(根节点为1，左右节点分别为2,3)，它的入队序列是:1->2->3。
     * 在访问节点1时，2、3以入队。下一个访问的是2，则2的左右节点入队(假如是3、4);访问3时，它的左右节点入队(假设是5、6)。
     * 此时入队序列为 1-> 2->3-> 4->5->6->7。分三次入队。很明显这三次分别是每一层次的所有节点。符合bfs。
     *
     * @param root
     * @return
     */
    public int[] solve(TreeNode root) {

        if (root == null) {
            return new int[0];
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();//remove与poll的区别是 如果队列为空poll不会抛出null。
            list.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        int[] ins = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ins[i] = list.get(i);
        }

        return ins;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        test(root);
    }

    private static int[] test(TreeNode root) {
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};

        System.out.println(queue.size());

        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            ans.add(node.val);
            System.out.println("  " + node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}

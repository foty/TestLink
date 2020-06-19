package com.example.testlink.sword_for_offer;

import java.util.Arrays;


/**
 * Create by lxx
 * Date : 2020/6/17 14:54
 * Use by 判断是否是二叉搜索树的后续遍历结果。假设输入的数组的任意两个数字都互不相同。
 */
public class T24 {


    public static void main(String[] args) {
        int[] ins = new int[]{1, 2, 5, 10, 6, 9, 4};
        solve(ins);
    }

    /**
     * 不断切割左右子树，判断有效性。
     * <p>
     * 找到左子树序列(全部小于根节点)，判断右子树序列(全部大于根节点)。
     * 递归剩下序列。
     *
     * @param postorder
     * @return
     */
    public static boolean solve(int[] postorder) {

        /**
         * [1,2,5,10,6,9,4,3]
         *
         *             3
         *          2      4
         *       1            6
         *                5      9
         *                         10
         *
         *  [4, 8, 6, 12, 16, 14, 10]
         *
         *       10
         *    6      14
         * 4    8  12    16
         *
         *
         */
        if (postorder == null || postorder.length <= 0) {
            return false;
        }
        System.out.print("[");
        for (int i = 0; i < postorder.length; i++) {

            System.out.print(postorder[i]);
            System.out.print(" ");
        }
        System.out.print("]");
        System.out.println();


        int partIndex = 0;
        int rootIndex = postorder.length - 1;//后续遍历的最后一个是子树的根节点。
        //找到左子树。
        for (int i = 0; i < rootIndex; i++) {
            if (postorder[i] < postorder[rootIndex]) {
                partIndex++;
            }
        }
        //处理一个特殊情况(没有左子树，partIndex = 0)
        if (partIndex == 0) {
            //递归右边子树
            solve(Arrays.copyOfRange(postorder, 0, rootIndex));
            return true;
        }
        //找到右子树
        for (int i = partIndex; i < rootIndex; i++) {
            //题意说了没有相同元素，右子树所有节点一定大于根节点,否则不是后序遍历序列。
            if (postorder[i] <= postorder[rootIndex]) {
                return false;
            }
        }
        boolean b1 = solve(Arrays.copyOfRange(postorder, 0, partIndex));
        boolean b2 = solve(Arrays.copyOfRange(postorder, partIndex, rootIndex));
        return b1 && b2;
    }
}

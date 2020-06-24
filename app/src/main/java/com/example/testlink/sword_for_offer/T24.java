package com.example.testlink.sword_for_offer;

import java.util.Arrays;


/**
 * Create by lxx
 * Date : 2020/6/17 14:54
 * Use by 判断是否是二叉搜索树的后续遍历结果。假设输入的数组的任意两个数字都互不相同。
 */
public class T24 {


    public static void main(String[] args) {
        int[] ins = new int[]{4, 8, 6, 12, 16, 14, 10};
        System.out.println(solve(ins));
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
         *  [4, 8, 6, 12, 16, 14, 10]

         */
        if (postorder == null) return false;
        if (postorder.length <= 0) return true;


//        return relSolve1(postorder);
        return relSolve2(postorder, 0, postorder.length - 1);
    }

    /**
     * 使用复制数组法
     *
     * @param postorder
     * @return
     */
    private static boolean relSolve1(int[] postorder) {
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
            int[] ints = Arrays.copyOfRange(postorder, 0, rootIndex);
            if (ints == null || ints.length <= 0) {
                return true;
            } else {
                return relSolve1(ints);
            }
        }
        //判断右子树
        for (int i = partIndex; i < rootIndex; i++) {
            //题意说了没有相同元素，右子树所有节点一定大于根节点,否则不是后序遍历序列。
            if (postorder[i] <= postorder[rootIndex]) {
                return false;
            }
        }
        int[] ints = Arrays.copyOfRange(postorder, 0, partIndex);
        int[] ints1 = Arrays.copyOfRange(postorder, partIndex, rootIndex);
        boolean b1;
        boolean b2;
        if (ints == null || ints.length <= 0) {
            b1 = true;
        } else {
            b1 = relSolve1(ints);
        }
        if (ints1 == null || ints1.length <= 0) {
            b2 = true;
        } else {
            b2 = relSolve1(ints1);
        }

        return b1 && b2;
    }

    /**
     * 使用边界法
     *
     * @param postorder
     * @return
     */
    private static boolean relSolve2(int[] postorder, int start, int end) {
        int partIndex = 0;
        //找到左子树。
        for (int i = start; i < end; i++) {
            if (postorder[i] < postorder[end]) {
                partIndex++;
            }
        }
        //处理一个特殊情况(没有左子树，partIndex = 0)
        if (partIndex == 0) {
            //递归右边子树
            if (start >= end) {
                return true;
            } else {
                return relSolve2(postorder, start, end - 1);
            }
        }
        //判断右子树
        for (int i = start + partIndex; i < end; i++) {
            //题意说了没有相同元素，右子树所有节点一定大于根节点,否则不是后序遍历序列。
            if (postorder[i] <= postorder[end]) {
                return false;
            }
        }
        boolean b1;
        boolean b2;
        if (partIndex == 1) {
            b1 = true;
        } else {
            b1 = relSolve2(postorder, start, start + partIndex -1);
        }

        if (start + partIndex >= end -1) {
            b2 = true;
        } else {
            System.out.println("递归右子树");
            b2 = relSolve2(postorder, start + partIndex, end - 1);
        }
        return b1 && b2;
    }
}

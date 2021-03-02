package com.example.testlink.dailyari;

class NumMatrix {

    /**
     * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。与
     * 前一题类似，但是这是一个二维数组。
     */

    int[][] mixatr;

    public NumMatrix(int[][] matrix) {
        mixatr = matrix;
    }

    /**
     * 最粗暴的方式。
     *
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (mixatr.length == 0) return 0;
        int sum = 0;
        for (int i = row1; i <= row2 && row2 < mixatr.length; i++) {
            for (int j = col1; j <= col2 && col2 < mixatr[0].length; j++) {
                sum += mixatr[i][j];
            }
        }
        System.out.println(sum);
        return sum;
    }

//    执行用时：165 ms, 在所有 Java 提交中击败了5.35%的用户
//    内存消耗：44.1 MB, 在所有 Java 提交中击败了51.55%的用户
}
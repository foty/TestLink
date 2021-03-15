package com.example.testlink.calculate.dailyari;

class NumMatrix {

    /**
     * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。与
     * 前一题类似，但是这是一个二维数组。
     */

    int[][] mixatr;

    int[][] mixSum; // 二位数组和

    public NumMatrix(int[][] matrix) {

        if (matrix.length == 0) return;
        mixatr = matrix;

        mixSum = new int[matrix.length + 1][matrix[0].length + 1];
        handSum(matrix);
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
        //    执行用时：165 ms, 在所有 Java 提交中击败了5.35%的用户
//    内存消耗：44.1 MB, 在所有 Java 提交中击败了51.55%的用户
    }


    public void handSum(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                mixSum[i + 1][j + 1] = mixSum[i - 1 + 1][j + 1] + mixSum[i + 1][j - 1 + 1]
                        - mixSum[i - 1 + 1][j - 1 + 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        if (mixSum == null) return 0;
        row1++;
        row2++;
        col1++;
        col2++;
        return mixSum[row2][col2] - mixSum[row1 - 1][col2] - mixSum[row2][col1 - 1] + mixSum[row1 - 1][col1 - 1];

        //这样内存效率变高了？
//        return mixSum[row2+1][col2+1] - mixSum[row1][col2+1] - mixSum[row2+1][col1] + mixSum[row1][col1];
//        执行用时：18 ms, 在所有 Java 提交中击败了 27.47% 的用户
//        内存消耗：43.7 MB, 在所有 Java 提交中击败了 96.79% 的用户
    }

//    执行用时：15 ms, 在所有 Java 提交中击败了60.40%的用户
//    内存消耗：44.3 MB, 在所有 Java 提交中击败了12.86%的用户
}
package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/6/3 14:30
 * Use by 顺时针打印矩阵
 */
public class T20 {

    public int[] spiralOrder(int[][] matrix) {

        int[] result = new int[matrix.length * matrix[0].length];

        //行
        int maxR = matrix[0].length - 1;
        int r = 0;
        //列
        int maxC = matrix.length - 1;
        int c = 0;

        int index = 0;

        while (r < maxR && c < maxC) {

            //从左到右
            for (int i = r; i <= maxR; i++) {
                result[index] = matrix[r][i];
                index++;
            }
            //从上到下
            c++;
            for (int i = c; i <= maxC; i++) {
                result[index] = matrix[r][c];
                index++;
            }
            //从右到左
            c--;
            for (int i = c; i >= 0; i--) {
                result[index] = matrix[r][c];
                index++;
            }
            //从下到上
            r--;
            for (int i = r; i <= 0; i--) {
                result[index] = matrix[r][c];
                index++;
            }

            r++;
            c++;
            maxR--;
            maxC--;
        }

        return result;
    }
}

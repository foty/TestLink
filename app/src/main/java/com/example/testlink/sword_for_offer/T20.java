package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/6/3 14:30
 * Use by 顺时针打印矩阵
 */
public class T20 {


    /**
     * 逐层遍历。记住终止行号始终大于开始行号，终止列号始终大于开始列号。以此作为循环的满足条件即可。
     * 注意的是，除了左->右,其他三种情况都需要排除一个已经被添加过的数(下->上是最后一个，其他是第一个)。
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int[] result = new int[matrix.length * matrix[0].length];

        //行
        int maxR = matrix[0].length - 1;
        int r = 0;
        //列
        int maxC = matrix.length - 1;
        int c = 0;

        int index = 0;
        while (r <= maxR && c <= maxC) {

            //从左到右(可以从开始直接添加到最后的一个值)
            for (int i = r; i <= maxR; i++) {
                result[index] = matrix[c][i];
                index++;
            }

            //从上到下(这一列第一个已经添加过，需要排除)
            for (int i = c + 1; i <= maxC; i++) {
                result[index] = matrix[i][maxR];
                index++;
            }

            //防止重复添加。
            if (r < maxR && c < maxC) {

                //从右到左(排除第一个)
                for (int i = maxR - 1; i > r; i--) {
                    result[index] = matrix[maxC][i];
                    index++;
                }

                //从下到上(排除最后一个)
                for (int i = maxC; i > c; i--) {
                    result[index] = matrix[i][r];
                    index++;
                }
            }

            r++;
            c++;
            maxR--;
            maxC--;
        }
        return result;
    }


    /**
     * 解法二。
     * @param matrix
     * @return
     */
    private int[] solve2(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int l = 0, r = matrix[0].length - 1;
        int t = 0, b = matrix.length - 1;
        int x = 0;
        int[] res = new int[(r + 1) * (b + 1)];

        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;
    }
}

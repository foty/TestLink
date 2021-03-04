package com.example.testlink.dailyari;

/**
 * Create by lxx
 * Date : 2021/3/1 10:50
 * Use by
 */
public class Topics {

    public static void main(String[] args) {

        test304();
    }


    /**
     * 编号303
     */
    public static void test303() {
        int[] ints = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(ints);

        int s = numArray.sumRange2(0, 2);
        System.out.println(s);
        int s1 = numArray.sumRange2(2, 5);
        System.out.println(s1);
        int s2 = numArray.sumRange2(0, 5);
        System.out.println(s2);
    }


    public static void test304() {
        int[][] ints = new int[][]{{-4, -5}};
        NumMatrix numMatrix = new NumMatrix(ints);
//        numMatrix.sumRegion(0, 0, 0, 0);
//        numMatrix.sumRegion(0, 0, 0, 1);
        numMatrix.sumRegion(0, 1, 0, 1);
        int s = numMatrix.sumRegion2(0, 1, 0, 1);
        System.out.println(s);
    }

    /**
     * 338.比特位计数
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        /**
         * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
         */
        int[] result = new int[num + 1];
        result[0] = 0;
        int height = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) { // 表示为2的整数次幂，也称之为最高位
                height = i;
            }
            result[i] = result[i - height] + 1;
        }
        return result;

        //    执行用时：2 ms, 在所有 Java 提交中击败了60.00%的用户
        //    内存消耗：42.4 MB, 在所有 Java 提交中击败了80.58%的用户
    }


    /**
     * 354、俄罗斯信封套娃
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        /**
         * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进
         * 另一个信封里，如同俄罗斯套娃一样。
         * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
         *
         * 说明:
         * 不允许旋转信封。
         * 示例:
         *
         * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
         * 输出: 3
         * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
         */

        return 0;
    }
}

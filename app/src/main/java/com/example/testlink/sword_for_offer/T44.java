package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/8/26 10:31
 * Use by n个筛子的点数
 * 描述： 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 */
public class T44 {

    public static void main(String[] args) {
        double[] solve = solve(2);
        double[] solve2 = solve2(2);


        for (double d : solve) {
            System.out.println(d);
        }
        System.out.println("--");

        for (double d : solve2) {
            System.out.println(d);
        }
    }

    /**
     *
     * 思路: 数组内的概率 = 出现和为S/总次数。
     * 先求出n个筛子能抛出和s的总次数，然后除以总的次数。
     *
     * 总的次数很好算，一个筛子是6种，2个筛子一共能出现36种，所以n个就是 6的n次方种。然后分别求出现和为s的次
     * 数。这里需要用到动态规划的技巧(dp)。
     *
     * 分析：
     *
     * 只看第n个筛子，它能扔出的点数有1,2,3,..6。扔完n枚筛子后点数和j出现的次数可以由扔完前n-1个筛子和
     * 加上第n筛子扔出的点数后转化过来。例如：第n个筛子扔出点数为1，那么前n-1个筛子的点数和为j-1；第n个筛
     * 子扔出点数为2，那么前n-1个筛子的点数和为j-2，以此类推。最后有：
     * n枚筛子后点数和j出现的次数 = (j-1)+(j-2)+(j-3)+(j-4)+(j-5)+(j-6)
     *
     * dp[n][s]表示扔n个筛子和为s出现的次数：
     *
     *            | n = 1: dp[n][s] = 1。(全部只出现一次，s的取值有[1,2,3,4,5,6])
     * dp[n][s] = |
     *            | n >= 2: dp[n][s] = dp[n-1][s-1] + dp[n-1][s-2]+...+dp[n-1][s-6]。
     *
     *
     * 动态规划的低版(使用备忘录的暴力解法)
     *
     * @param n
     * @return
     */
    public static double[] solve(int n) {

        int[][] dp = new int[n + 1][6 * n + 1]; //以 dp[1][1]为开头而不是dp[0][0]，方便使用下标做计算
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {

            for (int j = i; j <= n * 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j - k < i - 1) break;//n个筛子最小值都是等于n,前n个筛子的最小值不可能小于n-1
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        // 所有可能的和s出现次数
        int all = (int) Math.pow(6, n);
        // 概率 = 出现和为s的次数/总次数
        double[] result = new double[5 * n + 1];
        for (int i = n; i < dp[n].length; i++) {
            result[i - n] = (dp[n][i] * 1.00) / all;  // * 1.00转换成double值
        }
        return result;
    }

//    执行用时：1 ms, 在所有 Java 提交中击败了21.82%的用户
//    内存消耗：38 MB, 在所有 Java 提交中击败了17.56%的用户


    /**
     * 优化空间的更优解法。
     *
     * <p>
     * 实际上每次计算只跟上一次的结果有关系，在第一种解法基础上，没有必要将每n次的结果都保存起来。只需要保存上一
     * 次结果即可。这里可以使用一个额外数组操作。但是肯定是存在瑕疵的。同样的使用同一个数组，从尾到头开始计算就
     * 可以多节省出一个数组的空间。
     * 为什么从尾到头结果还是能正确呢？拿第二次扔的结果来说。最后一种出现的结果是12.要计算和为12的次数。根据
     * 程序逻辑最后一次能出现的结果有1...6。很明显当最后一次扔出1时，此时无解概率为0。因为上一次(1个筛子)无法扔
     * 出和为11的结果。这种情况即为:dp[12]=dp[11] + 1。而dp[11]未被赋值过而等于0。与实际情况符合。同样
     * dp[12]=dp[10]+2,dp[9]+3...dp[6]+6，也符合实际情况。再到dp[11] = dp[10] +1...dp[5]+6。也是跟求和
     * 为12的情况一样。就可以发现，要求和为s出现的次数只跟数组dp[s]前面的数有关。因此将数组从尾到头开始遍历求得结果
     * 一样是正确的。有一点要注意的是，当dp[j]有值的时候，因为要求和，需要先初始化。因为求dp[j]的值与dp[j]当前有没有
     * 值是没有关系的。
     *
     * @param n
     * @return
     */
    public static double[] solve2(int n) {
        int[] dp = new int[6 * n + 1]; //以 dp[1][1]为开头
        for (int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = n * 6; j >= i; j--) {
                //初始化值，因为使用同一个数组，前面的dp[j]可能保存有值，需要初始化
                dp[j] = 0;
                for (int k = 1; k <= 6; k++) {
                    if (j - k < i - 1) break; // j-k: 减去最后一个筛子扔出的k点；
                    // 前n-1个筛子扔出的和不能小于前n-1个筛子的最小和，也就是i-1。
                    dp[j] += dp[j - k];
                }
            }
        }

        // 所有可能的和s出现次数
        int all = (int) Math.pow(6, n);
        // 概率 = 出现和为s的次数/总次数
        double[] result = new double[5 * n + 1];
        for (int i = n; i < dp.length; i++) {
            result[i - n] = (dp[i] * 1.00) / all;
        }
        return result;
    }

//    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
//    内存消耗：37.8 MB, 在所有 Java 提交中击败了58.52%的用户
}

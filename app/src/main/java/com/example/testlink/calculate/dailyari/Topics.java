package com.example.testlink.calculate.dailyari;

import com.example.testlink.calculate.sword_for_offer.ListNode;
import com.example.testlink.calculate.sword_for_offer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Create by lxx
 * Date : 2021/3/1 10:50
 * Use by
 */
public class Topics {

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 4, 7, 8};
        //numWays(3, 2);
//        System.out.println(Math.sqrt(16) % 2 == 0);
//        System.out.println(1 & 0);

        profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3});
    }


    /**
     * 303、数组区域和
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

    /**
     * 304、二位数组区域和
     */
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
     */
    public int[] countBits(int num) {
        /**
         * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
         */

        /**
         * 思路：最高有效位解法。
         * 我们知道整数的二进制都是0101这样的。比如 0 = 0000,1 = 0001,2 = 0010。3 = 0011。下面将在二进制中最高位数字为1，剩下
         * 所有的位数都是为0的数，比如2(0010),4(0100),8(1000)这样的数称为最高有效位数而二进制中1所在位置称为最高有效位。每个数都有一
         * 个相对应的最高有效位数。比如2,4,8这些数的最高位有效数就是2,4,8自己本身。其他数如3(0011),5(0101),9(1001),10(1010)这些
         * 数的最高有效位数则是2(0010),4(0100),8(1000),8(1000)。最高有效位数都是2的整次幂数。
         *
         * 下面用b(x)表示数字x的二进制中1的数量，b(y)表示数字y的二进制中1的数量。如果b(y)始终比b(x)多一个1，就有：
         * b(y) = b(x)+ 1。对于正整数i，如果已知i的最高位有效数为j。那么俩数差k = i - j。对于他们二进制1的数量其实有：
         * b(k) = b(i) - b(j)。因为最高有效位数二进制中1的数量都是1(如2(0010),4(0100),8(1000))，b(j) = 1。那么 对于上面等式
         * b(i) = b(k) + 1(其中b(k) = b(i - j),j为i的最高位有效数)。那么如何求的最高位有效数，也就是2的整数次幂的数呢。有一
         * 个位运算规律是 x&(x-1)会将x最后一个1消除为0，对于最高位数j来说，j&(j-1) == 0。可用此作为依据。
         */

        int[] result = new int[num + 1];
        result[0] = 0;
        int height = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) { // 表示为2的整数次幂，也称之为当前数的最高位数
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

        if (envelopes.length == 0) return 0;
        int n = envelopes.length;
        // 固定一边升序排序，另外一边降序(可以保证在固定边相同的情况下，不会重复统计到)
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        int[] f = new int[n]; // 记录当前位置最多信封数量
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {// 表示可以套娃
                    f[i] = Math.max(f[i], f[j] + 1); //
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;

//        执行用时：266 ms, 在所有 Java 提交中击败了 31.80% 的用户
//        内存消耗：39.3 MB, 在所有 Java 提交中击败了 78.40% 的用户

    }

    /**
     * 232、用栈实现队列
     */
    public void test232() {
        MyQueue queue = new MyQueue();
    }

    /**
     * 503 下一个更大的数。
     */
    public static int[] nextGreaterElements(int[] nums) {
        // 暴力循环
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
//        for (int i = 0; i < nums.length; i++) {
//            int j;
//            if (i == nums.length - 1) {
//                j = 0;
//            } else {
//                j = i + 1;
//            }
//            while (j != i) {
//                if (nums[i] < nums[j]) {
//                    result[i] = nums[j];
//                    break;
//                }
//                j++;
//                if (j == nums.length)
//                    j = 0;
//            }
//        }
//        return result;
        //    执行用时：52 ms, 在所有 Java 提交中击败了8.52%的用户
        //    内存消耗：39.1 MB, 在所有 Java 提交中击败了99.64%的用户

        // 解法2 单调栈。
        /**
         * 思路分析：
         * 由解法1(暴力循环)可以得知，每次要确定下一个更大的数都要走一次遍历。其实这里是做了重复工作的。
         * 比如[3,2,1]。当去计算2、1时，其实重复遍历了。这里可以使用单调栈，对于单调递减的数，如[2,1],可以把它
         * 们先保存起来，待遇到比栈顶的更大的数时，将它们一一出栈。这个数就是对应的下一个更大的数。这里有2点要注意：
         * 1、为了保证最后一个数字，暴力法是将下标指针指向0.有种办法是拼接一段相同的数组(除了最后一个元素)。这样达到一个
         * 循环的效果。
         * 2、栈保存的是元素对应的下标。很显然，如果栈内存的数是数组的元素，那么这将更难定位到这个元素的下标。
         */

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length * 2 - 1; i++) {
            while (stack.isEmpty() && nums[i % nums.length] > nums[stack.peek()]) { //替换
                result[stack.pop()] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }
        return result;

//        执行用时：11 ms, 在所有 Java 提交中击败了 57.03% 的用户
//        内存消耗：40.1 MB, 在所有 Java 提交中击败了 47.19% 的用户
    }

    /**
     * 131、分割回文串I ?
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        /**
         *
         */
        return ret;
    }

    /**
     * 132、分割回文串II ?
     */
    public int minCut(String s) {

        return 0;
    }

    /**
     * 1047、删除字符串所有相邻重复项 ?
     */
    public static String removeDuplicates(String S) {
        int[] strIndex = new int[S.length()];
        for (int i = 0, j = i + 1; i < S.length() && j < S.length(); ) {
            if (S.charAt(i) == S.charAt(j)) {
                i--;
                if (i <= 0) i = 0;
            } else {
                i++;
            }
            j++;
            strIndex[0] = j;
        }

        return "";
    }

    /**
     * 224、基本计算器 ?
     */
    public int calculate(String s) {
        /**
         * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
         * 示例 1：
         * 输入：s = "1 + 1"
         * 输出：2
         * 示例 2：
         * 输入：s = " 2-1 + 2 "
         * 输出：3
         * 示例 3：
         * 输入：s = "(1+(4+5+2)-3)+(6+8)"
         * 输出：23
         * <p>
         *
         * 提示：
         * 1 <= s.length <= 3 * 105
         * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
         * s 表示一个有效的表达式
         */


        return 0;
    }

    /**
     * 227、基本计算器II ?
     */
    public int calculate2(String s) {
        /**
         * 提示：
         *
         * 1 <= s.length <= 3 * 105
         * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
         * s 表示一个 有效表达式
         * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
         * 题目数据保证答案是一个 32-bit 整数
         */
        return 0;
    }

    /**
     * 331、验证二叉树的前序序列化 ?
     */
    public boolean isValidSerialization(String preorder) {
        return false;
    }

    /**
     * 705、设计hash集合
     */
    public void test705() {
        MyHashSet set = new MyHashSet();
    }

    /**
     * 706、设计哈希映射
     */
    public void test706() {
        MyHashMap map = new MyHashMap();
    }

    /**
     * 54、螺旋矩阵
     */
    public static List<Integer> test54(int[][] matrix) {
        /**
         * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
         */

        /**
         * 思路：
         * 不需要特别的知识点。难点是矩阵的边界变化,拿出纸笔画出图看更明显。使用4个指针，最小行，最小列，最大行，最大列划分遍
         * 历边界即可。分别从左到右，从上到下，从右到左，从下到上遍历。其中最小(行。列)始终是要小于(或等于)最大(行、列)的。
         * 可以根据这个作为跳出循环的条件。等于的情况看如何变化4个指针。如果每次一个方向遍历完了，相对应的指针就加减，那么
         * 就需要划等号，保证能够访问完整。
         *
         */

        List<Integer> list = new ArrayList<>();
        if (matrix.length <= 0) return list;

        //最大行数
        int maxRow = matrix.length - 1;
        // 最大列数
        int maxColumn = matrix[0].length - 1;

        //最小行数
        int cRow = 0;
        //最小列数
        int cColumn = 0;

        while (true) {

            // 从左到右,行不变，列变化(变大)。
            for (int i = cColumn; i <= maxColumn; i++) {
                list.add(matrix[cRow][i]);
            }

            //从上到下，列不变，行变化(变大)。但这行开始位置已经被访问了，要排除，行数初始值+1。
            cRow++;
            if (cRow > maxRow) break;

            for (int i = cRow; i <= maxRow; i++) {
                list.add(matrix[i][maxColumn]);
            }

            // 从右到左，行不变，列变化(变小)。同样这列开始位置被访问过，要排除，列数最大值-1.
            maxColumn--;
            if (cColumn > maxColumn) break;

            for (int i = maxColumn; i >= cColumn; i--) {
                list.add(matrix[maxRow][i]);
            }

            // 从下到上，列不变，行变化(变小)，同样注意排除被访问过的位置
            maxRow--;
            if (cRow > maxRow) break;

            for (int i = maxRow; i >= cRow; i--) {
                list.add(matrix[i][cColumn]);
            }

            //准备下一次循环，从左到右，排除一个初始值
            cColumn++;
            if (cColumn > maxColumn) break;

        }
        return list;

//        执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：36.6 MB, 在所有 Java 提交中击败了 48.94% 的用户
    }

    /**
     * 59、螺旋矩阵II
     */
    public static int[][] generateMatrix(int n) {

        /**
         * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
         * 如：
         * 输入：n = 3
         * 输出：[[1,2,3],[8,9,4],[7,6,5]]
         * 提示：
         * 1 <= n <= 20
         */

        /**
         * 解法参考上一题的螺旋矩阵。解法通用，只是结果要使用二维数组保存
         */

        int[][] matrix = new int[n][n];

        int maxRow = matrix.length - 1;
        int maxColumn = matrix[0].length - 1;

        int cRow = 0;
        int cColumn = 0;

        int value = 1;

        while (true) {

            for (int i = cColumn; i <= maxColumn; i++) {
                matrix[cRow][i] = value;
                value++;
            }

            //从上到下，列不变，行变化(变大)。但这行开始位置已经被访问了，要排除，行数初始值+1。
            cRow++;
            if (cRow > maxRow) break;

            for (int i = cRow; i <= maxRow; i++) {
                matrix[i][maxColumn] = value;
                value++;
            }

            maxColumn--;
            if (cColumn > maxColumn) break;

            for (int i = maxColumn; i >= cColumn; i--) {
                matrix[maxRow][i] = value;
                value++;
            }

            maxRow--;
            if (cRow > maxRow) break;

            for (int i = maxRow; i >= cRow; i--) {
                matrix[i][cColumn] = value;
                value++;
            }

            cColumn++;
            if (cColumn > maxColumn) break;

        }

        return matrix;

//        执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：36.6 MB, 在所有 Java 提交中击败了 47.99% 的用户

    }

    /**
     * 115、不同的子序列 ?
     */
    public static void numDistinct(String s, String t) {

        /**
         * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符
         * 且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
         *
         * 提示：
         * 0 <= s.length, t.length <= 1000
         * s 和 t 由英文字母组成
         * 题目数据保证答案符合 32 位带符号整数范围。
         *
         * 示例 1：
         * 输入：s = "rabbbit", t = "rabbit"
         * 输出：3
         * 解释：
         * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
         * (上箭头符号 ^ 表示选取的字母)
         * rabbbit
         * ^^^^ ^^
         * rabbbit
         * ^^ ^^^^
         * rabbbit
         * ^^^ ^^^
         */

        // 1、递归耗时，但是没有AC，感觉代码是对的。0.0
        p115Find(s, 0, t, 0);


        // 2、动态规划
        /**
         * 解法分析
         * 动态规划的解法步骤一般是明确dp组的含义，寻找状态转移方程。
         * 先明确dp数组的含义，定义出dp组。根据题意是要统计在字符串s中找出能组成字符串s的子串数量。比如在“rabbbit”找 “rabbit”。
         * 它可以是(下标)[01256][013456][01456]3种情况。前面用递归写可以知道，当确定了t的“rabb”在s的数量后，再去确定“it”的数
         * 量即可得到答案。也就是说，可以先确定t中的一部分，求另外一部分解，可以得到答案，也就是分解问题。所以对于t，用t[i]表示t中
         * 的从下标为0到i的字符串在s中出现的数量。同样的对于s，用s[i]表示在s中从下标为0到i的字符串能找出等于字符串t的子串的数量。
         * 题目可以转换成在s[i]找出等于t[j]的子串数量，当i = s.length(),j=j.length()时，答案为题解。分解过后就可以定义出dp数组了。
         * 下面用二维数组dp[i][j]表示在s[i]找出等于t[j]的子串数量。
         *
         * 以“rabbbit”找 “rabbit”为例子，画出表格。中间数量表示在s[i]找出等于t[j]的子串数量,s[i]为行，t[j]为列。
         *
         *    r a b b b i t
         *  r 1 1 1 1 1 1 1
         *  a 0 1 1 1 1 1 1
         *  b 0 0 1 2 3 3 3
         *  b 0 0 0 1 3 2 2
         *  i 0 0 0 0 0 3 3
         *  t 0 0 0 0 0 0 3
         *
         *  同样的对于“babgbag”  “bag”
         *
         *    b a b g b a g
         *  b 1 1 2 2 3 3 3
         *  a 0 1 1 1 1 3 3
         *  g 0 0 0 1 1 1 5
         *
         * 边界问题：s或t为空串时。
         */

        int[][] result = new int[0][0];

        for (int i = 0; i < t.length(); i++) {
            for (int j = 0; j < s.length(); j++) {


            }
        }

    }

    private static int sum = 0;

    private static boolean p115Find(String s, int p, String t, int q) {

        // 越界条件
        if (p >= s.length()) return false;
        if (q >= t.length()) return false;

        boolean last = false;

        boolean all = false;

        //当前字符匹配
        if (s.charAt(p) == t.charAt(q)) {
            //完全匹配
            if (q == t.length() - 1) {
                sum++;
                all = true;
            }
            last = true;
            q++;
        }

        //完全匹配了,继续找还有没有这个。
        if (all) {
            p115Find(s, p + 1, t, q - 1);
            return true;

        } else {// 匹配下一个字符
            boolean b = p115Find(s, p + 1, t, q);

            if (b && last) {
                p115Find(s, p + 1, t, q - 1);
            }
            return b;
        }

    }

    /**
     * 92、反转链表II
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;

        // 构造一个虚拟头结点,这么做是为了防止头结点被改变了。
        ListNode first = new ListNode(-1);
        first.next = head;

        ListNode pre = first;
        ListNode leftNode;
        ListNode after;
        ListNode rightNode;

        // 找到left位置
        for (int i = 1; i <= left - 1; i++) {
            pre = pre.next;
        }

        leftNode = pre.next;

        rightNode = leftNode;
        //找到right位置
        for (int i = 1; i <= right - left; i++) {
            rightNode = rightNode.next;
        }

        after = rightNode.next;

        //分割出需要反转的链表
        pre.next = null;
        rightNode.next = null;

        //反转
        ListNode pre1 = leftNode;
        ListNode cur = leftNode.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre1;
            pre1 = cur;
            cur = next;
        }

        // 拼回来
        pre.next = rightNode;
        leftNode.next = after;

        return first.next;
    }

    /**
     * 1603、设计一个停车系统
     */
    public void test1063() {
        ParkingSystem system = new ParkingSystem(1, 1, 1);
    }

    /**
     * 150、逆波兰表达式求值
     */
    public static int evalRPN(String[] tokens) {

        /**
         * 逆波兰表达式的特点是：没有括号，运算符总是放在和它相关的操作数之后。因此，逆波兰表达式也称后缀表达式。
         * 比如 ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]，
         * 从最开始10,6,9都没有碰到符号，到3之后有一个+号，表示符号的前2个数做加法，所以到+号，换算成平常熟悉的中缀表达式就是
         * [10 ? 6 ? (9+3)],?表示暂定。
         * 继续往后，-11，*号。-11要与它的前一个数相乘，按照从左到右的顺序，与-11相乘的数是
         * (9+3)的和，于是表达式变成了：
         * [10 ? 6 ? ((9+3) * -11)]
         * 再到后面的/号：
         * [10 ? (6 / ((9+3) * -11))]
         * 到后面的*号：
         * [10 * (6 / ((9+3) * -11))]
         * 再到17,+号：
         * [(10 * (6 / ((9+3) * -11))) + 17 ]
         * 5，+号：
         * [((10 * (6 / ((9+3) * -11))) + 17) + 5 ]
         * 最终由逆波兰表达式转变成平常熟悉的中缀表达式，
         */

        /**
         * 解法分析：
         * 由逆波兰表达式转换到中缀表达式计算的规律可以知道，只要碰到符号，就是符号位置的前2个数字进行运算。
         * 所以只需要保存到前2个数字即可。可以使用数组，栈都可以。要注意的一点是，对于保存起来的2个数字xy。
         * 对于符号运算都是 x?y。?表示运算符号。对于+，*，x+y,与y+x是没有区别的，但是-与/就有区别了。注意这点
         * 即可。
         */

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                stack.push(stack.pop() + stack.pop());
                continue;
            }
            if (tokens[i].equals("-")) {
                int i1 = stack.pop();
                int i2 = stack.pop();
                stack.push(i2 - i1);
                continue;
            }
            if (tokens[i].equals("*")) {
                stack.push(stack.pop() * stack.pop());
                continue;
            }

            if (tokens[i].equals("/")) {
                int i1 = stack.pop();
                int i2 = stack.pop();
                stack.push(i2 / i1);
                continue;
            }

            stack.push(Integer.valueOf(tokens[i]));
        }
        return stack.pop();

//        执行用时：7 ms, 在所有 Java 提交中击败了 57.67% 的用户
//        内存消耗：38.6 MB, 在所有 Java 提交中击败了 7.19% 的用户
    }

    /**
     * 73、矩阵置0 ?
     */
    public void setZeroes(int[][] matrix) {

    }

    /**
     * 191、位1的个数
     */
    public static int hammingWeight(int n) {
        /**
         * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数
         */

        /**
         * 解法
         * 本来想偷个懒，转成字符串后搞个for找1就算了。后来发现这个输入的二进制会解析成十进制。莫得办法，
         * 只能用位运算来统计了。
         */

        int result = 0;

        // Integer.bitCount(n); // 使用系统api

        // 失败的方案。
        String s = String.valueOf(n);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                result++;
            }
        }

        /**
         * 应用性质为：
         * 在位运算中，n & (n - 1) 会将 n中最低位的1消掉。
         * 如统计5的二进制1的数量。
         * 5的二进制为
         *  0101
         * 4的二进制为
         *  0100
         * &操作后就是：
         *  0100 == 4
         * 继续&3
         * 0100
         * &
         * 0011
         * =
         * 0000
         *
         * 即重复次数就是1的数量
         */

        while (n != 0) {
            n = n & (n - 1);
            result++;
        }

        return result;
    }

    /**
     * 341、扁平化迭代器
     */
    public static void test341() {

        List<NestedInteger> nestedList = new ArrayList<>();

        NestedInteger.A a1 = new NestedInteger.A(1);
        NestedInteger.A a2 = new NestedInteger.A(1);
        List<NestedInteger> list1 = new ArrayList<>();
        list1.add(a1);
        list1.add(a2);
        NestedInteger.A n1 = new NestedInteger.A(0, false, list1);

        NestedInteger.A n2 = new NestedInteger.A(2);


        NestedInteger.A a3 = new NestedInteger.A(1);
        NestedInteger.A a4 = new NestedInteger.A(1);
        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(a3);
        list2.add(a4);
        NestedInteger.A n3 = new NestedInteger.A(0, false, list2);

        nestedList.add(n1);
        nestedList.add(n2);
        nestedList.add(n3);

        NestedIterator i = new NestedIterator(nestedList);
        while (i.hasNext()) {
            int s = i.next();
            System.out.print(s + "  ");
        }
    }

    /**
     * 456、132模式 ?
     */
    public static void test456(int[] nums) {

        /**
         * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
         * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
         * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
         * 如：
         * 输入：nums = [3,1,4,2]
         * 输出：true
         * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
         */

        // 暴力法。


    }

    /**
     * 82、删除排序链表中的重复元素II
     */
    public ListNode deleteDuplicatesII(ListNode head) {

        /**
         * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
         * 返回同样按升序排列的结果链表。
         * 例如：
         * 输入：head = [1,2,3,3,4,4,5]
         * 输出：[1,2,5]
         */


        /**
         * 分析：题目意思很明确，删除掉所有重复过的元素。确立好一个前指针，一个当前指针。前指针指向都是非重复的数，
         * 当前指针对比是否是重复元素。删除重复节点分2个步骤：1、先删除除了第一个相同节点外的全部相同节点；2、删除
         * 第一个相同节点。
         *
         * 注意：
         * 有时候第一个节点就是要被删除的节点，也就是说头结点要变化。这时候可以造一个假头结点，就不用考虑第一个节点
         * 被改变的情况了。这个小技巧适用其他链表问题。
         */

        if (head == null) return null;
        ListNode nullNode = new ListNode(-1);
        nullNode.next = head;

        ListNode lastNode = nullNode;
        ListNode curNode = head;

        while (curNode != null && curNode.next != null) {
            if (curNode.val == curNode.next.val) {
                //循环删除相同的节点
                while (curNode.next != null && curNode.val == curNode.next.val) {
                    curNode.next = curNode.next.next;
                }
                //删除第一个相同节点
                lastNode.next = curNode.next;
                if (curNode.next != null) {
                    curNode = lastNode.next;
                }
            } else {
                lastNode = curNode;
                curNode = curNode.next;
            }
        }
        return head;
    }

    /**
     * 83. 删除排序链表中的重复元素
     */
    public ListNode deleteDuplicatesI(ListNode head) {

        /**
         * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
         * 返回同样按升序排列的结果链表。
         * 例子：
         * 输入：head = [1,1,2]
         * 输出：[1,2]
         */

        /**
         * 很显然，和上一题一个解题思路。
         */

        if (head == null) return null;

//        ListNode nullNode = new ListNode(-1);
//        nullNode.next = head;
//
//        ListNode lastNode = nullNode;
//        ListNode curNode = head;
//
//        while (curNode != null && curNode.next != null) {
//            if (curNode.val == curNode.next.val) {
//                //循环删除相同的节点
//                while (curNode.next != null && curNode.val == curNode.next.val) {
//                    curNode.next = curNode.next.next;
//                }
//                if (curNode.next != null) {
//                    curNode = lastNode.next;
//                }
//            } else {
//                lastNode = curNode;
//                curNode = curNode.next;
//            }
//        }
//        return head;

//        执行用时：1 ms, 在所有 Java 提交中击败了 32.99% 的用户
//        内存消耗：37.7 MB, 在所有 Java 提交中击败了 85.08% 的用户


        //优化版：(和II相差无几，但是这里就不用一个while去循环，直接判断即可)
        // [1,1,1,2,3,3]

        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (curNode.val == curNode.next.val) {
                //循环删除相同的节点
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        return head;

    }

    /**
     * 61、旋转链表 ?
     */
    public ListNode rotateRight(ListNode head, int k) {
        return new ListNode(-1);
    }

    /**
     * 173、二叉树迭代器 ?
     */
    public void test173() {

    }

    /**
     * 190、颠倒二进制位 ?
     */
    public int test190() {
        /**
         * 颠倒给定的 32 位无符号整数的二进制位。
         * 输入: 00000010100101000001111010011100
         * 输出: 00111001011110000010100101000000
         * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
         *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
         *提示：
         * 输入是一个长度为 32 的二进制字符串
         */
        return 0;
    }

    /**
     * 74、搜索二维矩阵
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        /**
         * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
         * 每行中的整数从左到右按升序排列。
         * 每行的第一个整数大于前一行的最后一个整数。
         * 例如：
         * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
         * 输出：true
         */

        /**
         * 思路
         * 因为题目有规律，分别对行、列使用二分查找，提升查找效率
         */

        if (matrix.length == 0) return false;
        // 二分查找行
        int low = 0;
        int high = matrix.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (matrix[mid][0] > target) {
                high = mid - 1;
            } else if (matrix[mid][0] < target) {
                low = mid + 1;
            } else
                return true;
        }
        if (high < 0) {
            low = 0;
        } else if (low > high) {
            low = high;
        }
        //二分查找列
        int clow = 0;
        int chigh = matrix[low].length - 1;
        while (clow <= chigh) {
            int mid = (clow + chigh) / 2;
            if (matrix[low][mid] > target) {
                chigh = mid - 1;
            } else if (matrix[low][mid] < target) {
                clow = mid + 1;
            } else
                return true;
        }
        return false;

        //    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
        //    内存消耗：38 MB, 在所有 Java 提交中击败了43.05%的用户
    }

    /**
     * 78、子集(扩展题)
     */
    public List<List<Integer>> test90(int[] nums) {
        /**
         * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
         * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
         */

        // [1,2,2]
        // [[],[1],[1,2],[1,2,2],[2],[2,2]]

        /**
         * 思路
         *
         * 回溯+递归
         */

        List<Integer> list = new ArrayList<>();
        addChild90(nums, 0, list);

        return lists;
    }

    List<List<Integer>> lists = new ArrayList<>();

    private void addChild90(int[] nums, int position, List<Integer> list) {
        if (position >= nums.length) {
            List<Integer> r = new ArrayList<>(list);
            lists.add(r);
            return;
        }

        list.add(nums[position]);
        addChild90(nums, position + 1, list);
        list.remove(list.size() - 1);
        addChild90(nums, position + 1, list);
    }

    /**
     * 90、子集II(3.31) ?
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        /**
         *  给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
         *  解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
         */


        return lists;
    }

    /**
     * 1006、笨阶乘
     */
    public int clumsy(int N) {
        /**
         * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
         * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，
         * 加法(+)和减法(-)。例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：
         * 我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。另外，我们使用的除法是地板除法（floor division），
         * 所以 10 * 9 / 8 等于 11。这保证结果是一个整数。实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
         *
         *  提示：
         * 1 <= N <= 10000
         * -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
         */

        // 4*3/2+1
        /**
         * 思路：
         * 借助栈，执行加减乘除即可。
         */

        String[] op = new String[]{"*", "/", "+", "-"};
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(N);
        for (int i = N - 1; i > 0; i--) {
            switch (op[index]) {
                case "*":
                    stack.push(i * stack.pop());
                    break;
                case "/":
                    stack.push(stack.pop() / i);
                    break;

                case "+":
                    stack.push(i);
                    break;
                case "-":
                    stack.push(i * -1); // 转换成负数，最后统一相加
                    break;
            }
            index = (index + 1) % 4;
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;

//        执行用时：16 ms, 在所有 Java 提交中击败了 7.74% 的用户
//        内存消耗：37.5 MB, 在所有 Java 提交中击败了 18.06% 的用户
    }

    /**
     * 80、删除有序数组中的重复项II
     */
    public int removeDuplicates2(int[] nums) {

        /**
         * 给你一个有序数组 nums ，请你原地删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
         * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
         *
         * 例子:
         * 输入：nums = [1,1,1,2,2,3]
         * 输出：5, nums = [1,1,2,2,3]
         * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
         *
         */

        /**
         * 解题：
         * 从题目上还是很容易理解的。要在原地修改数组；并且每个相同的元素最多出现2次；还是有序数组。这些条件满足下，很容易就想到数组置换，使用双指
         * 针的办法实现。一个p指针代表在符合题意下所放的元素位置，另一个指针q循环遍历数组。当符合条件时，将q所指向的元素放到p所在的位置即可。很明确的条
         * 件有：前2个元素可以不用考虑，因为相同元素可以出现2次。从第三个元素开始。那么要怎样判断相同的元素已经添加了2个？这里就用到了另外一个条件：有序数组。
         * 只要后来的元素不等于指针p-2位置上的元素，也就是那就可以了。
         */

        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            if (length < 2 || nums[i] > nums[length - 2]) {
                nums[length] = nums[i];
                length++;
            }
        }
        return length;
//        执行用时：1 ms, 在所有 Java 提交中击败了 80.95% 的用户
//        内存消耗：38.5 MB, 在所有 Java 提交中击败了 79.84% 的用户
    }

    /**
     * 81. 搜索旋转排序数组 II
     */
    public boolean search(int[] nums, int target) {
        /**
         * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
         * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ...,
         * nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为
         * [4,5,6,6,7,0,1,2,4,4] 。
         * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，
         * 则返回 true ，否则返回 false 。
         *
         * 示例 1：
         * 输入：nums = [2,5,6,0,0,1,2], target = 0
         * 输出：true
         *
         * 提示：
         * 1 <= nums.length <= 5000
         * -104 <= nums[i] <= 104
         * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
         * -104 <= target <= 104
         *
         */


        /**
         * 直接遍历也行。考点是二分。
         */

        if (nums.length <= 0) return false;
        if (nums.length == 1) return nums[0] == target;

        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l <= r) {

            mid = (l + r) / 2;

            if (nums[mid] == target) return true;
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                l++;
                r--;
            } else if (nums[l] <= nums[mid]) { //看左半边情况,表示左半边是有序的。
                // 取=，表示这些左半边全相等；或者数组后半边全相等，但是以mid位置旋转；或者整个数组都是相等。

                if (nums[mid] > target && target >= nums[l]) { //看是否在左半边
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {   // 右半边有序
                if (target > nums[mid] && target <= nums[r]) { // 是否在右半边
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
//        执行用时：1 ms, 在所有 Java 提交中击败了 88.78% 的用户
//        内存消耗：38.2 MB, 在所有 Java 提交中击败了 52.34% 的用户
    }

    /**
     * 263、丑数
     */
    public boolean isUgly(int n) {
        /**
         * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
         * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
         */

        /**
         * 题解：
         * 只需要无限除以2、3、5，最后结果如果是等于1，则表示这个数的质因数只包含2，3，5了。
         * 根据丑数的定义，0和负整数一定不是丑数。(这点很重要)
         */
        if (n <= 0) return false;

        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;

//        执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：35.1 MB, 在所有 Java 提交中击败了 97.55% 的用户
    }

    /**
     * 264、丑数II
     */
    public static int nthUglyNumber(int n) {
        /**
         * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
         * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
         */

        /**
         * 思路：
         * 参考丑数1(T263)，注意1也会被称为丑数。
         *
         * 2 3 5
         * 1 2 3 4 5 6 8
         */
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int a2 = 1, a3 = 1, a5 = 1;

        for (int i = 2; i <= n; i++) {

            int i1 = dp[a2] * 2, i2 = dp[a3] * 3, i3 = dp[a5] * 5;
            dp[i] = Math.min(Math.min(i1, i2), i3);

            if (dp[i] == i1) {
                a2++;
            }
            if (dp[i] == i2) {
                a3++;
            }
            if (dp[i] == i3) {
                a5++;
            }
        }
        return dp[n];
//        执行用时：2 ms, 在所有 Java 提交中击败了 94.05% 的用户
//        内存消耗：37.3 MB, 在所有 Java 提交中击败了 82.58% 的用户
    }

    /**
     * 179、最大数
     */
    public String largestNumber(int[] nums) {
        /**
         * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
         * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
         * 提示：
         * 1 <= nums.length <= 100
         * 0 <= nums[i] <= 109
         */


        /**
         * 思路：
         * 转换成2个字符字符串相加，比较。
         */

        String[] sths = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sths[i] = nums[i] + "";
        }
        if (nums.length <= 1) return sths[0];

        Arrays.sort(sths, (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s2.compareTo(s1); // 从小到大使用s1.compareTo(s2),这里使用从大到小。
        });

        if (sths[0].equals("0")) return sths[0];

        StringBuilder sb = new StringBuilder();
        for (String sth : sths) sb.append(sth);
        return sb.toString();

//        执行用时：12 ms, 在所有 Java 提交中击败了 19.35% 的用户
//        内存消耗：38.2 MB, 在所有 Java 提交中击败了 25.91% 的用户
    }

    /**
     * 783、二叉搜索树节点最小距离(4-13) ?
     */
    public int minDiffInBST(TreeNode root) {
        /**
         * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
         * 输入：root = [4,2,6,1,3]
         * 输出：1
         * 输入：root = [1,0,48,null,null,12,49]
         * 输出：1
         */
        return root.val;
    }

    /**
     * 208、实现Trie(前缀树)(4-14) ?
     */
    public void test208() {
        Trie trie = new Trie();
    }

    /**
     * 213、打家劫舍II(4-15) ?
     */
    public int rob(int[] nums) {
        /**
         * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个
         * 房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
         * 系统会自动报警 。
         * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
         *
         * 示例 1：
         * 输入：nums = [2,3,2]
         * 输出：3
         * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
         */
        return 0;
    }

    /**
     * 87、扰乱字符串 (4-16) ?
     */
    public boolean isScramble(String s1, String s2) {
        /**
         * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
         * 如果字符串的长度为 1 ，算法停止
         * 如果字符串的长度 > 1 ，执行下述步骤：
         * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
         * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
         * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
         * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
         *
         * 如：
         * 输入：s1 = "abcde", s2 = "caebd"
         * 输出：false
         *
         * 提示：
         * s1.length == s2.length
         * 1 <= s1.length <= 30
         * s1 和 s2 由小写英文字母组成
         */
        return false;
    }

    /**
     * 220、存在重复元素III (4-17) ?
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /**
         * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足
         * abs(i - j) <= k 。如果存在则返回 true，不存在返回 false。
         * 示例 1：
         * 输入：nums = [1,2,3,1], k = 3, t = 0
         * 输出：true
         *
         * 提示：
         * 0 <= nums.length <= 2 * 104
         * -231 <= nums[i] <= 231 - 1
         * 0 <= k <= 104
         * 0 <= t <= 231 - 1
         */
        return false;
    }

    /**
     * 26、删除有序数组中的重复项 (4-18) ?
     */
    public int removeDuplicates(int[] nums) {
        /**
         * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
         * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
         * 例子：
         * 输入：nums = [1,1,2]
         * 输出：2, nums = [1,2]
         * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
         *
         *  提示：
         * 0 <= nums.length <= 3 * 104
         * -104 <= nums[i] <= 104
         * nums 已按升序排列
         */

        /**
         * 思路
         * 参考： removeDuplicates2()
         */
        return 0;
    }

    /**
     * 27、移除元素
     */
    public int removeElement(int[] nums, int val) {
        /**
         * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
         * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
         * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
         *
         *  提示：
         * 0 <= nums.length <= 100
         * 0 <= nums[i] <= 50
         * 0 <= val <= 100
         */

        /**
         * 思路
         *
         * 双指针
         */

        if (nums.length <= 0) return 0;
        if (nums.length == 1) return nums[0] == val ? 0 : 1;
        int p = 0;
        for (int i = 0; i < nums.length; i++) { // [3.2.2.3] 3
            if (nums[i] != val) {
                if (p != i) {
                    nums[p] = nums[i];
                    nums[i] = 0;
                }
                p++;
            }
        }
        return p;
//        执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：37.3 MB, 在所有 Java 提交中击败了 8.45% 的用户
    }

    /**
     * 28、实现strStr()
     */
    public int strStr(String haystack, String needle) {

        /**
         * 实现 strStr() 函数。
         * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
         * 如果不存在，则返回  -1 。
         *  
         * 说明：
         * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
         * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
         *
         */
        //实际上应该是自己实现一个indexOf方法，而不是直接调用这个api
        //不过 真香。哈哈哈
        return haystack.indexOf(needle);

        /**
         * 本题最佳解法 kmp，还没学会
         */
    }

    /**
     * 91、解码方法
     */
    public int numDecodings(String s) {
        /**
         * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
         * 'A' -> 1
         * 'B' -> 2
         * ...
         * 'Z' -> 26
         * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
         * "AAJF" ，将消息分组为 (1 1 10 6)
         * "KJF" ，将消息分组为 (11 10 6)
         * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
         *
         * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
         * 题目数据保证答案肯定是一个 32 位 的整数。
         *
         * 示例 1：
         * 输入：s = "12"
         * 输出：2
         * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
         *
         * 提示：
         * 1 <= s.length <= 100
         * s 只包含数字，并且可能包含前导零。
         */

        /**
         * 思路：
         * 使用动态规划。
         *
         * 设dp[i]表示前i个字符串解码方法的总数。对于最后一次解码使用字符数量有2种情况。分别是1位字符解码，如'1','2'等等；2位字符
         * 解码，如"12","26"。不存在有3位字符(字母Z最大为26)。
         * 第一种情况下，确定了最后使用了一个字符解码后，那么总方法数取决于前i-1的总方法数，状态转移方程有：dp[i] = dp[i-1]；
         * 第二种情况，同样的确定了使用2个字符解码，最终总方法数取决于前i-2的总方法数，状态转移方程为dp[i]= dp[i-2]；
         * 但是这2种情况并不冲突，可以同时满足。当同时满足1,2情况时，dp[i] = dp[i-1] + dp[i-2]。
         * dp数组定义好了，转移方程也写出来了，还需要考虑的一个因素是上述情况都是在一定条件下的。如情况1，单个字符解码，数字0是不能被解码的；
         * 情况2，'01','05','28'，这种2位数也是不能解码的，0的开头是非法，28是超出了最大解码字母Z(26)。都是不符合条件的。
         */

//        int[] dp = new int[s.length() + 1];
//        dp[0] = 1;
//        for (int i = 1; i <= s.length(); i++) {
//            //个位数解码成字符(0不能解码)
//            if (s.charAt(i - 1) > '0') {
//                dp[i] = dp[i - 1];
//            }
//            //2位数解码成字符(10~26)
//            if (i > 1 && s.charAt(i - 2) != '0') {
//                int c = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
//                if (c <= 26) {
//                    dp[i] += dp[i - 2];
//                }
//            }
//        }
//        return dp[s.length()];


        //优化[状态压缩]
        /**
         * 通过观察分析可以看出，对于dp数组只与i，i-1,i-2相关。所以可以只维护一个大小为3的数字替代大小为n的数组。
         * 空间复杂度从O(n)优化到了O(1)。
         */
        int[] dp = new int[3];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            // 默认值
            dp[i % 3] = 0;

            //个位数解码成字符(0不能解码)
            if (s.charAt(i - 1) > '0') {
                dp[i % 3] = dp[(i - 1) % 3];
            }
            //2位数解码成字符(10~26)
            if (i > 1 && s.charAt(i - 2) != '0') {
                int c = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                if (c <= 26) {
                    dp[i % 3] += dp[(i - 2) % 3];
                }
            }
        }

        return dp[s.length() % 3];
//        执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：36.5 MB, 在所有 Java 提交中击败了 86.57% 的用户
    }

    /**
     * 363、矩形区域不超过K的最大数值和
     */
    public int maxSumSubmatrix(int[][] mat, int k) {
        /**
         * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
         * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
         * 示例 1：
         * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
         * 输出：2
         * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
         * 示例 2：
         * 输入：matrix = [[2,2,-1]], k = 3
         * 输出：3
         *
         * 提示：
         * m == matrix.length
         * n == matrix[i].length
         * 1 <= m, n <= 100
         * -100 <= matrix[i][j] <= 100
         * -105 <= k <= 105
         * 进阶：如果行数远大于列数，该如何设计解决方案？
         */

        /**
         * 思路：
         * 先用前缀和
         */
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int p = i; p <= m; p++) {
                    for (int q = j; q <= n; q++) {
                        int cur = sum[p][q] - sum[i - 1][q] - sum[p][j - 1] + sum[i - 1][j - 1];
                        if (cur <= k) {
                            ans = Math.max(ans, cur);
                        }
                    }
                }
            }
        }
        return ans;
//        执行用时：288 ms, 在所有 Java 提交中击败了 30.89% 的用户
//        内存消耗：38.5 MB, 在所有 Java 提交中击败了 76.13% 的用户
    }

    /**
     * 368、 最大整除子集
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        /**
         * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
         * answer[i] % answer[j] == 0 ，或
         * answer[j] % answer[i] == 0
         * 如果存在多个有效解子集，返回其中任何一个均可。
         *
         * 示例 1：
         * 输入：nums = [1,2,3]
         * 输出：[1,2]
         * 解释：[1,3] 也会被视为正确答案。
         * 示例 2：
         * 输入：nums = [1,2,4,8]
         * 输出：[1,2,4,8]
         *
         * 提示：
         * 1 <= nums.length <= 1000
         * 1 <= nums[i] <= 2 * 109
         * nums 中的所有整数 互不相同
         *
         */

        /**
         * 思路:
         * 题目中最大整除子集就是这个集合中任意2个数之间为倍数关系。即(A/B==0 || B/A==0)。要求最大，也就是集合内size最长。
         * 法1：
         * 有一个比较好的思路就是对数组先排序，从小到大。对于数组中的每个数nums[i]都除以前面的数，凡是能整除的数都能成为以nums[i]
         * 为最大元素的集合中的元素（注意还不能确定是否是以nums[i]为最大数的最大整除集合，因为A/B=0,A/C=0,但不能保证B/C=0）。
         *
         * 法2：使用动态规划。
         * 按照上面思路，数组nums是有序的，而且A/B=0,C/A=0,那么一定有C/B=0,其中C>A>B。假如知道了在数组nums中对于A能被它整除的数的数量，那么
         * 对于任何一个数B能将A整除，那么B同样能整除A能整除的数。于是有知道了A的最大整除子集p，那么B的最大整除子集为p+1。
         * 定义DP数组：使用dp[i]表示在数组中前i个数的最大整除子集数量。
         * base case：每个数资审都是自己的子集，所以dp[i]的初始状态都是1。
         * 边界条件：数组的size
         * 转移方程：当两个数组下标i、j，nums[i]/nums[j]=0时，dp[i] = dp[i]+dp[j],dp[i]默认等于1，也可以写成
         * dp[i] = 1+dp[j]
         */

        List<Integer> result = new ArrayList<>();
        if (nums.length == 1) {
            result.add(nums[0]);
            return result;
        }

        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxSize = 0;
        int maxValue = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {// 题目声明没有重复元素
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 动态规划求最大集合长度,因为还没有遍历到i，需要比较取得最大的dp[i]
                }
            }
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxValue = nums[i];
            }
        }

        // 倒推获取元素
        for (int i = nums.length - 1; i >= 0; i--) {
            if (dp[i] == maxSize && maxValue % nums[i] == 0) {
                result.add(nums[i]);
                maxValue = nums[i];
                maxSize--;
            }
            if (maxSize < 0) break;
        }

        return result;
//        执行用时：19 ms, 在所有 Java 提交中击败了 98.30% 的用户
//        内存消耗：38.7 MB, 在所有 Java 提交中击败了 51.71% 的用户
    }

    /**
     * 377、组合总和Ⅳ
     */
    public int combinationSum4(int[] nums, int target) {
        /**
         * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
         * 题目数据保证答案符合 32 位整数范围。
         *
         * 示例 1：
         * 输入：nums = [1,2,3], target = 4
         * 输出：7
         * 解释：
         * 所有可能的组合为：
         * (1, 1, 1, 1)
         * (1, 1, 2)
         * (1, 2, 1)
         * (1, 3)
         * (2, 1, 1)
         * (2, 2)
         * (3, 1)
         * 请注意，顺序不同的序列被视作不同的组合。
         *
         * 示例 2：
         * 输入：nums = [9], target = 3
         * 输出：0
         *
         *  提示：
         * 1 <= nums.length <= 200
         * 1 <= nums[i] <= 1000
         * nums 中的所有元素 互不相同
         * 1 <= target <= 1000
         */


        /**
         * 求最值就看能不能往dp上靠
         * 思路：一开始的思路是定义一个数组大小为1001的dp数组，dp[i]表示前i个数的组合方式总数量。然后在去遍历nums，得出数组中每个数
         * 的总数量，即sum(i) = dp[target-nums[i]],最后相加。但是这样有个问题就是数组中不一定存在target-nums[i]这个数，
         *
         * 正确的思路：
         * dp数组定义不变，dp大小为target+1。最终结果就是dp[target],使用nums中的元素做逻辑处理。这样做的好处是能确保dp数组中得出的结果完
         * 全来自于nums的计算结果。成功避免了前一个思路是否存在这个dp[i]的问题。
         */
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] > i) continue;
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
//        执行用时：1 ms, 在所有 Java 提交中击败了 98.30% 的用户
//        内存消耗：35.5 MB, 在所有 Java 提交中击败了 96.65% 的用户
    }

    /**
     * 897、递增顺序搜索树
     */
    public TreeNode increasingBST(TreeNode root) {
        /**
         * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
         * 如：
         * 输入：root = [5,1,7]
         * 输出：[1,null,5,null,7]
         *  
         * 提示：
         *
         * 树中节点数的取值范围是 [1, 100]
         * 0 <= Node.val <= 1000
         */

        /**
         * 思路：
         * 中序遍历后使用容器保存节点数据，重新构建新的顺序搜索树
         */

        List<Integer> list = new ArrayList<>();
        ss897(root, list);

        TreeNode head = new TreeNode(-1);
        TreeNode last = head;
        for (int i : list) {
            TreeNode node = new TreeNode(i);
            last.right = node;
            last = node;
        }
        return head.right;
//        执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：35.9 MB, 在所有 Java 提交中击败了 82.07% 的用户
    }

    private void ss897(TreeNode root, List<Integer> list) {
        if (root == null) return;
        ss897(root.left, list);
        list.add(root.val);
        ss897(root.right, list);
    }

    /**
     * 1011、 在D天内送达包裹的能力
     */
    public static int shipWithinDays(int[] weights, int D) {
        /**
         * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
         * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
         * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
         *
         * 示例 1
         * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
         * 输出：15
         * 解释：
         * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
         * 第 1 天：1, 2, 3, 4, 5
         * 第 2 天：6, 7
         * 第 3 天：8
         * 第 4 天：9
         * 第 5 天：10
         *
         * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
         * 提示：
         * 1 <= D <= weights.length <= 50000
         * 1 <= weights[i] <= 500
         */

        /**
         * 思路：二分解法。
         * 假设x为题目答案的最低承运能力。那么对于数组中weights的元素来说都有:
         * weights[i] < x,都不能一天运走 ;weights[i] >= x，都能被一天运走；
         * 既然用到二分，那肯定要先确定二分的数列。假设这个数列为y，y的取值范围应该是在[weights[max],sum(weights)]的。
         * 先看最大值部分，能够一天就运走所有包裹显然就是最大的承运能力。所以max(x) = sum[weights]。对于最小承运能力能不能
         * 从1开始算。显然不行，假如我有一个包裹重为3，那不得拆分成3天运？这很明显不合理。因为1<= D <= weights.length。
         * 以1来计算实际运完天数肯定大于D。min(y) = weights[min]也不行，运完天数一样会大于D。所以min(y)=weights[max]
         * 才是最合理的。以这个值来算，运完所需要天数刚好等于D。
         */

        int max = 0;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            max = Math.max(max, weights[i]);
        }
        int l = max; //
        int r = sum;
        int mid = 0;
        sum = 0;
        int day;
        while (l < r) {
            mid = (l + r) / 2;
            day = 0;
            for (int i = 0; i < weights.length; ) {
                while (i < weights.length && sum + weights[i] <= mid) {
                    sum += weights[i];
                    i++;
                }
                day++;
                System.out.print(sum + " , " + weights[i]);
                sum = 0;
            }
            if (day <= D) { // 判断能不能在D天内运完，不能运完则尝试增大承运能力，能运完则尝试减小承运能力
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
//        执行用时：13 ms, 在所有 Java 提交中击败了 45.71% 的用户
//        内存消耗：41.4 MB, 在所有 Java 提交中击败了 93.46% 的用户
    }

    /**
     * 938. 二叉搜索树的范围和
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        /**
         * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
         * 例子：
         * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
         * 输出：32
         *
         * 提示：
         * 树中节点数目在范围 [1, 2 * 104] 内
         * 1 <= Node.val <= 105
         * 1 <= low <= high <= 105
         * 所有 Node.val 互不相同
         */

        /**
         * 思路：递归
         * 中序遍历后，形成的有序数列，再将范围内的数相加即可。
         */

        return dfs938(root, low, high);
//        执行用时：1 ms, 在所有 Java 提交中击败了 55.17% 的用户
//        内存消耗：46.6 MB, 在所有 Java 提交中击败了 18.17% 的用户
    }

    private int dfs938(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        int left = dfs938(root.left, low, high);
        if (root.val >= low && root.val <= high)
            sum += root.val;
        int right = dfs938(root.right, low, high);
        return sum + left + right;
    }

    /**
     * 633、平方数之和
     */
    public boolean judgeSquareSum(int c) {
        /**
         * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
         *
         * 示例 1：
         *
         * 输入：c = 5
         * 输出：true
         * 解释：1 * 1 + 2 * 2 = 5
         *
         * 提示：
         * 0 <= c <= 231 - 1
         */

        /**
         * 思路： 暴力枚举
         * 当一个数开根号后的数(double类型)与这个数的int类型相等，那么可以说是完全开平方，即通过平方得到。比如根号3 = 1.73， int后等于1。
         * 就是不能完全开平方出来。根号4 = 2.0，int后等于2,2.0 == 2，可以完全开平方出来。
         */
        int max = (int) Math.sqrt(c);
        for (int i = 0; i <= max; i++) {
            double sqrt = Math.sqrt(c - i * i);
            if (sqrt == (int) sqrt) {
                return true;
            }
        }
        return false;
//        执行用时：5 ms, 在所有 Java 提交中击败了 21.32% 的用户
//        内存消耗：35 MB, 在所有 Java 提交中击败了 93.38% 的用户
    }

    /**
     * 403、青蛙过河 ?
     */
    public boolean canCross(int[] stones) {
        /**
         * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
         * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
         * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
         * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
         *
         * 示例 1：
         * 输入：stones = [0,1,3,5,6,8,12,17]
         * 输出：true
         * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳
         * 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
         *
         * 示例 2：
         * 输入：stones = [0,1,2,3,4,8,9,11]
         * 输出：false
         * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
         *
         * 提示：
         * 2 <= stones.length <= 2000
         * 0 <= stones[i] <= 231 - 1
         * stones[0] == 0
         */
        if (stones[1] != 1) return false;


        return false;
    }

    /**
     * 137、只出现一次的数字 II
     */
    public int singleNumber(int[] nums) {
        /**
         * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
//        执行用时：8 ms, 在所有 Java 提交中击败了 5.37% 的用户
//        内存消耗：38.1 MB, 在所有 Java 提交中击败了 82.80% 的用户
    }

    /**
     * 1720、解码异或后的数组
     */
    public int[] decode(int[] encoded, int first) {
        /**
         * 未知 整数数组 arr 由 n 个非负整数组成。
         * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得
         * 到 encoded = [1,2,3] 。给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。请解码返回原数组 arr 。可以证明答案
         * 存在并且是唯一的。
         *
         * 示例 1：
         *
         * 输入：encoded = [1,2,3], first = 1
         * 输出：[1,0,2,1]
         * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
         *
         * 提示：
         * 2 <= n <= 104
         * encoded.length == n - 1
         * 0 <= encoded[i] <= 105
         * 0 <= first <= 105
         */

        /**
         * 思路:
         * 利用^的性质即可。一个数2次^上同一个数等于原数，即a^b^b = a.
         *  a^b = c, c^a = a^b^a = b。
         */

        int[] result = new int[encoded.length + 1];
        result[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            result[i + 1] = encoded[i] ^ result[i];
        }
        return result;
//        执行用时：2 ms, 在所有 Java 提交中击败了 73.79% 的用户
//        内存消耗：39.2 MB, 在所有 Java 提交中击败了 91.13% 的用户
    }

    /**
     * 1486、数组异或操作
     */
    public int xorOperation(int n, int start) {
        /**
         * 给你两个整数，n 和 start 。
         * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
         * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
         *
         * 示例 1：
         * 输入：n = 5, start = 0
         * 输出：8
         * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
         *      "^" 为按位异或 XOR 运算符。
         *
         * 提示：
         * 1 <= n <= 1000
         * 0 <= start <= 1000
         * n == nums.length
         */

        /**
         * 思路：
         * 没啥特别的，根据题目描述即可
         */

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= (start + i * 2);
        }
        return ans;
    }

    /**
     * 1723、完成所有工作的最短时间 [hard]
     */
    public static int minimumTimeRequired(int[] jobs, int k) {

        /**
         * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
         * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作
         * 花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
         * 返回分配方案中尽可能 最小 的 最大工作时间 。
         *
         * 输入：jobs = [1,2,4,7,8], k = 2
         * 输出：11
         * 解释：按下述方式分配工作：
         * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
         * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
         * 最大工作时间是 11 。
         *
         * 提示：
         * 1 <= k <= jobs.length <= 12
         * 1 <= jobs[i] <= 107
         */

        /**
         * 思路：
         * 整体思路与‘在D天内送达包裹的能力 1011’基本一样。基本代码都是一样的。区别是1011是严格按照顺序执行的，而此题可以使用组合，随意搭配。难也就是难
         * 在这里。具体看代码，还是很好理解的。
         * 此题有一个关键点就是剪枝。如果没有剪枝操作的话，是无法提交的，会超时。关于剪枝的理解也看代码内。不一样的是官方答案中做了降序处理，
         * 对于`(workloads[j] == 0 || workloads[j] + cur == maxTime)`中的workloads[j] + cur == maxTime 可能会更好理解。
         * 测试过没有处理降序，也不增加workloads[j] + cur == maxTime判断提交，也是一样可以AC的。
         */

        int l = 0; // max(jobs)
        int r = 0; // sum(jobs)
        for (int i = 0; i < jobs.length; i++) {
            r += jobs[i];
            l = Math.max(l, jobs[i]);
        }
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (check1723(jobs, k, mid)) { //判断是否能分配完工作，不能分配完则增大最大工作时间。
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static boolean check1723(int[] jobs, int k, int maxTime) {
        int[] worker = new int[k];
        // 这里要穷举所有可能的组合，使用递归实现。
        return dispatchWork1732(jobs, worker, 0, maxTime);
    }

    /**
     * @param index 安排的第几个工作。
     */
    private static boolean dispatchWork1732(int[] jobs, int[] workloads, int index, int maxTime) {
        if (index >= jobs.length) {
            return true;  // 所有工作能够安排完，表示此分配方案可行(不代表一定是最佳)
        }
        int cur = jobs[index];
        for (int j = 0; j < workloads.length; j++) { //将工作循环分配给工人，看是否合适
            if (workloads[j] + cur <= maxTime) {
                workloads[j] += cur;
                if (dispatchWork1732(jobs, workloads, index + 1, maxTime)) { // 当前人员工作未达到预期max，可以继续安排，使用递归。
                    return true;
                }
                workloads[j] -= cur;// 执行到这表示将cur分配给j是不行的，尝试分配给下一个工人，对于当前工人需要减去这部分工作。
            }
            // 剪枝处理。
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作(即时最后分配成功了也表示有一人工作量不饱和，不是最优解，可以提前排除这种分配方案)
            // 或者当前工作恰能使该工人的工作量达到了上限()
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[j] == 0 || workloads[j] + cur == maxTime) {
                break;
            }
        }
        return false;
    }

    /**
     * 872、叶子相似的树
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        /**
         * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
         * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
         * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
         * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
         *
         * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
         * 输出：true
         * 示例 2：
         *
         * 输入：root1 = [1], root2 = [1]
         * 输出：true
         */

        /**
         * 思路遍历出树的叶子节点，比较即可。
         */
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        dfs872(root1, sb1);
        dfs872(root2, sb2);
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        return sb1.toString().equals(sb2.toString());
    }

    private void dfs872(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            sb.append(node.val);
            sb.append(","); // 坑在 1,14 与 11,4的区别上了。mmp，加个，用作区别
            return;
        }
        dfs872(node.left, sb);
        dfs872(node.right, sb);
    }

    /**
     * 1734、解码异或后的排列
     */
    public int[] decode(int[] encoded) {
        /**
         * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 
         * encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
         * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
         *
         * 示例 1：
         * 输入：encoded = [3,1]
         * 输出：[1,2,3]
         * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
         * 示例 2：
         *
         * 输入：encoded = [6,5,4,6]
         * 输出：[2,4,1,5,3]
         *
         * 提示：
         * 3 <= n < 105
         * n 是奇数。
         * encoded.length == n - 1
         */

        /**
         * 思路：利用^的特性。一个数同时异或2次同一个数等于它本身。即a ^ b ^ b = a.
         * 我们发现这题与`1720(解码异或后的数组)`很相似，区别在于1486给出了原数组p的第一个数。而这个题没有给出第一个数。所以在能求出原数组第一个数的情况下
         * 就能求出这题的解了。怎么求第一个数?假如把原数组第一个数当成a，数组剩下的数依次异或后的数为b，那么只需要知道原数组的的所有元素异或结果与除了第一数
         * 剩下数的异或结果就可以求出元素组的第一个数了。2个点：1、原数组所有元素依次异或结果；2、除去第一个数外原数组所有元素依次异或结果。对于第一点需要注意
         * 理解题目：`它是前n个正整数的排列`,也就是说原数组元素是由1~n组成的，只需要从1到n依次异或操作即可，看懂了这点就能很好求出第一个点。对于第二点，其实
         * 也不难，认真观察了encoded数组与原数组就能发现，第二点要求的结果就是encoded的奇数位元数异或结果(一时没理解可以在纸上画个图，对比原数组与encoded数
         * 组)只需要将encoded数组奇数位置依次异或，最后将这2个数异或，其结果就是原数组的第一个数了。
         */

        // 原数组所有元素异或结果
        int a = 1;
        for (int i = 2; i <= encoded.length + 1; i++) {
            a ^= i;
        }

        int b = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            b ^= encoded[i];
        }

        int[] result = new int[encoded.length + 1];
        result[0] = a ^ b;
        for (int i = 0; i < encoded.length; i++) {
            result[i + 1] = encoded[i] ^ result[i];
        }
        return result;
    }

    /**
     * 1310、子数组异或查询
     */
    public int[] xorQueries(int[] arr, int[][] queries) {

        /**
         * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
         * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
         * 并返回一个包含给定查询 queries 所有结果的数组。
         *
         * 示例 1：
         * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
         * 输出：[2,7,14,8]
         * 解释：
         * 数组中元素的二进制表示形式是：
         * 1 = 0001
         * 3 = 0011
         * 4 = 0100
         * 8 = 1000
         * 查询的 XOR 值为：
         * [0,1] = 1 xor 3 = 2
         * [1,2] = 3 xor 4 = 7
         * [0,3] = 1 xor 3 xor 4 xor 8 = 14
         * [3,3] = 8
         *
         * 提示：
         *
         * 1 <= arr.length <= 3 * 10^4
         * 1 <= arr[i] <= 10^9
         * 1 <= queries.length <= 3 * 10^4
         * queries[i].length == 2
         * 0 <= queries[i][0] <= queries[i][1] < arr.length
         */

        /**
         * 思路：
         * 与`1734、解码异或后的排列`有点类似，都是利用^的性质,一个数同时异或2次同一个数等于它本身。即a ^ b ^ b = a.
         * 首先可以直接根据题目意思直接根据queries[i][0]、queries[i][1]写for依次异或arr数组即可，但是这样会有大量的重复计算，
         * 比如[1,2],[1,3]。[1,3]的计算其实就是包含了[1,2]的计算。那么可以先构建一个数组xor，保存从0到i的异或结果。接下来就是
         * 根据xor如何求得queries[i]区间的值。
         * 举个例子：arr=[1,2,3,4,5] 要求[2,3]的值。在xor中知道xor[2]= 1^2^3;xor[3] = 1^2^3^4,f(2,3) = 3^4;仔细观察分析
         * 就能知道: f(2,3) = xor[3] ^ xor[2] ^ 3
         *                = 1^2^3^4 ^ 1^2^3 ^ 3
         *                = 1^2^3^4 ^ 1^2 ^ 3^3
         *                = 1^2 ^ 3^4 ^ 1^2
         *                = 3^4
         * 而3就是arr中的第queries[i][0]个元素。总的来说就是怎么通过异或来得到自己想要的那一部分。
         */

        int[] xors = new int[arr.length];
        xors[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xors[i] = arr[i] ^ xors[i - 1];
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][1] == queries[i][0]) {
                result[i] = arr[queries[i][0]];
            } else
                result[i] = xors[queries[i][0]] ^ arr[queries[i][0]] ^ xors[queries[i][1]];
        }
        return result;
//        执行用时：2 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：49.5 MB, 在所有 Java 提交中击败了 42.24% 的用户
    }

    /**
     * 1269、停在原地的方案数
     */
    public static int numWays(int steps, int arrLen) {
        /**
         * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
         * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
         * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
         * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
         *
         * 示例 1：
         *
         * 输入：steps = 3, arrLen = 2
         * 输出：4
         * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
         * 向右，向左，不动
         * 不动，向右，向左
         * 向右，不动，向左
         * 不动，不动，不动
         *
         * 示例 2：
         * 输入：steps = 4, arrLen = 2
         * 输出：8
         *
         * 提示：
         * 1 <= steps <= 500
         * 1 <= arrLen <= 10^6
         */

        /**
         * 思路：动态规划
         * 1、定义dp
         * dp[i][j]表示走了i步，到达位置j点的方案数。0<= i <=step; 0<=j <= arrLen。最终所求结果为dp[step][0],走了step步，回到原点。
         *
         * 2、base case
         * dp[0][0] = 1;步数为0时就在原地，可以当成是1种方案。并且dp[0][j]=0,但实际上并不需要这一列。
         *
         * 3、边界条件
         * step步数不能超过数组范围之外，所以step要取min(step,arrLen),实际上还有一个隐藏条件就是移动后题目要求能回到原点，步数必须是对称的，得留一半
         * 步数用来返回。
         *
         * 4、转移方程
         * 对于走到dp[i][j]的前一步移动方案只有不动，从左来，从右来。不动时，步数花费，路程不变[i-1,j];从左,步数花费，路程-1;从右,步数花费，路程+1;
         * 还有注意从左、从右的条件限制
         *
         * 5、状态压缩
         * 使用3个变量保存状态。这里就不写了0.0
         */
        final int m = 1000000007;
        int[][] dp = new int[steps + 1][arrLen + 1];
        dp[0][0] = 1; // 步数为0时就在原地，可以当成是一种方案。
        int max = Math.min(steps / 2, arrLen); // 确保能回到原点，步数可不能离的太远

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= max; j++) {
                if (j + 1 <= max)
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % m;  // 右边(路程+1，步数-1)
                if (j > 0)
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % m;  // 左边(路程-1，步数-1)
                dp[i][j] = (dp[i][j] + dp[i - 1][j]) % m;  // 不动(路程不变，步数-1)
            }
        }
        return dp[steps][0];
    }

    /**
     * 12、整数转罗马数字
     */
    public String intToRoman(int num) {
        /**
         * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
         * 字符          数值
         * I             1
         * V             5
         * X             10
         * L             50
         * C             100
         * D             500
         * M             1000
         * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
         * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
         * 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
         *
         * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
         * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
         * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
         * 给你一个整数，将其转为罗马数字。
         *  
         * 示例 1:
         * 输入: num = 3
         * 输出: "III"
         *
         * 示例 2:
         * 输入: num = 58
         * 输出: "LVIII"
         * 解释: L = 50, V = 5, III = 3.
         *
         * 示例 3:
         * 输入: num = 1994
         * 输出: "MCMXCIV"
         * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
         *  
         * 提示：
         * 1 <= num <= 3999
         */

        /**
         * 思路
         * 纯数学解法1：
         * 根据题目意思对num依次分解，从而求出对应位数上的罗马值。难度不高，在于要分情况判断
         */

        List<String> list = new ArrayList<>();
        String[] flag5 = new String[]{"V", "L", "D"};
        String[] flag1 = new String[]{"I", "X", "C", "M"};
        int count1 = 0;
        int count5 = 0;
        while (num != 0) {
            int s = num % 10;
            if (s == 4) {
                list.add(flag1[count1] + flag5[count5]);
            } else if (s == 9) {
                list.add(flag1[count1] + flag1[count1 + 1]); //进位
            } else if (s != 0) {
                StringBuffer sb = new StringBuffer();
                int i1 = s - 5;
                if (i1 >= 0) {
                    sb.append(flag5[count5]);
                    for (int i = 0; i < i1; i++) {
                        sb.append(flag1[count1]);
                    }
                } else {
                    for (int i = 0; i < s; i++) {
                        sb.append(flag1[count1]);
                    }
                }
                list.add(sb.toString());
            }
            num = num / 10;
            count5++;
            count1++;
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb2.append(list.get(i));
        }
        return sb2.toString();
//        执行用时：8 ms, 在所有 Java 提交中击败了 22.71% 的用户
//        内存消耗：38.4 MB, 在所有 Java 提交中击败了 29.33% 的用户

        /**
         * 解法2：
         * 由于数据量不大，直接暴力求出每个位置上对应的数添加完成。
         */
//        String[] thousands = {"", "M", "MM", "MMM"};
//        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
//        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
//        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
//
//        StringBuffer roman = new StringBuffer();
//        roman.append(thousands[num / 1000]);
//        roman.append(hundreds[num % 1000 / 100]);
//        roman.append(tens[num % 100 / 10]);
//        roman.append(ones[num % 10]);
//        return roman.toString();
//        执行用时：6 ms, 在所有 Java 提交中击败了 49% 的用户
//        内存消耗：38.4 MB, 在所有 Java 提交中击败了 29% 的用户
    }

    /**
     * 13、罗马数字转整数
     */
    public int romanToInt(String s) {
        /**
         * 题目参考12题，就是条件与结果对调，给出罗马数字求整数。
         *
         * 示例 4:
         * 输入: "LVIII"
         * 输出: 58
         * 解释: L = 50, V= 5, III = 3.
         *
         * 提示：
         * 1 <= s.length <= 15
         * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
         * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
         * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
         * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
         * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
         */

        /**
         * 思路
         * [12、整数转罗马数字]是数字转罗马，通过保存罗马匹配数字，这题可以保存数字匹配罗马实现。依次对每个罗马字符匹配的每个数字相加，
         * 如果出现相反的情况就相减。
         */
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if (i < s.length() - 1 && value < map.get(s.charAt(i + 1))) {// 后面的比前面的数大，IV这种情况
                sum = sum - value;
            } else {
                sum = sum + value;
            }
        }
        return sum;
    }


    /**
     * 421、数组中两个数的最大异或值
     */
    public int findMaximumXOR(int[] nums) {

        /**
         * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
         *
         * 提示：
         * 1 <= nums.length <= 2 * 104
         * 0 <= nums[i] <= 231 - 1
         *
         * 进阶：你可以在 O(n) 的时间解决这个问题吗？
         */

        /**
         * 思路
         *
         * 不追求O(n) 的情况下，双层for循环遍历时间复杂度O(n^2)
         */
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                result = Math.max(temp ^ nums[j], result);
            }
        }
        return result;
    }

    /**
     * 993、二叉树的堂兄弟节点
     */

    public boolean isCousins(TreeNode root, int x, int y) {

        /**
         * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
         * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
         * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
         * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
         *
         * 示例：
         * 输入：root = [1,2,3,4], x = 4, y = 3
         * 输出：false
         *
         * 示例：
         * 输入：root = [1,2,3,null,4], x = 2, y = 3
         * 输出：false
         *
         * 提示：
         * 二叉树的节点数介于 2 到 100 之间。
         * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
         *
         */

        /**
         * 思路
         * 分别找到x、y的父节点以及对应的深度。深度使用递归很好获取，然后比较两个父节点以及深度是否一致。
         * 注意一点的是，因为是整个数遍历，当x、y有一方的父节点等于null，即未赋值时，也就是x、y有一方是根
         * 节点来的，此时结果必定为false。
         */

        if (root == null) return false;
        dfs993(root, x, y, 0);
        if (tx == null || ty == null) return false;
        return tx.val != ty.val && dx == dy;

//        执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗：36.1 MB, 在所有 Java 提交中击败了 75.46% 的用户
    }

    TreeNode tx = null, ty = null;
    int dx = 0, dy = 0;

    private boolean dfs993(TreeNode node, int x, int y, int deep) {
        if (node == null) return false;
        boolean b = node.val == x || node.val == y;
        if (dfs993(node.left, x, y, deep + 1)) {
            if (node.left.val == x) {
                tx = node;
                dx = deep + 1;
            }
            if (node.left.val == y) {
                ty = node;
                dy = deep + 1;
            }
        }
        if (dfs993(node.right, x, y, deep + 1)) {
            if (node.right.val == x) {
                tx = node;
                dx = deep + 1;
            }
            if (node.right.val == y) {
                ty = node;
                dy = deep + 1;
            }
        }
        return b;
    }

    /**
     * 1442、形成两个异或相等数组的三元组数目
     */
    public int countTriplets(int[] arr) {
        /**
         * 给你一个整数数组 arr 。
         * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
         * a 和 b 定义如下：
         * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
         * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
         * 注意：^ 表示 按位异或 操作。
         *
         * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
         */

        /**
         * 思路：
         * 暴力循环解决
         */

        int a = 0, b = 0;
        int count = 0;
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                a = 0;
                for (int x = i; x < j; x++) {
                    a = a ^ arr[x];
                }

                for (int k = j; k < len; k++) {
                    b = 0;
                    for (int y = j; y <= k; y++) {
                        b = b ^ arr[y];
                    }
                    if (a == b) count++;
                }
            }
        }

        /**
         * 为什么可以简写成这样?
         * 从题目知道
         * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
         * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
         * 那么a ^ b = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] ^ arr[j] ^ arr[j + 1] ^ ... ^ arr[k]，
         * 也就是从i到k的所有数^一遍，看是否等于0，2数异或结果等于0要么俩数相等，要么有一方等于0。于是可以使用2层循环完成。
         * 外循环i从0到arr.length-1,内循环j从i到arr.length。保存异或的结果，如果结果等于0，统计数量。这个数量也不是单纯的++
         * 就可以了，因为涉及到3个下标的组合。比如i= 0,j= 5的时候异或结果等于0了，3个数i、j、确定了2个数，那么k可以是除去i、j后
         * 中间的任意一个数，因为最终结果都是等于0，也就是a==b，所以count的数量 = j-i。
         * 此外还有几个隐藏注意点就是下标i < j，k是可以等于j的，可以忽略；
         * 内循环是要从i的位置开始，而不是i+1。因为最终计算count的数量是根据j、i内部数量来计算的，本身就已经排除了i、j所以内循环开
         * 始位置是i，也就是j=i。有人可能会想到:j=i,这样开始执行异或的时候不都是等于0了么，这俩个数是相等的。这个简单只要在判断结果
         * 的时候加上j>i就可以了，而且本身统计数量的时候就会应用到j-i。
         */

        int s;
        for (int i = 0; i < len - 1; i++) {
            s = 0;
            for (int j = i; j < len; j++) {
                s = s ^ arr[j];
                if (s == 0 && j > i) {
                    count = count + j - i;
                }
            }
        }
        return count;
    }

    /**
     * 1738、找出第 K 大的异或坐标值
     */
    public int kthLargestValue(int[][] matrix, int k) {

        /**
         * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
         * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
         * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
         *
         * 示例 ：
         * 输入：matrix = [[5,2],[1,6]], k = 1
         * 输出：7
         * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
         *
         * 示例 ：
         * 输入：matrix = [[5,2],[1,6]], k = 2
         * 输出：5
         * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
         *
         * 示例 ：
         * 输入：matrix = [[5,2],[1,6]], k = 3
         * 输出：4
         * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
         *
         * 示例 ：
         * 输入：matrix = [[5,2],[1,6]], k = 4
         * 输出：0
         * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
         *
         *
         * 提示：
         * m == matrix.length
         * n == matrix[i].length
         * 1 <= m, n <= 1000
         * 0 <= matrix[i][j] <= 106
         * 1 <= k <= m * n
         *
         */

        /**
         * 思路：
         * 为了方便，题意可以理解为：二维数组 matrix ,大小为 m x n。还有一个与matrix大小一样的数组b，在数组b中
         * b(i,j) = matrix[0][0]^...^matrix[i][0]^...^matrix[i][j-1]^matrix[i][j],其中 0<=i<m 且 0<=j<n。然后求
         * 二位数组b中第K打的元素。注意数组b中i对应的是matrix.length。二维数组b的排列格式应该是：
         * {5,1
         * 2,6}
         * 计算：
         * [0,0] i = 0,j = 0; m[i,j] = [0,0] = 5
         * [0,1] i= [0],j=[0,1]; m[i,j] = [0,0]=5,[0,1]=2
         * [1,0] i=[0,1],j=[0]; m[i,j] = [0,0]=5,[1,0]=1
         * [1,1] i=[0,1],j=[0,1] m[i,j] = [0,0][0,1][1,0][1,1] 5,2,1,6
         *
         * 类似这种数组累加(乘)的一般可以使用前缀和的方法。其实就是题目难懂一些，解题不算很难。
         */

        int a = matrix.length;
        int b = matrix[0].length;

        int[][] arrs = new int[a][b];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < a; i++) {  // 这里可以优化一下，将数组大小创建为[a+1][b+1],因为0^任意数等于数本身，就可以省略对i、j边界判断
            for (int j = 0; j < b; j++) {
                arrs[i][j] = matrix[i][j];
                if (i > 0 && j > 0)
                    arrs[i][j] = arrs[i][j] ^ arrs[i - 1][j] ^ arrs[i][j - 1] ^ arrs[i - 1][j - 1];
                else if (i > 0)
                    arrs[i][j] = arrs[i][j] ^ arrs[i - 1][j];
                else if (j > 0)
                    arrs[i][j] = arrs[i][j] ^ arrs[i][j - 1];

                list.add(arrs[i][j]);
            }
        }
        Collections.sort(list, (o1, o2) -> o2 - o1);
        return list.get(k - 1);
    }

    /**
     * 692、前K个高频单词
     */
    public List<String> topKFrequent(String[] words, int k) {
        /**
         * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
         * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
         *
         * 示例 1：
         * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
         * 输出: ["i", "love"]
         * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
         *     注意，按字母顺序 "i" 在 "love" 之前。
         *
         * 示例 2：
         * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
         * 输出: ["the", "is", "sunny", "day"]
         * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
         *     出现次数依次为 4, 3, 2 和 1 次。
         *
         * 注意：
         *
         * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
         * 输入的单词均由小写字母组成。
         */

        /**
         * 思路：
         * 普通方式解决。先统计数量，排序即可。不最求高效率的话，还是比较简单的一题
         */


        // 先统计每个字符串出现的次数
        HashMap<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        // 使用一个集合保存字符串(不相同)
        List<String> rec = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            rec.add(entry.getKey());
        }
        //对保存的字符串集合进行排序
        Collections.sort(rec, (word1, word2) -> cnt.get(word1).equals(cnt.get(word2)) ?
                word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1));

        return rec.subList(0, k);
    }

    /**
     * 1035、不相交的线 ?
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        /**
         * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
         * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
         *
         * nums1[i] == nums2[j]
         * 且绘制的直线不与任何其他连线（非水平线）相交。
         *
         * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。以这种方法绘制线条，并返回可以绘制的最大连线数。
         *
         * 示例 1：
         * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
         * 输出：2
         * 解释：可以画出两条不交叉的线，如上图所示。
         * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
         *
         * 提示：
         * 1 <= nums1.length <= 500
         * 1 <= nums2.length <= 500
         * 1 <= nums1[i], nums2[i] <= 2000
         */
        return 0;
    }

    /**
     * 810、黑板异或游戏
     */
    public boolean xorGame(int[] nums) {

        /**
         * 黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结
         * 果等于 0 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
         * 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
         * 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
         *  
         * 示例：
         * 输入: nums = [1, 1, 2]
         * 输出: false
         * 解释:
         * Alice 有两个选择: 擦掉数字 1 或 2。
         * 如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。
         * 如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
         *
         * 提示：
         * 1 <= N <= 1000
         * 0 <= nums[i] <= 2^16
         */

        /**
         * 思路
         * 具体看官方题解，数学推理这玩意不太擅长，大概意思就是从数组的奇偶方向考虑证明
         */
        if (nums.length % 2 == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }

    /**
     * 1707、与数组中元素的最大异或值 ?
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {

        /**
         * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
         * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，
         * 其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
         * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
         *
         * 示例 1：
         * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
         * 输出：[3,3,7]
         * 解释：
         * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
         * 2) 1 XOR 2 = 3.
         * 3) 5 XOR 2 = 7.
         *
         * 示例 2
         * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
         * 输出：[15,-1,5]
         *  
         * 提示：
         * 1 <= nums.length, queries.length <= 105
         * queries[i].length == 2
         * 0 <= nums[j], xi, mi <= 109
         */

        /**
         * 暴力法超时
         */
        int[] result = new int[queries.length];
        int len = queries.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int j = queries[i][0];
            int k = queries[i][1];
            int max = -1;
            for (int l = 0; l < nums.length; l++) {
                if (nums[l] > k) {
                    break;
                }
                max = Math.max(j ^ nums[l], max);
            }
            result[i] = max;
        }
//        return result;

        /**
         * 字典树
         */

        return nums;
    }

    /**
     * 664、奇怪的打印机 ?
     */
    public int strangePrinter(String s) {

        /**
         * 有台奇怪的打印机有以下两个特殊要求：
         * 打印机每次只能打印由 同一个字符 组成的序列。
         * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
         * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
         *
         * 示例 1：
         * 输入：s = "aaabbb"
         * 输出：2
         * 解释：首先打印 "aaa" 然后打印 "bbb"。
         * 示例 2：
         *
         * 输入：s = "aba"
         * 输出：2
         * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
         *  
         * 提示：
         * 1 <= s.length <= 100
         * s 由小写英文字母组成
         */
        return 0;
    }

    /**
     * 1787、使所有区间的异或结果为零 ?
     */
    public int minChanges(int[] nums, int k) {
        /**
         * 给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right（包括
         * left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
         * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
         *  
         * 示例 1：
         * 输入：nums = [1,2,0,3,0], k = 1
         * 输出：3
         * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
         *
         * 示例 2：
         * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
         * 输出：3
         * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
         *
         * 示例 3：
         * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
         * 输出：3
         * 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
         *  
         * 提示：
         * 1 <= k <= nums.length <= 2000
         * 0 <= nums[i] < 210
         */
        return 0;
    }

    /**
     * 1190、反转每对括号间的子串
     */
    public String reverseParentheses(String s) {
        /**
         * 给出一个字符串 s（仅含有小写英文字母和括号）。
         * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
         * 注意，您的结果中 不应 包含任何括号。
         *
         *
         * 示例 1：
         * 输入：s = "(abcd)"
         * 输出："dcba"
         *
         * 示例 2：
         * 输入：s = "(u(love)i)"
         * 输出："iloveu"
         *
         * 示例 3：
         * 输入：s = "(ed(et(oc))el)"
         * 输出："leetcode"
         *
         * 示例 4：
         * 输入：s = "a(bcdefghijkl(mno)p)q"
         * 输出："apmnolkjihgfedcbq"
         *  
         * 提示：
         * 0 <= s.length <= 2000
         * s 中只有小写英文字母和括号
         * 我们确保所有括号都是成对出现的
         */

        /**
         * 借助栈实现字符反转。多个()层级可以使用集合保存每个栈，每次反转完成都从集合删除这个栈，这样保证反转()内的元素都是有序的。
         * 注意的是，反转完一个栈的元素后，如果还处于()中，也就是集合内还有栈，要将反转的元素放到前一个栈中保存。
         */

        List<Stack<Character>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                Stack<Character> stack = new Stack<>();
                list.add(stack);
            } else if (s.charAt(i) == ')') {
                Stack<Character> stack = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                if (list.size() > 0) {
                    while (!stack.isEmpty()) {
                        list.get(list.size() - 1).push(stack.pop());
                    }
                } else {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                }
            } else {
                if (list.size() <= 0) {
                    sb.append(s.charAt(i));
                } else {
                    list.get(list.size() - 1).push(s.charAt(i));
                }
            }
        }
        return sb.toString();
//        执行用时：4 ms, 在所有 Java 提交中击败了 61.12% 的用户
//        内存消耗：36.9 MB, 在所有 Java 提交中击败了 57.80% 的用户
    }

    /**
     * 461、汉明距离
     */
    public int hammingDistance(int x, int y) {

        /** 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
         * 给出两个整数 x 和 y，计算它们之间的汉明距离。
         *
         * 注意：
         * 0 ≤ x, y < 231.
         *
         *
         * 示例:
         * 输入: x = 1, y = 4
         * 输出: 2
         *
         * 解释:
         * 1   (0 0 0 1)
         * 4   (0 1 0 0)
         *        ↑   ↑
         * 上面的箭头指出了对应二进制位不同的位置。
         */

        /**
         * 思路：异或运算
         * 在二进制中，异或运算是位置相同为0，不同则为1。当2个数执行异或运算后，不同位置的数量就是异或结果1的数量。(还记得有个题目统
         * 计二进制中1的个数吗?题目:191)
         */

        return Integer.bitCount(x ^ y);
    }

    /**
     * 477、汉明距离总和
     */

    public int totalHammingDistance(int[] nums) {
        /**
         * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
         * 计算一个数组中，任意两个数之间汉明距离的总和。
         *
         * 示例:
         * 输入: 4, 14, 2
         * 输出: 6
         *
         * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
         * 所以答案为：
         * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
         *
         * 注意:
         * 数组中元素的范围为从 0到 10^9。
         * 数组的长度不超过 10^4。
         */

        /**
         * 思路
         * 金典暴力百分5。(使用逐位统计效率高些)
         */

        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                count += Integer.bitCount(nums[i] ^ nums[j]);
            }
        }
        return count;

        // 逐位统计法
//        int ans = 0, n = nums.length;
//        for (int i = 0; i < 30; ++i) {
//            int c = 0;
//            for (int val : nums) {
//                c += (val >> i) & 1;
//            }
//            ans += c * (n - c);
//        }
//        return ans;
    }

    /**
     * 1074. 元素和为目标值的子矩阵数量
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {

        /**
         * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
         * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
         * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
         *
         * 示例 1：
         * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
         * 输出：4
         * 解释：四个只含 0 的 1x1 子矩阵。
         *
         * 示例 2：
         * 输入：matrix = [[1,-1],[-1,1]], target = 0
         * 输出：5
         * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
         *
         * 示例 3：
         * 输入：matrix = [[904]], target = 0
         * 输出：0
         *
         * 提示：
         * 1 <= matrix.length <= 100
         * 1 <= matrix[0].length <= 100
         * -1000 <= matrix[i] <= 1000
         * -10^8 <= target <= 10^8
         */
        int a = matrix.length + 1;
        int b = matrix[0].length + 1;
        int[][] arr = new int[a][b];

        int count = 0;
        // 前缀和
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + matrix[i][j];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {

                for (int k = 0; k < i; ++k) {
                    for (int t = 0; t < j; ++t) {
                        if ((arr[i][j] - arr[k][j] - arr[i][t] + arr[k][t]) == target) {
                            count++;
                        }
                    }
                }

            }
        }
        return count;
    }

    /**
     * 231、2 的幂
     */
    public boolean isPowerOfTwo(int n) {

        /** 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
         * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
         *
         * 示例 1：
         * 输入：n = 1
         * 输出：true
         * 解释：2^0 = 1
         *
         * 示例 2：
         * 输入：n = 16
         * 输出：true
         * 解释：2^4 = 16
         *
         * 示例 3：
         * 输入：n = 3
         * 输出：false
         *
         * 提示：
         * -231 <= n <= 231 - 1
         */

        /**
         * 思路：
         * 一个数n是2的幂，当且仅当n是正整数，并且n的二进制表示中仅包含1个1。
         * 所以只要统计到数n的二进制是否只有一个1即可。对于一个数，如果它&上比自己小1的数时，会将自己的最高位1抹除，此时如果结果等于0，
         * 则表示这个是2的幂。
         */
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 342. 4的幂
     */
    public boolean isPowerOfFour(int n) {

        /**
         * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
         * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
         *
         * 示例 1：
         * 输入：n = 16
         * 输出：true
         *
         * 示例 2：
         * 输入：n = 5
         * 输出：false
         *
         * 示例 3：
         * 输入：n = 1
         * 输出：true
         *
         * 提示：
         * -231 <= n <= 231 - 1
         */

        /**
         * 思路分析：
         * 1、如果这个数不是2的n次幂，那么也一定不是4的n次幂。
         * 2、对数n开平方，如果结果是偶数则是4的n次幂，不是则不是4的n次幂。
         */

        if (n == 0) return false;
        if (n == 1) return true;
        if (n > 0 && (n & (n - 1)) != 0) return false;
        double v = Math.sqrt(n);
        return v % 2 == 0;

        /**
         * 更加高级的一点想法：
         * 如果n是4的幂，我们可以发现它除以3的余数一定为1。
         */
        // return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

    /**
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {

        /** 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维
         * 数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。你按照如下规则进行一场游戏：
         *
         * 你从第 0 天开始吃糖果。
         * 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
         * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
         * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃
         * 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。
         * 注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
         * 请你返回得到的数组 answer 。
         *
         * 示例 1：
         * 输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
         * 输出：[true,false,true]
         * 提示：
         * 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
         * 2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。
         * 换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
         * 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
         *
         * 示例 2：
         * 输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
         * 输出：[false,true,true,false,false]
         *
         * 提示：
         * 1 <= candiesCount.length <= 10^5
         * 1 <= candiesCount[i] <= 10^5
         * 1 <= queries.length <= 10^5
         * queries[i].length == 3
         * 0 <= favoriteTypei < candiesCount.length
         * 0 <= favoriteDayi <= 10^9
         * 1 <= dailyCapi <= 10^9
         */

        /**
         *
         */

        // 官方答案
//        int n = candiesCount.length;
//        // 前缀和
//        long[] sum = new long[n];
//        sum[0] = candiesCount[0];
//        for (int i = 1; i < n; ++i) {
//            sum[i] = sum[i - 1] + candiesCount[i];
//        }
//
//        int q = queries.length;
//        boolean[] ans = new boolean[q];
//        for (int i = 0; i < q; ++i) {
//            int[] query = queries[i];
//            int favoriteType = query[0], favoriteDay = query[1], dailyCap = query[2];
//
//            long x1 = favoriteDay + 1;
//            long y1 = (long) (favoriteDay + 1) * dailyCap;
//            long x2 = favoriteType == 0 ? 1 : sum[favoriteType - 1] + 1;
//            long y2 = sum[favoriteType];
//
//            ans[i] = !(x1 > y2 || y1 < x2);
//        }
//        return ans;

        // [sum,sum + candiesCount[c]
        // [day,s]
        //  result[i] = day <= preSum[type] + candiesCount[type] && preSum[type]  < day * max;

        // 自己写的，没整明白的
        int n = queries.length;
        boolean[] result = new boolean[n];
        for (int i = 0; i < n; i++) {
            int[] a = queries[i];
            int c = a[0];
            int day = a[1];
            int max = a[2];

            //吃到第c种类型前需要吃几个糖。
            long sum = 0;
            for (int j = 0; j < c; j++) {
                sum += candiesCount[j];
            }
            //最大化吃的糖数量
            long s = (day + 1) * max;
            result[i] = sum + candiesCount[c] > day && s > sum;
        }
        return result;
    }

    /**
     * 525、连续数组
     */
    public int findMaxLength(int[] nums) {
        /** 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
         *
         * 示例 1:
         * 输入: nums = [0,1]
         *
         * 输出: 2
         * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
         *
         * 示例 2:
         * 输入: nums = [0,1,0]
         * 输出: 2
         * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
         *
         * 提示：
         * 1 <= nums.length <= 105
         * nums[i] 不是 0 就是 1
         */

        /**
         * 思路
         * 0与1的数量相等，则数量(0)-数量(1)等于0。可以使用一个变量去表示，当遇到0做减法，遇到1做加法。
         */

        int length = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) { // 表示这一段1与0的数量是相同的了。
                int prevIndex = map.get(counter);
                length = Math.max(length, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return length;
    }

    /**
     * 160、相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        /** 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
         * 题目数据 保证 整个链式结构中不存在环。
         * 注意，函数返回结果后，链表必须 保持其原始结构 。
         *
         * 示例 1：
         * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
         * 输出：Intersected at '8'
         * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
         * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
         * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
         *
         * 示例 2：
         * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
         * 输出：Intersected at '2'
         * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
         * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
         * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
         *
         * 示例 3：
         * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
         * 输出：null
         * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
         * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
         * 这两个链表不相交，因此返回 null 。
         *
         * 提示：
         * listA 中节点数目为 m
         * listB 中节点数目为 n
         * 0 <= m, n <= 3 * 104
         * 1 <= Node.val <= 105
         * 0 <= skipA <= m
         * 0 <= skipB <= n
         * 如果 listA 和 listB 没有交点，intersectVal 为 0
         * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
         *
         * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
         */

        /**
         * 思路
         * 链A的长度为n，链B的长度为m。让2个指针走(n+m)次。最后相等时要么是相交点，要么都是等于null(走完了m+n)。
         */

        if (headA == null || headB == null) return null;
        ListNode A = headA;
        ListNode B = headB;
        while (headA != headB) {
            headA = headA == null ? B : headA.next;
            headB = headB == null ? A : headB.next;
        }
        return headA;
    }

    /**
     * 203、移除链表元素
     */
    public ListNode removeElements(ListNode head, int val) {

        /** 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
         *
         *  示例 1：
         * 输入：head = [1,2,6,3,4,5,6], val = 6
         * 输出：[1,2,3,4,5]
         *
         * 示例 2：
         * 输入：head = [], val = 1
         * 输出：[]
         *
         * 示例 3：
         * 输入：head = 0[7,7,7,7], val = 7
         * 输出：[]
         *  
         * 提示：
         * 列表中的节点在范围 [0, 104] 内
         * 1 <= Node.val <= 50
         * 0 <= k <= 50
         */

        /**
         * 比较简单的一题了，唯一的点就是构建一个空节点，这样就不用考虑头结点被删除的情况了
         */
        if (head == null) return null;
        ListNode nullNode = new ListNode(0);
        nullNode.next = head;
        ListNode pre = nullNode;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
            } else
                pre = head;
            head = head.next;
        }
        return nullNode.next;
    }

    /**
     * 494、目标和
     */
    public int findTargetSumWays(int[] nums, int target) {
        /**
         * 给你一个整数数组 nums 和一个整数 target 。
         * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
         * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
         * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
         *
         * 示例 1：
         * 输入：nums = [1,1,1,1,1], target = 3
         * 输出：5
         * 解释：一共有 5 种方法让最终目标和为 3 。
         * -1 + 1 + 1 + 1 + 1 = 3
         * +1 - 1 + 1 + 1 + 1 = 3
         * +1 + 1 - 1 + 1 + 1 = 3
         * +1 + 1 + 1 - 1 + 1 = 3
         * +1 + 1 + 1 + 1 - 1 = 3
         *
         * 示例 2：
         * 输入：nums = [1], target = 1
         * 输出：1
         *  
         * 提示：
         * 1 <= nums.length <= 20
         * 0 <= nums[i] <= 1000
         * 0 <= sum(nums[i]) <= 1000
         * -1000 <= target <= 100
         */

        /**
         * 动态规划解法。
         */


        /**
         * 回溯+递归，暴力破解。
         */

        df494(nums, 0, 0, target);
        return sums94;
    }

    int sums94 = 0;

    private void df494(int[] nums, int index, int sum, int target) {
        if (index >= nums.length) { //整个数组循环完,判断和是否与target相等
            if (sum == target) {
                sums94++;
            }
        } else {
            df494(nums, index + 1, sum + nums[index], target);
            df494(nums, index + 1, sum - nums[index], target);
        }
    }

    /**
     * 1049. 最后一块石头的重量 II
     */
    public int lastStoneWeightII(int[] stones) {

        /** 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
         * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
         * 如果 x == y，那么两块石头都会被完全粉碎；
         * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
         * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
         *
         * 示例 1：
         * 输入：stones = [2,7,4,1,8,1]
         * 输出：1
         * 解释：
         * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
         * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
         * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
         * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
         *
         * 示例 2：
         * 输入：stones = [31,26,33,21,40]
         * 输出：5
         *
         * 示例 3：
         * 输入：stones = [1,2]
         * 输出：1
         *
         * 提示：
         * 1 <= stones.length <= 30
         * 1 <= stones[i] <= 100
         */

        /**
         * 思路
         * 动态规划，转换成01背包问题
         *
         * 说了可以转换成01背包问题解决。那么怎么转换呢。题目意思是求剩下最后一块石头重量尽可能小，也就是最接近0。那么对于产生最后一块石头的
         * 2块石头的重量就要尽可能的相等。对此，可以先将这一堆石头分成2堆，总重量分别记作A，B。然后再相减得出差值即可。A、B差值要尽可能小，那
         * 么A、B要尽可能的相等。怎么求A、B的重量呢，如果要去求俩堆石头的重量，难度会大一些，可以通过转换变成求其中一堆石头的重量，这样就容易
         * 多了。我们知道石头堆总重量为S，且S = A + B;对于目标 t = A - B => (S-B) - B => S - 2B。t的值要小，S是固定值，那么B的值就要越大。
         * 到这已经有点01背包的影子了。B的值 = 背包价值，都是取最大。还有一个变量：背包的容量？在此题中其实背包容量也是等于价值的。对于一堆石头分
         * 成2堆，最理想的情况就是A = B。所以背包容量最大值为S/2。到此石头问题已经完全转换成01背包问题了。
         * 注意最后结果 t = S - 2B。背包问题求得是B，所以最后结果应该使用总数减去2倍的B。
         */

        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int max = sum / 2;

        int[][] dp = new int[stones.length][max + 1];

        for (int i = 0; i <= max; i++) {
            dp[0][i] = i >= stones[0] ? stones[0] : 0;
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 0; j <= max; j++) {
                int n = dp[i - 1][j];

                int m = j >= stones[i] ? dp[i - 1][j - stones[i]] + stones[i] : 0;
                dp[i][j] = Math.max(n, m);
            }
        }
        return sum - 2 * dp[stones.length - 1][max];
    }

    /**
     * 879、盈利计划
     */
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        /**
         * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
         * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
         * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
         * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
         *
         * 示例 1：
         * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
         * 输出：2
         * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
         * 总的来说，有两种计划。
         *
         * 示例 2：
         * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
         * 输出：7
         * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
         * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
         *
         * 提示：
         *
         * 1 <= n <= 100
         * 0 <= minProfit <= 100
         * 1 <= group.length <= 100
         * 1 <= group[i] <= 100
         * profit.length == group.length
         * 0 <= profit[i] <= 100
         */

        /**
         * 思路: 动态规划。往01背包问题上靠,属于01背包问题的变种。关键就是它比背包问题多出一个至少盈利变量，
         * 所以可以由二维转成三维数组。
         *
         * 3个变量：工作数量，人数，至少盈利数。
         * dp[i][j][k]表示： 前i件工作，使用人数不超过j，所得利润至少为k的方案数。
         */

        int p = group.length;
        int[][][] dp = new int[p + 1][n + 1][minProfit + 1]; // 前i件工作，所以可以去到i，数组+1。

        // 初始化：前0件工作，使用人数为i，利润最少为0，认为是一种解决方案。
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }
        for (int i = 1; i < p; i++) {
            // 当前工作需要的人数
            int w = group[i];
            //当前工作能产生的利润
            int pr = profit[i];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    // 不做
                    dp[i][j][k] = dp[i - 1][j][k];
                    // 做
                    if (j >= w) // 不做的方案+做的方案。
                        dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - w][Math.max(0, k - pr)]) % 1000000007;
                }
            }
        }
        return dp[p - 1][n][minProfit];
    }

    /**
     * 518、零钱兑换 II
     */
    public int change(int amount, int[] coins) {

        /** 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
         *
         * 示例 1:
         * 输入: amount = 5, coins = [1, 2, 5]
         * 输出: 4
         * 解释: 有四种方式可以凑成总金额:
         * 5=5
         * 5=2+2+1
         * 5=2+1+1+1
         * 5=1+1+1+1+1
         *
         * 示例 2:
         * 输入: amount = 3, coins = [2]
         * 输出: 0
         * 解释: 只用面额2的硬币不能凑成总金额3。
         *
         * 示例 3:
         * 输入: amount = 10, coins = [10]
         * 输出: 1
         *
         * 注意:
         * 你可以假设：
         * 0 <= amount (总金额) <= 5000
         * 1 <= coin (硬币面额) <= 5000
         * 硬币种类不超过 500 种
         * 结果符合 32 位符号整数
         */

        /**
         * 思路 动态规划。
         * 1、定义dp数组
         * 设dp[i] 为凑成总金额面值为i的方法数，最终结果为 dp[i]
         *
         * 2、base case
         * dp[0] = 1。面值为0，啥都不用管，也表示为一种方式
         *
         * 3、转移方程
         * 对于普通dp问题通常都是寻找上一个状态转换，即dp[i] => dp[i-1]。但是放到这个题目下，i表示凑成的总金额面值。如果说上一个状
         * 态为i-1，那么dp[i] = dp[i-1](假设存在面值为1的硬币的情况下)。但实际情况是面值为1的硬币不一定存在。题目是规定了硬币面值
         * 数组。对于面值为1的硬币，只有dp[i] = dp[i-1]；同时对于面值为2的硬币，有dp[i] = dp[i-2]。同理，转移方程的最终转换与硬币
         * 面值数组相关。需要遍历这个面值数组，累加结果即可。
         *
         * 4、边界条件
         * 没有特殊条件。
         *
         * 5、状态压缩
         *
         * 注意事项：
         * 外层为金额数，内层为硬币时，最终结果会出现重复的组合。比如对于 5 [1,2,5]这组情况能得到以下组合：
         * 1：(1) 1
         * 2：(1+1) (2) 2
         * 3: (1+2)[(1+1+1)、(1+2)] (2+1) 到这里可以看出来对于[1+2]就包含了[2+1]这种组合方案，结果出现重复。
         * 4：...
         * 5：...
         * 所以外层为金额数，内层为硬币的循环方式不是非常方便。那么反过来为什么可以呢，同样以5 [1,2,5]为例子：
         * 外层i= 1,内层(dp[i]的值)情况：
         * 1: (1+0)  = 1
         * 2: (1+1)  = 1
         * 3: (1+2)  = 1
         * 4: (1+3)  = 1
         * 5: (1+4)  = 1
         *
         * 外层i= 2，内层情况：
         * 2: (2+0)  = 1+1 =2 (开头的值1为第一次遍历保存结果)
         * 3: (2+1)  = 1+1 =2
         * 4: (2+2)  = 1+2 =3 (开头的值1为第一次遍历保存结果，此时的dp[2]的结果已经被更新，为2)
         * 5: (2+3)  = 1+2 =3
         *
         * 外层i= 5，内层情况：
         * 5: (5+0) = 3+1 =4
         * 对于结果5的方案只有(1+4) (2+3) (5+0)3次。最终结果没有重复。
         */

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
//            for (int j = coins[i]; j <= amount; j++) {
                if (j - coins[i] >= 0) // 对于这步可以优化放到初始化j上面去,就不用在判断了，可以快1ms左右
                    dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /**
     * 279、完全平方数
     */
    public int numSquares(int n) {

        /** 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
         * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
         * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
         *
         * 示例 1：
         * 输入：n = 12
         * 输出：3
         * 解释：12 = 4 + 4 + 4
         *
         * 示例 2：
         * 输入：n = 13
         * 输出：2
         * 解释：13 = 4 + 9
         *
         * 提示：
         * 1 <= n <= 10^4
         */

        /**
         * 1、定义dp
         * dp[i]为当和为i时，需要的完全平方数的最小数量。
         *
         * 2、base case
         * dp[0] = 0,一个都选，数量就是0.
         *
         * 3、转移方程
         * 其实跟前面一题有点类似。对于dp[i]，假如我确定了一个完全平方数x,组成和为i的完全平方数就有i = (i-x) + x。要求i的数量，只需要求出(i-x)部
         * 分的数量再加1就等于了求i的数量。即dp[i] = dp[i-x] +1。所以只需要求出i范围内的所有完全平方数的每种组合方案数量，取最小值即可。
         *
         * 4、边界条件
         * i范围内的完全平方数。
         *
         * 5、状态压缩
         */
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            //初始化：最坏的情况，全部都是1组成
            dp[i] = i;
            // 枚举范围内的所有完全平方数的可能性，记得取最小值。
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                // 关于为什么要+1。首先dp[i]表示的是数量，当(j*j)确立之后，对于i剩余的数就是i - j * j。也就是说在
                // dp[i-j*j]数量下加上j*j就组成了和为i的数了。j*j当做一个完全平方数，所以
                // dp[i]需要在dp[i-j^2]的数量基础上+1，才是dp[i]的值。
            }
        }
        return dp[n];
    }

    /**
     * 278、第一个错误的版本
     */
    public int firstBadVersion(int n) {
        /** 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，
         * 所以错误的版本之后的所有版本都是错的。
         * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
         * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。
         * 你应该尽量减少对调用 API 的次数。
         * 示例:
         * 给定 n = 5，并且 version = 4 是第一个错误的版本。
         *
         * 调用 isBadVersion(3) -> false
         * 调用 isBadVersion(5) -> true
         * 调用 isBadVersion(4) -> true
         * 所以，4 是第一个错误的版本。
         *
         *  boolean isBadVersion(int version);
         */

        /**
         * 思路，其实就是在一个数组中查找目标值，二分查找
         */
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2; // 与 (left+right)/2对比，优势在于计算时不会超出int，防止溢出。
//            if (isBadVersion(mid)) { //判定系统api，不用管
//                right = mid;
//            } else {
//                left = mid + 1;
//            }
        }
        return left;
    }

    /**
     * 374、猜数字大小
     */
    public int guessNumber(int n) {

        /** 猜数字游戏的规则如下：
         *
         * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
         * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
         * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
         * -1：我选出的数字比你猜的数字小 pick < num
         * 1：我选出的数字比你猜的数字大 pick > num
         * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
         * 返回我选出的数字。
         *
         * 示例 1：
         * 输入：n = 10, pick = 6
         * 输出：6
         * 示例 2：
         * 输入：n = 1, pick = 1
         * 输出：1
         * 示例 3：
         * 输入：n = 2, pick = 1
         * 输出：1
         * 示例 4：
         * 输入：n = 2, pick = 2
         * 输出：2
         *
         * 提示：
         * 1 <= n <= 231 - 1
         * 1 <= pick <= n
         *
         * 系统api int guess(int num);
         */

        /**
         * 思路：跟昨天题目一样，二分查找。
         */

        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2; // 与 (left+right)/2对比，优势在于计算时不会超出int，防止溢出。
//            if (guess(mid) == -1) { //判定系统api，不用管
//                right = mid;
//            } else if (guess(mid) == 0) {
//                return mid;
//            } else {
//                left = mid + 1;
//            }
        }
        return left;
    }

    /**
     * 852. 山脉数组的峰顶索引
     */
    public int peakIndexInMountainArray(int[] arr) {

        /** 符合下列属性的数组 arr 称为 山脉数组 ：
         * arr.length >= 3
         * 存在 i（0 < i < arr.length - 1）使得：
         * arr[0] < arr[1] < ... arr[i-1] < arr[i]
         * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
         * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
         * 的下标 i 。
         *
         * 示例 1：
         * 输入：arr = [0,10,5,2]
         * 输出：1
         *
         * 示例 2：
         * 输入：arr = [3,4,5,1]
         * 输出：2
         *
         * 示例 3：
         * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
         * 输出：2
         *
         * 提示：
         * 3 <= arr.length <= 104
         * 0 <= arr[i] <= 106
         * 题目数据保证 arr 是一个山脉数组
         */

        /**
         * 思路
         * 直接遍历找最大值，时间复杂度O(n)，题目应该是考的二分。
         */

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return i;
        }

        // 二分法
        int max = 0;
        int left = 1, right = arr.length - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                max = mid; // 有可能就是这个值，先保存，也有可能是在下降阶段，目标在左边。
                right = mid - 1;
            } else {
                left = mid + 1; //说明还在上升阶段，目标在右边
            }
        }
        return max;
    }
}


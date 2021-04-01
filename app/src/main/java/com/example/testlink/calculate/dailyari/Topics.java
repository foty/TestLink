package com.example.testlink.calculate.dailyari;

import com.example.testlink.calculate.sword_for_offer.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Create by lxx
 * Date : 2021/3/1 10:50
 * Use by
 */
public class Topics {

    public static void main(String[] args) {

        test341();
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
         * 最高有效位解法。
         * 我们知道整数的二进制都是0101这样的。比如 0 = 0000,1 = 0001,2 = 0010。3 = 0011。下面将在二进制中最高位数字为1，剩下
         * 所有的位数都是为0的数，比如2(0010),4(0100),8(1000)这样的数称为最高有效位数，这些数二进制中1所在位置称为最高有效位。
         * 下面用b(x)表示数字x的二进制中1的数量，b(y)表示数字y的二进制中1的数量。如果b(y)始终比b(x)多一个1，就有：
         * b(y) = b(x)+ 1。对于正整数i，如果已知i的最高位有效数为j。那么俩数差k = i - j。对于他们二进制1的数量其实有：
         * b(k) = b(i) - b(j)。现有最高有效位数二进制中1的数量都是1(如2(0010),4(0100),8(1000))。那么 对于上面等式
         * b(i) = b(k) + 1(其中b(k) = b(i - j),j为i的最高位有效数)。那么如何求的最高位有效数，也就是2的整数次幂的数呢。有一
         * 个位运算规律是 x&(x-1)会将x最后一个1消除为0，对于最高位数j来说，j&(j-1) == 0。可用此作为依据
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
         * 1、为了保证最后一个数字，暴力法是将下标指针指向0.有种办法拼接一段相同的数组，除了最后一个元素。这样达到一个
         * 循环的效果。
         * 2、栈保存的是元素对应的下标。很显然，如果栈内存的数数组的元素，那么这将很难定位这个元素的下标。
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
         * 解答分析：
         * 不需要特别的知识点即可。唯一的难点是矩阵的边界变化。使用4个指针，最小行，最小列，最大行，最大列划分遍
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
         * 解法参考上一题的螺旋矩阵。解法通用
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
         * 对于符号运算都是 x?y。？表示运算符号。对于+，*，x+y,与y+x是没有区别的，但是-与/就有区别了。注意这点
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
     * 190、颠倒二进制位
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

        List<Integer> list = new ArrayList<>();
        addChild(nums, 0, list);

        return lists;
    }

    List<List<Integer>> lists = new ArrayList<>();

    private void addChild(int[] nums, int position, List<Integer> list) {
        if (position >= nums.length) {
            List<Integer> r = new ArrayList<>(list);
            lists.add(r);
            return;
        }

        list.add(nums[position]);
        addChild(nums, position + 1, list);
        list.remove(list.size() - 1);
        addChild(nums, position + 1, list);
    }

    /**
     * 90、子集II(3.31)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        /**
         *  给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
         *  解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
         */


        return lists;
    }
}


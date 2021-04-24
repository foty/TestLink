package com.example.testlink.calculate.dailyari;

import com.example.testlink.calculate.sword_for_offer.ListNode;
import com.example.testlink.calculate.sword_for_offer.TreeNode;

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
        int[] ints = new int[]{4, 8, 10, 240};
        List<Integer> list = largestDivisibleSubset(ints);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
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
         * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
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
         * 只要后来的元素不等于指针p-2位置上的元素，那就可以了。
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
     * 783、二叉搜索树节点最小距离(4-13)
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
     * 208、实现Trie(前缀树)(4-14)
     */
    public void test208() {
        Trie trie = new Trie();
    }

    /**
     * 213、打家劫舍II(4-15)
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
     * 87、扰乱字符串 (4-16)
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
     * 220、存在重复元素III (4-17)
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
     * 26、删除有序数组中的重复项 (4-18)
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
            } else {

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
         * 有一个比较好的思路就是对数组先排序，从小到大。对于数组中的每个数nums[i]都除以前面的数，凡是能整除的数都能成为以nums[i]
         * 为最大元素的集合中的元素（注意还不能确定是否是以nums[i]为最大数的最大整除集合，因为A/B=0,A/C=0,但不能保证B/C=0）。
         * 使用动态规划。
         * 按照上面思路，数组nums是有序的，而且A/B=0,C/A=0,那么一定有C/B=0,其中C>A>B。假如知道了在数组nums中对于A能被它整除的数的数量，那么
         * 对于任何一个数B能将A整除，那么B同样能整除A能整除的数。于是有知道了A的最大整除子集p，那么B的最大整除子集为p+1。
         *
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
}


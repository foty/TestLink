package com.example.testlink.calculate.dailyari;

import com.example.testlink.calculate.sword_for_offer.ListNode;
import com.example.testlink.calculate.sword_for_offer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Create by lxx
 * Date : 2021/3/1 10:50
 * Use by
 */
public class Topics {

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
     * 208、实现Trie(前缀树)
     */
    public void test208() {
        Trie208 trie = new Trie208();
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
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗 ？
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
         * 链A的长度为n，链B的长度为m。让2个指针各走(n+m)次。最后相等时要么是相交点，要么都是等于null(走完了m+n)。
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

    /**
     * 65、有效数字
     * <p>
     * {@link com.example.testlink.calculate.sword_for_offer.T53}
     */
    public boolean isNumber(String s) {
        /** 有效数字（按顺序）可以分成以下几个部分：
         * 一个 小数 或者 整数
         * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
         * 小数（按顺序）可以分成以下几个部分：
         *
         * （可选）一个符号字符（'+' 或 '-'）
         * 下述格式之一：
         * 至少一位数字，后面跟着一个点 '.'
         * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
         * 一个点 '.' ，后面跟着至少一位数字
         * 整数（按顺序）可以分成以下几个部分：
         * （可选）一个符号字符（'+' 或 '-'）
         * 至少一位数字
         * 部分有效数字列举如下：
         * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
         *
         * 部分无效数字列举如下：
         * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
         * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
         *
         * 示例 1：
         * 输入：s = "0"
         * 输出：true
         *
         * 示例 2：
         * 输入：s = "e"
         * 输出：false
         *
         * 示例 3：
         * 输入：s = "."
         * 输出：false
         *
         * 示例 4：
         * 输入：s = ".1"
         * 输出：true
         *
         * 提示：
         * 1 <= s.length <= 20
         * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
         */

        // 看剑指offer T53。一样的题目,应用到有限状态机。

        return false;
    }

    /**
     * 483、最小好进制 ？
     */
    public String smallestGoodBase(String n) {

        /**
         *  对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
         * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
         *
         * 示例 1：
         * 输入："13"
         * 输出："3"
         * 解释：13 的 3 进制是 111。
         *
         * 示例 2：
         * 输入："4681"
         * 输出："8"
         * 解释：4681 的 8 进制是 11111。
         *
         * 示例 3：
         * 输入："1000000000000000000"
         * 输出："999999999999999999"
         * 解释：1000000000000000000 的 999999999999999999 进制是 11。
         *
         * 提示：
         * n的取值范围是 [3, 10^18]。
         * 输入总是有效且没有前导 0。
         */

        /**
         * 抄官方，ps 没看懂，先抄下来
         */

        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }

    /**
     * 1239、串联字符串的最大长度
     */
    public static int maxLength(List<String> arr) {

        /**
         * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
         * 请返回所有可行解 s 中最长长度。
         *
         * 示例 1：
         * 输入：arr = ["un","iq","ue"]
         * 输出：4
         * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
         *
         * 示例 2：
         * 输入：arr = ["cha","r","act","ers"]
         * 输出：6
         * 解释：可能的解答有 "chaers" 和 "acters"。
         *
         * 示例 3：
         * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
         * 输出：26
         *
         * 提示：
         * 1 <= arr.length <= 16
         * 1 <= arr[i].length <= 26
         * arr[i] 中只含有小写英文字母
         */

        /**
         * 思路：暴力枚举后，选择最大长度的字符。
         * 回溯+dfs
         */

        Set<Character> set = new HashSet<>();
        dfs1239(arr, 0, set);
        return max1239;
    }

    private static int max1239 = 0;

    private static void dfs1239(List<String> arr, int index, Set<Character> set) {
        // 终止条件
        if (index == arr.size()) {
            max1239 = Math.max(set.size(), max1239);
            return;
        }
        // 判断是否有重复，没有重复则添加到set
        String s = arr.get(index);
        boolean add = true;
        Set<Character> set1 = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            set1.add(c);
            if (set.contains(c) || set1.contains(c)) { // 保证当前字符串也是没有重复的元素。
                add = false;
            }
        }
        //是否添加
        if (add) {
            set.addAll(set1);
        }
        dfs1239(arr, index + 1, set);
        //回溯
        if (add) {
            set.removeAll(set1);
            dfs1239(arr, index + 1, set);
        }
    }

    /**
     * 401、二进制手表
     */
    public List<String> readBinaryWatch(int turnedOn) {

        /**
         *  二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
         * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
         * 小时不会以零开头：
         * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
         * 分钟必须由两位数组成，可能会以零开头：
         * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
         *
         * 示例 1：
         * 输入：turnedOn = 1
         * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
         *
         * 示例 2：
         * 输入：turnedOn = 9
         * 输出：[]
         *
         * 解释：
         * 0 <= turnedOn <= 10
         */
        List<String> ans = new ArrayList<String>();
        for (int h = 0; h < 12; ++h) {
            for (int m = 0; m < 60; ++m) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }

    /**
     * 剑指Offer 38. 字符串的排列
     * {@link com.example.testlink.calculate.sword_for_offer.T28}
     */
    public String[] permutation(String s) {
        /**
         * 输入一个字符串，打印出该字符串中字符的所有排列。
         * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
         *
         * 示例:
         * 输入：s = "abc"
         * 输出：["abc","acb","bac","bca","cab","cba"]
         *
         * 限制：
         * 1 <= s 的长度 <= 8
         */

        /**
         * 剑指offer T28
         */
        return null;
    }

    /**
     * 剑指 Offer 15.二进制中1的个数
     * {@link com.example.testlink.calculate.sword_for_offer.T10}
     * {@link #hammingWeight(int)}
     */
    public int t191(int i) {
        /**
         * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。
         * 因此，如果输入 9，则该函数输出 2。
         *
         * 示例 1：
         * 输入：00000000000000000000000000001011
         * 输出：3
         * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
         *
         * 提示：
         * 输入必须是长度为 32 的 二进制串 。
         */

        // 与T10、hammingWeight()一样。看这2个解法即可。写个最舒服的方式,利用了&的一个特性
        int sum = 0;
        while (i != 0) {
            i = i & (i - 1);
            sum++;
        }
        return sum;
    }

    /**
     * 149、直线上最多的点数
     */
    public int maxPoints(int[][] points) {

        /**
         *  给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
         *
         * 示例 1：
         * 输入：points = [[1,1],[2,2],[3,3]]
         * 输出：3
         *
         * 示例 2：
         * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
         * 输出：4
         *
         * 提示：
         * 1 <= points.length <= 300
         * points[i].length == 2
         * -104 <= xi, yi <= 104
         * points 中的所有点 互不相同
         */

        /**
         * 思路，暴力枚举每2个点的斜率，借助这2个点，判断第三个点是不是在同一条直线上，这样可以绕过求斜率跟截距
         * 时产生的问题。具体操作就是根据俩个点分别求出Δx与Δy，求出一条直线，然后在用其中的一个点与第三个点确立另一条直线，
         * 如果是同一条直线的话有：Δy1/Δx1 = Δy2/Δx2,交叉相乘有：Δy1*Δx2 = Δx1*Δy2。根据这个可以不用求出斜率来。
         */

        int max = 0;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                long dy = points[j][1] - points[i][1];
                long dx = points[j][0] - points[i][0];
                int count = 2;
                for (int k = j + 1; k < points.length; k++) {
                    if (dx * (points[k][1] - points[i][1]) == dy * (points[k][0] - points[i][0]))
                        count++;
                }
                max = Math.max(count, max);
            }
        }
        return max;

        // 暴力枚举每2个点的斜率，对比斜率是否相等(需要把斜率保存起来)
    }

    /**
     * 752、打开转盘锁
     */
    public int openLock(String[] deadends, String target) {
        /**
         * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：
         * 例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
         * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
         * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
         * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
         *
         * 示例 1:
         * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
         * 输出：6
         * 解释：
         * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
         * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
         * 因为当拨动到 "0102" 时这个锁就会被锁定。
         *
         * 示例 2:
         * 输入: deadends = ["8888"], target = "0009"
         * 输出：1
         * 解释：
         * 把最后一位反向旋转一次即可 "0000" -> "0009"
         *
         * 示例 3:
         * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
         * 输出：-1
         * 解释：
         * 无法旋转到目标数字且不被锁定。
         *
         * 示例 4:
         * 输入: deadends = ["0000"], target = "8888"
         * 输出：-1
         *
         * 提示：
         * 1 <= deadends.length <= 500
         * deadends[i].length == 4
         * target.length == 4
         * target 不在 deadends 之中
         * target 和 deadends[i] 仅由若干位数字组成
         */

        /**
         * 思路：bfs
         * 看到是bfs，就要想到队列，想到去重set，想到图。
         * bfs通用模板：
         * 1、构建队列，加入初始状态
         * 2、构建去重集合，加入初始状态
         * 3、定义计数器
         * 4、访问队列所有元素
         *   4.1、找到目标
         *   4.2、将当前元素产生的其他状态加入到队列
         * 5、更新计数器
         *
         * 重点分析第4步骤：对于转盘来说，它有4个孔，但是转的方向可以往上也可以往下，那么转动一次能产生8种结
         * 果，在不考虑去重的情况下，需要把这8次的结果都放到队列里去。
         */

        if ("0000".equals(target)) return 0;

        Set<String> dead = new HashSet<String>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) return -1;

        int step = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer("0000");
        Set<String> seen = new HashSet<String>();
        seen.add("0000");

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String status = queue.poll();
                for (String nextStatus : get752(status)) { // 根据当前状态枚举能产生的新状态
                    if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    //往下转-1，注意0与9特殊位置
    public char numPrev752(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    //往上转+1，注意0与9特殊位置
    public char numSucc752(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    public List<String> get752(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            // 往下
            array[i] = numPrev752(num);
            ret.add(new String(array));
            //往上
            array[i] = numSucc752(num);
            ret.add(new String(array));
            //准备转下一个孔，要恢复到初始状态
            array[i] = num;
        }
        return ret;
    }

    /**
     * 773、滑动谜题 bfs
     */
    public int slidingPuzzle(int[][] board) {
        /**
         *  在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
         * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
         * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
         * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
         * 示例：
         * 输入：board = [[1,2,3],[4,0,5]]
         * 输出：1
         * 解释：交换 0 和 5 ，1 步完成
         *
         * 输入：board = [[1,2,3],[5,4,0]]
         * 输出：-1
         * 解释：没有办法完成谜板
         *
         * 输入：board = [[4,1,2],[5,0,3]]
         * 输出：5
         * 解释：
         * 最少完成谜板的最少移动次数是 5 ，
         * 一种移动路径:
         * 尚未移动: [[4,1,2],[5,0,3]]
         * 移动 1 次: [[4,1,2],[0,5,3]]
         * 移动 2 次: [[0,1,2],[4,5,3]]
         * 移动 3 次: [[1,0,2],[4,5,3]]
         * 移动 4 次: [[1,2,0],[4,5,3]]
         * 移动 5 次: [[1,2,3],[4,5,0]]
         *
         * 输入：board = [[3,2,4],[1,5,0]]
         * 输出：14
         *
         * 提示：
         * board 是一个如上所述的 2 x 3 的数组.
         * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
         */

        /**
         * 思路：bfs
         * 说什么来着，看到bfs就要想到队列，想到去重set，想到图，想到它的步骤模板
         * 步骤：
         * 1、构建队列，加入初始状态
         * 2、构建去重set，加入初始状态
         * 3、定义计时器
         * 4、遍历队列元素
         *    1、找到目标
         *    2、将新产生的状态放入队列
         * 5、更新计时器
         *
         *
         * 老规矩，分析第4步骤：滑板一次操作能产生的新状态跟0板块的位置是相关的，而0板块所在的位置决定了它交换能产生的
         * 新状态。交换位置其实是固定的，比如0在左上角的话，那么它只能跟右边、下边的板块交换。所以可以给板子编号从[012345]的顺序开始枚举，
         * 枚举的结果用二维数组保存：第一维度表示0所在的位置，大小固定是6；第二维度表示能做交换操作的板块的下标。这样就可以确定0板块在不同
         * 位置能产生的新状态了。
         */

        // 将数组转换成一维目标
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(board[i][j]);
            }
        }

        String initial = sb.toString();
        if ("123450".equals(initial)) {
            return 0;
        }
        int step = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(initial);
        Set<String> seen = new HashSet<String>();
        seen.add(initial);

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String status = queue.poll();
                for (String nextStatus : get773(status)) {
                    if (!seen.contains(nextStatus)) {
                        if ("123450".equals(nextStatus)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    // 枚举 status 通过一次交换操作得到的状态
    private List<String> get773(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        int x = status.indexOf('0');
        for (int y : neighbors[x]) {
            swap773(array, x, y);
            ret.add(new String(array));
            // 恢复状态
            swap773(array, x, y);
        }
        return ret;
    }

    private void swap773(char[] array, int x, int y) {
        char temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * 909、蛇梯棋 bfs
     */
    public static int snakesAndLadders(int[][] board) {

        /**
         * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
         * 例如，一块 6 x 6 大小的棋盘，编号如下：
         * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
         * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
         * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
         * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
         * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
         * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。
         * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
         * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
         *
         * 示例：
         * 输入：[
         * [-1,-1,-1,-1,-1,-1],
         * [-1,-1,-1,-1,-1,-1],
         * [-1,-1,-1,-1,-1,-1],
         * [-1,35,-1,-1,13,-1],
         * [-1,-1,-1,-1,-1,-1],
         * [-1,15,-1,-1,-1,-1]]
         * 输出：4
         * 解释：
         * 首先，从方格 1 [第 5 行，第 0 列] 开始。
         * 你决定移动到方格 2，并必须爬过梯子移动到到方格 15。
         * 然后你决定移动到方格 17 [第 3 行，第 5 列]，必须爬过蛇到方格 13。
         * 然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
         * 然后你决定移动到方格 36, 游戏结束。
         * 可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
         *
         * 提示：
         * 2 <= board.length = board[0].length <= 20
         * board[i][j] 介于 1 和 N*N 之间或者等于 -1。
         * 编号为 1 的方格上没有蛇或梯子。
         * 编号为 N*N 的方格上没有蛇或梯子。
         */

        /**
         * bfs
         * 模板：
         * 1、构建队列，初始状态加入到队列
         * 2、构建去重set，初始状态加入到set
         * 3、定义计数器
         * 4、遍历队列
         *    4.1、找到目标
         *    4.2、将产生的新状态加入到队列
         * 5、更新计数器
         *
         * 分析第4步骤：要模拟一个筛子，一个1-6的循环,能产生6种可能状态。有梯子，再飞一次。
         *
         * 关键注意点：
         * 1、怎么更好更方便地操作数据?也就是队列中存放的什么数据最方便。
         * 因为是数组，所以会先考虑能否使用坐标值，但实际情况是要根据骰子点数决定前进步数，包括遇到梯子直接飞到对应的位置上。
         * 所以要考虑坐标点、格子位置、移动步数的平衡。如果以存放的是坐标值，那么就要通过坐标值跟前进步数以及遇到梯子计算下一步
         * 的坐标。如果存放的是格子位置(1开始，N*N结束)，就要通过格子计算坐标，但是下一步位置会很好计算，直接相加即可。相对于坐标
         * 计算显然更方便一点。
         *
         * 2、通过格子位置计算坐标？
         * 格子是N*N的，可以通过格子位置来计算出坐标值。对于格子位置计算坐标：
         * 假如格子是6X6的，当前跳到第20个。将20转换成对应坐标。假设要求坐标是[x,y]。正常模式下(从上到下，从左到右)，y对应第
         * 几排，x对应某排中的哪一列。求第几排直接使用总的位置除以一排排几个即可。第几列其实就是看能不能排满一排，还剩几个，其实
         * 就是模运算。所以有下面结论：
         * 求x方向模运算，y方向除运算。
         * 但是注意啊，刚刚是正常模式下(从上到下，从左到右),格式排列的情况，二维数组的排列是有点差异的。
         * 1、二维数组aar[x][y]中x在排列是y方向，y是x方向。所以按照格子排列顺序下求的[x,y]当成坐标时是[y,x]；
         * 2、格子排列和数组0位置都是从左上角开始，直接除的结果对应着正常数组的y坐标，但起点是从左下角开始，所以结果的y也要取反；
         * 3、格子排列是从1开始，数组是从0开始计算，所以在进行除法，模运算要提前-1。比如在6X6的格子里，6是在第一排第6个，
         * 但在数组中表示是[0,5]，只有先将格子位置6先-1，再计算才能得到正确答案，否则将得出[1,0]的错误坐标。
         * 4、每一行的排列就是x轴方向并不是全都是从左到右，还有从右到左。但起点的x方向始终是从左到右，可以根据这点判断第y行的x排列方
         * 向，如果是反向的需要将x取反。
         *
         * 3、计数器更新的问题？
         * 通常情况下计算器在while{}内自增即可，但是对于这个题目来说，是存在梯子的，当它跨越了梯子后，最终结果比起正常while{}内计数
         * 器自增的结果是要小的，就是多加了步数。
         */

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); //构建数组类型，[0]表示格子位置，取值为1~N*N,[1]表示为到这个格子的步数。
        // 去重，为了与格子位置相匹配，长度+1。
        boolean[] set = new boolean[board.length * board.length + 1];

        while (!queue.isEmpty()) {
            int[] statue = queue.poll();
            for (int[] x : get909(statue[0], statue[1], board.length)) {

                // 格子位置转换成坐标
                int[] stepInt = getStep(x[0], board.length);
                // 判断是否有梯子
                if (board[stepInt[0]][stepInt[1]] != -1) { // 有梯子，飞到梯子上
                    x[0] = board[stepInt[0]][stepInt[1]];
                }
                //到达目标
                if (x[0] == board.length * board.length) {
                    return x[1] + 1; //为什么+1,因为这个是产生的新状态，是下一步的结果，是预处理了下一步。
                }
                //标记为已访问
                if (!set[x[0]]) {
                    set[x[0]] = true;
                    queue.offer(new int[]{x[0], x[1] + 1}); // 新状态放到队列
                }
            }
        }
        return -1;
    }

    private static List<int[]> get909(int x, int step, int size) {
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            if (x + i > size * size) break; // 避免超出 N*N范围
            int[] t = new int[]{x + i, step}; //这里暂时不能更新，因为不确定是否有梯子能到这里
            list.add(t);
        }
        return list;
    }

    private static int[] getStep(int index, int size) {
        //正常模式下求x、y坐标
        int y = (index - 1) / size;
        y = size - 1 - y; // 取反方向

        int x = (index - 1) % size;

//        if (y % 2 == 1) { // ？？？没懂为什么可以直接用y%2 == 1,如果是这样做，y不能提前取反。
//            x = size - 1 - x;
//        }

        // 最后一行必定从左到右，判断当前行奇偶性是否跟最后一行相同,不同则取反
        if ((y % 2) != ((size - 1) % 2)) {
            x = size - 1 - x;
        }
        return new int[]{y, x};
    }

    /**
     * 815、公交路线 bfs ?
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        /**
         * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
         * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
         * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
         * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
         *
         * 示例 1：
         * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
         * 输出：2
         * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
         *
         * 示例 2：
         * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
         * 输出：-1
         *
         * 提示：
         * 1 <= routes.length <= 500.
         * 1 <= routes[i].length <= 105
         * routes[i] 中的所有值 互不相同
         * sum(routes[i].length) <= 105
         * 0 <= routes[i][j] < 106
         * 0 <= source, target < 106
         *
         */
        return 0;
    }

    /**
     * 168、Excel表列名称(26进制)
     */
    public String convertToTitle(int columnNumber) {
        /**
         * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
         * 例如，
         *     1 -> A
         *     2 -> B
         *     3 -> C
         *     ...
         *     26 -> Z
         *     27 -> AA
         *     28 -> AB
         *     ...
         *
         * 示例 1:
         * 输入: 1
         * 输出: "A"
         *
         * 示例 2:
         * 输入: 28
         * 输出: "AB"
         *
         * 示例 2:
         * 输入: 54
         * 输出: "BB"
         *
         * 示例 3:
         * 输入: 701
         * 输出: "ZY"
         *
         * 示例 4：
         * 输入: 702
         * 输出: "ZZ"
         *
         * 示例 5：
         * 输入: 703
         * 输出: "AAA"
         */

        /**
         * 思路：26进制。
         * 说道26进制，思考下十进制是怎么做的?是不是先取模，得出个位数字，然后除以10，再取模，得到十位数字，依此类推。
         * 那么这个26进制也可以这么搞。不断取模除以26，每做一次除法，意味着位数+1，使用一个sb保存起来。但是由于这样计算是从低位
         * 到高位的，最终结果读时显示应该是从高位到低位，所以最后需要对sb反转。
         *
         * 困扰：10进制是从0-9,共有10个数字，此题是从1-26，而不是从0开始的。但是可以通过手段变成有0的情况：对于10进制有
         * 取值0-9,10个数字，除以10,那么在取模运算前先-1，如何?会变成取值0-25,26个数字，除以26。跟10进制的情况一模一样。
         * 定义0 = A，...,25=Z。将模的值转换成char即可。
         */

        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            // 提前-1，将整个数往前靠一位，整体变成[0-25]
            columnNumber--;
            char c = (char) ((columnNumber % 26) + 'A');
            sb.append(c);
            columnNumber /= 26;
        }
        sb = sb.reverse();
        return sb.toString();
    }

    /**
     * 1833. 雪糕的最大数量
     */
    public int maxIceCream(int[] costs, int coins) {

        /** 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
         * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有
         * coins 现金可以用于消费，他想要买尽可能多的雪糕。
         * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
         * 注意：Tony 可以按任意顺序购买雪糕。
         *
         * 示例 1：
         * 输入：costs = [1,3,2,4,1], coins = 7
         * 输出：4
         * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
         *
         * 示例 2：
         * 输入：costs = [10,6,8,7,7,8], coins = 5
         * 输出：0
         * 解释：Tony 没有足够的钱买任何一支雪糕。
         *
         * 示例 3：
         * 输入：costs = [1,6,3,1,2,5], coins = 20
         * 输出：6
         * 解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
         *
         * 提示：
         * costs.length == n
         * 1 <= n <= 105
         * 1 <= costs[i] <= 105
         * 1 <= coins <= 108
         */

        /**
         * 思路：
         * 看着跟01背包一样的，但是直接使用dp(01背包)会超时，而且数据量相对比较小，所以使用贪心，其实贪心也是dp的一种特殊情况。
         * 使用贪心算法；要买最多的雪糕，就应该从最便宜的开始买起。直到硬币不够了。
         */
        Arrays.sort(costs);
        int count = 0;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] <= coins) { // 钱够直接买
                count++;
                coins -= costs[i];
            } else
                break;
        }
        return count;
    }

    /**
     * 726. 原子的数量
     */
    public String countOfAtoms(String formula) {

        /**
         * 给定一个化学式formula（作为字符串），返回每种原子的数量。原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
         * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
         * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
         * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
         * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），
         * 跟着它的数量（如果数量大于 1），以此类推。
         * 示例 1:
         * 输入:
         * formula = "H2O"
         * 输出: "H2O"
         * 解释:
         * 原子的数量是 {'H': 2, 'O': 1}。
         *
         * 示例 2:
         * 输入:
         * formula = "Mg(OH)2"
         * 输出: "H2MgO2"
         * 解释:
         * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
         *
         * 示例 3:
         * 输入:
         * formula = "K4(ON(SO3)2)2"
         * 输出: "K4N2O14S4"
         * 解释:
         * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
         * 注意:
         *
         * 所有原子的第一个字母为大写，剩余字母都是小写。
         * formula的长度在[1, 1000]之间。
         * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
         */

        /**
         * 思路：
         * 利用栈保存()内的元素，从而整体地统计原子数量，最后使用TreeMap实现map的排序
         * 发现几个好用的api ：
         * Character.isDigit(char c) :判断该char是否为数字
         * Character.isLowerCase(char c) :判断该char是否为小写字母
         */

        //  "K4(ON(SO3)2)2"
        Stack<HashMap<String, Integer>> stack = new Stack<>();
        //先放入最外一层。
        stack.push(new HashMap<>());

        for (int i = 0; i < formula.length(); i = index) {
            if (formula.charAt(i) == '(') {
                index++;
                stack.push(new HashMap<>());
            } else if (formula.charAt(i) == ')') {
                index++;
                //获取数字
                int num = getNum726(formula);
                // 将当前()内的原子整合到外部，其实就是去()

                //当前()内的原子
                HashMap<String, Integer> in = stack.pop();
                //()外层的原子
                HashMap<String, Integer> out = stack.peek();
                for (String s : in.keySet()) {
                    out.put(s, num * in.get(s) + out.getOrDefault(s, 0));
                }
            } else {
                // 找到一个原子
                String key = get726(formula);
                //找到这个原子的数量
                int num = getNum726(formula);
                //放入栈
                HashMap<String, Integer> hashMap = stack.get(stack.size() - 1);
                hashMap.put(key, num + hashMap.getOrDefault(key, 0));
            }
        }

        // 排序map，使用TreeMap实现
        TreeMap<String, Integer> sortMap = new TreeMap<>(stack.pop());
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Integer> entry : sortMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            sb.append(key);
            if (value > 1) {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    int index;

    private String get726(String formula) {
        StringBuilder sb = new StringBuilder();
        // 取出第一个大写字符
        sb.append(formula.charAt(index));
        index++;
        while (index < formula.length() && Character.isLowerCase(formula.charAt(index))) {
            sb.append(formula.charAt(index));
            index++;
        }
        return sb.toString();
    }

    private int getNum726(String formula) {
        //不是数字，或者越界,认为是1
        if (index >= formula.length() || !Character.isDigit(formula.charAt(index))) {
            return 1;
        }
        StringBuilder sb = new StringBuilder();
        while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
            sb.append(formula.charAt(index));
            index++;
        }
        return Integer.valueOf(sb.toString());
    }


    /**
     * 1418、点菜展示表
     */
    public List<List<String>> displayTable(List<List<String>> orders) {

        /** 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，
         * 其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
         * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
         * 接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
         * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
         *
         * 示例 1：
         * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"]
         * ,["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
         * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],
         * ["10","1","0","0","0"]]
         * 解释：
         * 点菜展示表如下所示：
         * Table,Beef Burrito,Ceviche,Fried Chicken,Water
         * 3    ,0           ,2      ,1            ,0
         * 5    ,0           ,1      ,0            ,1
         * 10   ,1           ,0      ,0            ,0
         * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
         * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
         * 餐桌 10：Corina 点了 "Beef Burrito"
         *
         * 提示：
         * 1 <= orders.length <= 5 * 10^4
         * orders[i].length == 3
         * 1 <= customerNamei.length, foodItemi.length <= 20
         * customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
         * tableNumberi 是 1 到 500 范围内的整数。
         */


        // 获取餐品名称和桌号，统计每桌点餐数量
        Set<String> nameSet = new HashSet<>();
        Map<Integer, Map<String, Integer>> foodsCnt = new HashMap<>();
        for (List<String> order : orders) {
            nameSet.add(order.get(2));
            int id = Integer.parseInt(order.get(1));
            Map<String, Integer> map = foodsCnt.getOrDefault(id, new HashMap<>());
            map.put(order.get(2), map.getOrDefault(order.get(2), 0) + 1);
            foodsCnt.put(id, map);
        }

        // 提取餐品名称，并按字母顺序排列
        int n = nameSet.size();
        List<String> names = new ArrayList<String>();
        for (String name : nameSet) {
            names.add(name);
        }
        Collections.sort(names);

        // 提取桌号，并按餐桌桌号升序排列
        int m = foodsCnt.size();
        List<Integer> ids = new ArrayList<Integer>();
        for (int id : foodsCnt.keySet()) {
            ids.add(id);
        }
        Collections.sort(ids);

        // 填写点菜展示表
        List<List<String>> table = new ArrayList<List<String>>();
        List<String> header = new ArrayList<String>();
        header.add("Table");
        for (String name : names) {
            header.add(name);
        }
        table.add(header);
        for (int i = 0; i < m; ++i) {
            int id = ids.get(i);
            Map<String, Integer> cnt = foodsCnt.get(id);
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(id));
            for (int j = 0; j < n; ++j) {
                row.add(Integer.toString(cnt.getOrDefault(names.get(j), 0)));
            }
            table.add(row);
        }
        return table;
    }

    /**
     * 1711、 大餐计数 ?
     */
    public int countPairs(int[] deliciousness) {

        /**
         *  大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
         * 你可以搭配 任意 两道餐品做一顿大餐。
         * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。
         * 结果需要对 10^9 + 7 取余。注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
         *
         * 示例 1：
         * 输入：deliciousness = [1,3,5,7,9]
         * 输出：4
         * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
         * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
         * 示例 2：
         * 输入：deliciousness = [1,1,1,3,3,3,7]
         * 输出：15
         * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
         *
         * 提示：
         * 1 <= deliciousness.length <= 10^5
         * 0 <= deliciousness[i] <= 2^20
         */

        /**
         * 暴力双重循环直接超时。太秀了
         */
        int result = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                int count = deliciousness[i] + deliciousness[j];
                if (count != 0 && (count & (count - 1)) == 0) {
                    result++;
                    result %= 1000000007; // 防止越界?
                }
            }
        }
        return result;
    }

    /**
     * 930、 和相同的二元子数组 ? 滑动窗口
     */
    public int numSubarraysWithSum(int[] nums, int goal) {

        /** 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
         * 子数组 是数组的一段连续部分。
         *
         * 示例 1：
         * 输入：nums = [1,0,1,0,1], goal = 2
         * 输出：4
         * 解释：
         * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
         *
         * 示例 2：
         * 输入：nums = [0,0,0,0,0], goal = 0
         * 输出：15
         *
         * 提示：
         * 1 <= nums.length <= 3 * 104
         * nums[i] 不是 0 就是 1
         * 0 <= goal <= nums.length
         */
        return 0;
    }

    /**
     * 面试题 17.10. 主要元素
     */
    public int majorityElement(int[] nums) {
        /**
         * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、
         * 空间复杂度为 O(1) 的解决方案。
         *
         * 示例 1：
         *
         * 输入：[1,2,5,9,5,9,5,5,5]
         * 输出：5
         *
         * 示例 2：
         * 输入：[3,2]
         * 输出：-1
         *
         * 示例 3：
         * 输入：[2,2,1,1,1,2,2]
         * 输出：2
         */

        /**
         * 思路
         * 参考求众数的方法：摩尔投票。
         * 求得众数后，判断该数量是否超过总元素数量一半。
         */

        int more = 0; // 保存众数
        int count = 0; // 当前众数计数器
        for (int i : nums) {
            if (count == 0) {
                more = i;
            }
            if (i == more) {
                count++;
            } else {
                count--;
            }
        }
        count = 0; // 众数数量
        for (int i : nums) {
            if (i == more) {
                count++;
            }
        }
        return count > nums.length / 2 ? more : 0;
    }

    /**
     * 274. H 指数
     */
    public static int hIndex(int[] citations) {

        /**
         *  给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
         * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了
         * 至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
         *
         * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
         *
         * 示例：
         * 输入：citations = [3,0,6,1,5]
         * 输出：3
         * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
         *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
         *
         * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
         */

        /**
         * 思路：
         * 关键点在在N篇中，有n篇论文大于n次，剩下(N-n)篇论文小于n次。
         * 可以假定当前数组[i]就是h，然后在数组中找到值大于等于h的数量是不是等于h。然后在满足条件的所有h中选一个最大的值返回。
         *
         */

        int maxH = 0, c1;
        for (int i = 1; i <= citations.length; i++) {
            c1 = 0;
            for (int j = 0; j < citations.length; j++) {
                if (citations[j] >= i) c1++;
            }

            if (i <= c1) maxH = Math.max(maxH, i);
        }
        return maxH;

        // 效率高解法：
//        Arrays.sort(citations);
//        for (int i = 0; i < citations.length; i++) {
//            int h = citations.length - i;
//            if (h <= citations[i]) {
//                return h;
//            }
//        }
//        return 0;
    }

    /**
     * 1818. 绝对差值和 ?
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        /**
         *  给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
         * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
         * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
         * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
         * |x| 定义为：
         * 如果 x >= 0 ，值为 x ，或者
         * 如果 x <= 0 ，值为 -x
         *
         * 示例 1：
         * 输入：nums1 = [1,7,5], nums2 = [2,3,5]
         * 输出：3
         * 解释：有两种可能的最优方案：
         * - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
         * - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
         * 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
         *
         * 示例 2：
         * 输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
         * 输出：0
         * 解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
         *
         * 示例 3：
         * 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
         * 输出：20
         * 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
         * 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
         *
         * 提示：
         * n == nums1.length
         * n == nums2.length
         * 1 <= n <= 105
         * 1 <= nums1[i], nums2[i] <= 105
         */
        return 0;
    }

    /**
     * 1846. 减小和重新排列数组后的最大元素
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        /**
         *  给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
         *
         * arr 中 第一个 元素必须为 1 。
         * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足
         * abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
         * 你可以执行以下 2 种操作任意次：
         *
         * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
         * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
         * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
         *
         * 示例 1：
         * 输入：arr = [2,2,1,2,1] [11222]
         * 输出：2
         * 解释：
         * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
         * arr 中最大元素为 2 。
         *
         * 示例 2：
         * 输出：3
         * 解释：
         * 一个可行的方案如下：
         * 1. 重新排列 arr 得到 [1,100,1000] 。
         * 2. 将第二个元素减小为 2 。
         * 3. 将第三个元素减小为 3 。
         * 现在 arr = [1,2,3] ，满足所有条件。
         * arr 中最大元素为 3 。
         *
         * 示例 3：
         * 输入：arr = [1,2,3,4,5]
         * 输出：5
         * 解释：数组已经满足所有条件，最大元素为 5 。
         *
         * 提示：
         * 1 <= arr.length <= 105
         * 1 <= arr[i] <= 109
         */

        /**
         * 思路：朴素解法 [排序+贪心]
         * 根据题意，保证前后位置的差值绝对值为1.那么最理想的情况下就是最大值等于数组长度。而实际上不一定能取到数组长度的最大值。
         * 所以排序数组，数组第一个数为1。后面的元素只要比前一个元素大，就重新赋值，同时保存当前的最大值。
         */

        if (arr.length == 1) return 1;
        Arrays.sort(arr);
        arr[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 0) {
                arr[i] = arr[i - 1] + 1;
            } else {
                arr[i] = arr[i - 1];
            }
        }
        return arr[arr.length - 1];
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     */
    public int search34(int[] nums, int target) {
        /**
         * 统计一个数字在排序数组中出现的次数。
         * 示例 1:
         * 输入: nums = [5,7,7,8,8,10], target = 8
         * 输出: 2
         *
         * 示例 2:
         * 输入: nums = [5,7,7,8,8,10], target = 6
         * 输出: 0
         *
         * 限制：
         * 0 <= 数组长度 <= 50000
         */

        /**
         * 思路：写二分法
         */

        // 只找一边写法。缺陷是如果left = 0，时间复杂度等于O(n)
        int count = 0;
        int left = get53Left(nums, target);
        for (int i = left; i < nums.length; i++) {
            if (nums[i] == target) count++;
            else break;
        }
        return count;
    }

    private int get53Left(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 剑指 Offer 42、连续子数组的最大和 (53)
     */
    public int maxSubArray(int[] nums) {
        /**
         * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
         * 要求时间复杂度为O(n)。
         *
         * 示例1:
         * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
         * 输出: 6
         * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
         *
         * 提示：
         * 1 <= arr.length <= 10^5
         * -100 <= arr[i] <= 100
         */

        /**
         * 思路：dp
         * 步骤：
         * 1、定义dp数组
         *      dp[i]表示连续子数组到i时的最大和。(ps：这点很重要，否则会直接影响到后续转移方程的计算。题目要求的连续子数组，所以对于这个数组与当前i
         *      位置的元素来说只有2种情况，将i归类到原连续数组中，第二是不归类到原连续数组，而是另起连续数组，以i位置元素开始)所以最终结果不是返回
         *      dp[i]。需要通过比较保存最大的。
         * 2、确定base case
         *      当只有一个元素时，和最大就是元素本身。dp[0] = nums[0]
         * 3、转移方程
         *      对于当前位置i的元素在连续数组中，有2种情况考虑。1、连续数组加上nums[i]，即 dp[i] = dp[i-1] + nums[i];2、连续数组不加nums[i],反而
         *      放弃原连续数组，以nums[i]开始另立一个连续子数组。即 dp[i] = nums[i];二者取最大值。
         * 4、边界条件
         *
         * 5、状态压缩
         *      发现无论何时转移方程只与dp[i-1]、dp[i]相关，可以考虑使用两个变量替换，将空间从O(n)->O(1)
         */

        // 未优化空间版dp
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; // 题目声明 nums.length >= 1
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        // 优化空间后
        // int pre, aft,max = nums[0];
        // pre = nums[0];
        // for (int i = 1; i < nums.length; i++) {
        //     aft = Math.max(pre + nums[i], nums[i]);
        //     pre = aft;
        //     max = Math.max(max, aft);
        // }

        return max;
    }

    /**
     * 面试题 10.02、变位词组
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        /**
         * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
         * 注意：本题相对原题稍作修改
         *
         * 示例:
         * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
         * 输出:
         * [
         *   ["ate","eat","tea"],
         *   ["nat","tan"],
         *   ["bat"]
         * ]
         *
         * 说明：
         * 所有输入均为小写字母。
         * 不考虑答案输出的顺序。
         */

        /**
         * 思路：
         * 题目意思是将所有字符相同的组成的不同字符串归为一类。官方思路是：既然所有字符都一样，那么不同字符串排序后的字符串肯定
         * 是一样的，可以以此作为key，存放相同字符组成的字符串。关键点排序。
         */
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 1877. 数组中最大数对和的最小值
     */
    public int minPairSum(int[] nums) {


        /** 一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
         * 比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
         * 给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
         * nums 中每个元素 恰好 在 一个 数对中，且
         * 最大数对和 的值 最小 。
         * 请你在最优数对划分的方案下，返回最小的最大数对和 。
         *
         * 示例 1：
         * 输入：nums = [3,5,2,3]
         * 输出：7
         * 解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
         * 最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
         *
         * 示例 2：
         * 输入：nums = [3,5,4,2,4,6]
         * 输出：8
         * 解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
         * 最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。
         *
         * 提示：
         * n == nums.length
         * 2 <= n <= 105
         * n 是 偶数 。
         * 1 <= nums[i] <= 105
         */

        /**
         * 思路：(带点贪心味道)
         * 要让最大的数对和最小，最好的办法就是让这些数对尽可能的平均下来。既然说到平均了，那肯定是排序啊。这样保证了最小。
         * 然后在通过首尾组成数对求数对和，保存最大值。
         */

        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            max = Math.max(max, nums[i] + nums[nums.length - 1 - i]);
        }
        return max;
    }

    /**
     * 863、二叉树中所有距离为 K 的结点
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        /**
         *  给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
         * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
         *
         * 示例 1：
         * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
         * 输出：[7,4,1]
         * 解释：
         * 所求结点为与目标结点（值为 5）距离为 2 的结点，
         * 值分别为 7，4，以及 1
         *
         * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
         * 上面的输入仅仅是对这些对象进行了序列化描述。
         *
         * 提示：
         * 给定的树是非空的。
         * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
         * 目标结点 target 是树上的结点。
         * 0 <= K <= 1000.
         */

        /**
         * 思路：
         * 使用map创建连接表,节点值都是唯一的，可以作为key，因为可能存在向上寻找，需要保存当前节点的父节点。
         */

        HashMap<Integer, TreeNode> map = new HashMap();
        dfs863(root, map);
        List<Integer> list = new ArrayList<>();
        dfs863Count(target, 0, null, list, map, k);
        return list;
    }

    private void dfs863(TreeNode root, HashMap<Integer, TreeNode> map) {
        if (root == null) return;

        if (root.left != null) {
            map.put(root.left.val, root);
            dfs863(root.left, map);
        }

        if (root.right != null) {
            map.put(root.right.val, root);
            dfs863(root.right, map);
        }

    }

    private void dfs863Count(TreeNode target, int cur, TreeNode last, List<Integer> list, HashMap<Integer, TreeNode> map, int k) {
        if (target == null) return;

        if (cur == k) {
            list.add(target.val);
        }

        //左边
        if (target.left != last) // 防止重新访问到自己
            dfs863Count(target.left, cur + 1, target, list, map, k);
        //右边
        if (target.right != last)
            dfs863Count(target.right, cur + 1, target, list, map, k);
        //往上
        if (map.get(target.val) != last)//?
            dfs863Count(map.get(target.val), cur + 1, target, list, map, k);
    }

    /**
     * 171、 Excel表列序号
     */
    public int titleToNumber(String columnTitle) {
        /**
         *  给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
         * 例如，
         *     A -> 1
         *     B -> 2
         *     C -> 3
         *     ...
         *     Z -> 26
         *     AA -> 27
         *     AB -> 28
         *     ...
         *
         * 示例 1:
         * 输入: columnTitle = "A"
         * 输出: 1
         * 示例 2:
         * 输入: columnTitle = "AB"
         * 输出: 28
         * 示例 3:
         * 输入: columnTitle = "ZY"
         * 输出: 701
         * 示例 4:
         * 输入: columnTitle = "FXSHRXW"
         * 输出: 2147483647
         *
         * 提示：
         * 1 <= columnTitle.length <= 7
         * columnTitle 仅由大写英文组成
         * columnTitle 在范围 ["A", "FXSHRXW"] 内
         */

        /**
         * 思路：题目还是比较简单的，之前做过一个反过来的题目(168、Excel表列名称),可以参考的就是10进制的思路。
         * 从题目得到有用信息有：字符串长度代表位数，每一位大小26。满26进1。联系10进制的算法，可以从字符串的末端开始
         * 计算，首先是“个位”，是多少就是多少。往后每增加一位，进制都要乘以26，再与位数上的数相乘，最后相加低位和。
         */

        int sub = 0;
        int count = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int k = columnTitle.charAt(i) - 'A' + 1;
            sub += k * count;
            count *= 26;
        }
        return sub;
    }

    /**
     * 987、 二叉树的垂序遍历
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        /**
         *  给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
         *
         * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
         * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列
         * 上有多个结点，则按结点的值从小到大进行排序。
         * 返回二叉树的 垂序遍历 序列。
         *
         * 示例 1：
         * 输入：root = [3,9,20,null,null,15,7]
         * 输出：[[9],[3,15],[20],[7]]
         * 解释：
         * 列 -1 ：只有结点 9 在此列中。
         * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
         * 列  1 ：只有结点 20 在此列中。
         * 列  2 ：只有结点 7 在此列中。
         *
         * 示例 2：
         * 输入：root = [1,2,3,4,5,6,7]
         * 输出：[[4],[2],[1,5,6],[3],[7]]
         * 解释：
         * 列 -2 ：只有结点 4 在此列中。
         * 列 -1 ：只有结点 2 在此列中。
         * 列  0 ：结点 1 、5 和 6 都在此列中。
         *           1 在上面，所以它出现在前面。
         *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
         * 列  1 ：只有结点 3 在此列中。
         * 列  2 ：只有结点 7 在此列中。
         *
         * 示例 3：
         * 输入：root = [1,2,3,4,6,5,7]
         * 输出：[[4],[2],[1,5,6],[3],[7]]
         * 解释：
         * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
         * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
         *
         * 提示：
         * 树中结点数目总数在范围 [1, 1000] 内
         * 0 <= Node.val <= 1000
         */

        /**
         * 思路：哈希表+排序。排序规则：先排列号，从小到大，相同则按行号从小到大；再排行号，相同则按value从小到大
         */

        map.put(root, new int[]{0, 0, root.val});
        dfs987(root);

        List<int[]> list = new ArrayList<>(map.values());
        Collections.sort(list, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // 列号排序
            if (a[1] != b[1]) return a[1] - b[1]; // 行号排序
            return a[2] - b[2]; // 内容排序
        });

        int n = list.size();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ) {
            int j = i;
            List<Integer> tmp = new ArrayList<>();
            while (j < n && list.get(j)[0] == list.get(i)[0]) tmp.add(list.get(j++)[2]);
            ans.add(tmp);
            i = j;
        }
        return ans;
    }

    Map<TreeNode, int[]> map = new HashMap<>(); // col, row, val

    private void dfs987(TreeNode root) {
        if (root == null) return;
        int[] info = map.get(root);
        int col = info[0], row = info[1], val = info[2];
        if (root.left != null) {
            map.put(root.left, new int[]{col - 1, row + 1, root.left.val});
            dfs987(root.left);
        }
        if (root.right != null) {
            map.put(root.right, new int[]{col + 1, row + 1, root.right.val});
            dfs987(root.right);
        }
    }

    /**
     * 1337、 矩阵中战斗力最弱的 K 行
     */
    public int[] kWeakestRows(int[][] mat, int k) {

        /**
         *  给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
         * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
         * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
         * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
         *
         * 示例 1：
         * 输入：mat =
         * [[1,1,0,0,0],
         *  [1,1,1,1,0],
         *  [1,0,0,0,0],
         *  [1,1,0,0,0],
         *  [1,1,1,1,1]],
         * k = 3
         * 输出：[2,0,3]
         * 解释：
         * 每行中的军人数目：
         * 行 0 -> 2
         * 行 1 -> 4
         * 行 2 -> 1
         * 行 3 -> 2
         * 行 4 -> 5
         * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
         *
         * 提示：
         * m == mat.length
         * n == mat[i].length
         * 2 <= n, m <= 100
         * 1 <= k <= m
         * matrix[i][j] 不是 0 就是 1
         */

        /**
         * 思路：题目很简单，将二维数组中每个数组内的1的数量相加起来比较，越小越弱；如果相同，则看第一维下标，越小越弱。
         * 最后输出第一维下标组成的数组。这里涉及到2个比较指标，可以创建二维数组实现，一维表示1的数量，另一维表示对应的下标，然后
         * 通过Arrays的比较方法完成排序。最后将这个二维数组中的下标值取出变成1维数组即可。
         *
         * tips:如果有比较3、4个指标，可以相对的增大二维数组中数组的长度
         */

        // 创建排序列表
        int[][] all = new int[mat.length][2]; // 2是指2个指标，1的数量与对应下标。
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[0].length; j++) {
                count += mat[i][j];
            }
            all[i] = new int[]{count, i};
        }

        // 排序
        Arrays.sort(all, (o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        // 整理
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = all[i][1];
        }
        return result;
    }

    /**
     * 743、网络延迟时间 ???
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        /**
         * 有n个网络节点，标记为 1 到 n。
         *
         * 给你一个列表times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点，wi
         * 是一个信号从源节点传递到目标节点的时间。
         * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
         *
         * 示例 1：
         * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
         * 输出：2
         *
         * 示例 2：
         * 输入：times = [[1,2,1]], n = 2, k = 1
         * 输出：1
         *
         * 示例 3：
         * 输入：times = [[1,2,1]], n = 2, k = 2
         * 输出：-1
         *
         * 提示：
         * 1 <= k <= n <= 100
         * 1 <= times.length <= 6000
         * times[i].length == 3
         * 1 <= ui, vi <= n
         * ui != vi
         * 0 <= wi <= 100
         * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
         */

        final int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<int[]>();
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x].add(new int[]{y, t[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int time = p[0], x = p[1];
            if (dist[x] < time) {
                continue;
            }
            for (int[] e : g[x]) {
                int y = e[0], d = dist[x] + e[1];
                if (d < dist[y]) {
                    dist[y] = d;
                    pq.offer(new int[]{d, y});
                }
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    /**
     * 581、 最短无序连续子数组
     */
    public int findUnsortedSubarray(int[] nums) {
        /**
         *  给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
         * 请你找出符合题意的 最短 子数组，并输出它的长度。
         *
         * 示例 1：
         * 输入：nums = [2,6,4,8,10,9,15]
         * 输出：5
         * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
         *
         * 示例 2：
         * 输入：nums = [1,2,3,4]
         * 输出：0
         *
         * 示例 3：
         * 输入：nums = [1]
         * 输出：0
         *
         * 提示：
         * 1 <= nums.length <= 104
         * -105 <= nums[i] <= 105
         */
        if (nums.length <= 1) return 0;

        /**
         * 思路：
         * 整个表都要是升序的，那么从左到右，或者从右到左，必然右边的数比左边的大；如果出现左边比右边大的数那么这个数就是要调整的位置。
         * 这样一来左右2边各确定一个位置，中间的数都是需要调整的，统计个数即可。注意，从左到右比较最大值，表示此值右边都是升序的；从右到左比
         * 较最小值，表示此值左边都是升序的。这点理解很重要。
         */

        int left = 0;
        int right = -1; // 为什么要等于-1，因为如果是一个升序数组的话，left，right都不会被赋值，(right - left + 1)结果却等于了1。
        int max = nums[0];
        int min = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            //从左到右
            if (nums[i] < max) { //正常顺序是要比max大
                right = i;
            } else {
                max = nums[i];
            }

            // 从右到左
            if (nums[nums.length - 1 - i] > min) { //正常顺序是要比min小
                left = nums.length - 1 - i;
            } else {
                min = nums[nums.length - 1 - i];
            }

        }

        return right - left + 1;
    }

    /**
     * 611、有效三角形的个数
     */
    public int triangleNumber(int[] nums) {

        /**
         * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
         *
         * 示例 1:
         * 输入: [2,2,3,4]
         * 输出: 3
         * 解释:
         * 有效的组合是:
         * 2,3,4 (使用第一个 2)
         * 2,3,4 (使用第二个 2)
         * 2,2,3
         * 注意:
         *
         * 数组长度不超过1000。
         * 数组里整数的范围为 [0, 1000]。
         */

        int count = 0;
//        for (int i = 0; i < nums.length - 2; i++) {
//            for (int j = i + 1; j < nums.length - 1; j++) {
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (nums[i] + nums[j] > nums[k]
//                            && nums[i] + nums[k] > nums[j]
//                            && nums[j] + nums[k] > nums[i]) {
//                        count++;
//                    }
//                }
//            }
//        }

        // 上面的方式直接超时，处理办法为排序后使用倒序，因为这样可以确定大小关系。
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j++) {
                for (int k = j - 1; k >= 0; k++) {
                    if (nums[i] + nums[j] > nums[k]) count++;
                }
            }
        }

        return count;
    }

    /**
     * 802、找到最终的安全状态
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {

        /**
         * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
         * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
         * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
         * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
         * 满足 (i, j) 是图的一条有向边。
         *
         * 示例 1：
         * Illustration of graph
         * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
         * 输出：[2,4,5,6]
         * 解释：示意图如上。
         *
         * 示例 2：
         * 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
         * 输出：[4]
         *
         * 提示：
         * n == graph.length
         * 1 <= n <= 104
         * 0 <= graph[i].length <= n
         * graph[i] 按严格递增顺序排列。
         * 图中可能包含自环。
         * 图中边的数目在范围 [1, 4 * 104] 内。
         */

        /**
         * 思路：递归+备忘录(标识)，具体看注释。
         */

        // 声明备忘录： 0-默认情况，也就是未被访问过；1-非安全节点或访问中的节点(环) ；2-安全节点
        int[] visit = new int[graph.length];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            if (dfs802(graph, visit, i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean dfs802(int[][] graph, int[] visitNode, int i) {
        // 递归终止条件：访问过并且这个节点是安全节点；非安全节点或处于遍历中(大概率是成环了)；
        if (visitNode[i] == 1) return false;
        if (visitNode[i] == 2) return true;

        visitNode[i] = 1; //标记成访问中
        // 递归该节点的有向边
        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs802(graph, visitNode, graph[i][j])) { // 有一条不能走到终点，必然不是安全节点，直接返回false。
                return false;
            }
        }
        // 所有路径都能走到终点，表示这是一个安全节点，添加标识，返回true。
        visitNode[i] = 2;
        return true;
    }

    /**
     * 847、访问所有节点的最短路径
     */
    public int shortestPathLength(int[][] graph) {
        /**
         * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
         * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
         * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
         *
         * 示例 1：
         * 输出：4
         * 解释：一种可能的路径为 [1,0,2,0,3]
         *
         * 示例 2：
         * 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
         * 输出：4
         * 解释：一种可能的路径为 [0,1,4,2,3]
         *
         * 提示：
         * n == graph.length
         * 1 <= n <= 12
         * 0 <= graph[i].length < n
         * graph[i] 不包含 i
         * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
         * 输入的图总是连通图
         */
        return 0;
    }

    /**
     * 457、环形数组是否存在循环
     */
    public static boolean circularArrayLoop(int[] nums) {
        /**
         * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
         * 如果 nums[i] 是正数，向前（下标递增方向）移动 |nums[i]| 步
         * 如果 nums[i] 是负数，向后（下标递减方向）移动 |nums[i]| 步
         * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
         * 数组中的 循环 由长度为 k 的下标序列 seq 标识：
         * 遵循上述移动规则将导致一组重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
         * 所有 nums[seq[j]] 应当不是 全正 就是 全负
         * k > 1
         * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
         *
         * 示例 1：
         * 输入：nums = [2,-1,1,2,2]     4
         * 输出：true
         * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
         *
         * 示例 2：
         * 输入：nums = [-1,2]
         * 输出：false
         * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
         *
         * 示例 3:
         * 输入：nums = [-2,1,-1,-2,-2]
         * 输出：false
         * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
         * 所有 nums[seq[j]] 应当不是全正就是全负。
         *
         * 提示：
         * 1 <= nums.length <= 5000
         * -1000 <= nums[i] <= 1000
         * nums[i] != 0
         */

        for (int i = 0; i < nums.length; i++) {
            //1、 递归超时，应该没有大问题了。
            if (dfs457(nums, i)) {
                return true;
            }
            //2、快慢指针
            if (s457(nums, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfs457(int[] nums, int index) {
        List<Integer> list = new ArrayList<>();
        int size = nums.length;
        list.add(index);
        while (true) {
            int i = nums[index];
            if (i > 0)
                index = (index + i) % size;
            else {
                i = (-i) % size;
                index = (index - i) >= 0 ? index - i : index - i + size;
            }

            if (list.contains(index)) break;
            else list.add(index);
        }
        if (list.size() <= 1) return false;

        int start = list.indexOf(index);
        int count = 0;
        for (int i = start; i < list.size(); i++) {
            if (nums[start] > 0 != nums[list.get(i)] > 0) {
                return false;
            }
            count++;
        }
        return count > 1;
    }

    private static boolean s457(int[] nums, int index) {
        int slow = index;
        int fast = next457(index, nums); // 快指针要提前走一步，否则一开始slow就等于fast了。

        //判断是否都是大于0或者小于0，注意快指针一次走2步，每一步都是要判断的
        while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next457(fast, nums)] > 0) {
            if (slow == fast) {
                //表示有环，但是要排除k=1的情况。判断依据就是如果k==1的环，那么它的下一步还
                // 是等于它自己，可以让慢指针再走一步判断。
                if (slow == next457(slow, nums)) return false;
                else return true;
            }
            slow = next457(slow, nums); // 慢指针一步一步走。
            fast = next457(next457(fast, nums), nums); // 快指针走2步
        }
        return false;
    }

    private static int next457(int i, int[] nums) {
        // 注意正数与负数情况的到下一步的下标的数不能是负数。也不能越界，所以需要取模处理。
        // 正负数相加，因为i是负的，本质是做减法，但是可以当做正数来处理，最终结果取反即可。如:-3 % 4 =-3。取反就是补上一个数组长
        // 度得到3对应的位置。但是加上数组长度可能越界，还要再取模。这样还包括了正数的情况，可以同一处理
        return ((i + nums[i]) % nums.length + nums.length) % nums.length;
    }

    /**
     * 1137、 第N个泰波那契数
     */
    public int tribonacci(int n) {
        /**
         * 泰波那契序列 Tn 定义如下：
         * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
         * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
         *
         * 示例 1：
         * 输入：n = 4
         * 输出：4
         * 解释：
         * T_3 = 0 + 1 + 1 = 2
         * T_4 = 1 + 1 + 2 = 4
         *
         * 示例 2：
         * 输入：n = 25
         * 输出：1389537
         *
         * 提示：
         * 0 <= n <= 37
         * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
         */

        /**
         * 思路：斐波那契数列思想(动态规划、模拟)
         * 联想到斐波那契数列: F(N) = F(N - 1) + F(N - 2), 其中 N > 1。后一个数等于前3个数的和。因为给出了转移方程
         * 直接模拟就可以了。
         */
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int a = 0, b = 1, c = 1;
        for (int i = 3; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    /**
     * 313、 超级丑数
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        /**
         *  超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
         * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
         * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
         *
         * 示例 1：
         * 输入：n = 12, primes = [2,7,13,19]
         * 输出：32
         * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
         * 示例 2：
         * 输入：n = 1, primes = [2,3,5]
         * 输出：1
         * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
         * 提示：
         * 1 <= n <= 106
         * 1 <= primes.length <= 100
         * 2 <= primes[i] <= 1000
         * 题目数据 保证 primes[i] 是一个质数
         * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
         */


        return 0;
    }

    /**
     * 413、等差数列划分
     */
    public int numberOfArithmeticSlices(int[] nums) {

        /** 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
         *
         * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
         * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
         *
         * 子数组 是数组中的一个连续序列。
         *
         *
         *
         * 示例 1：
         *
         * 输入：nums = [1,2,3,4]
         * 输出：3
         * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
         * 示例 2：
         *
         * 输入：nums = [1]
         * 输出：0
         *
         *
         * 提示：
         *
         * 1 <= nums.length <= 5000
         * -1000 <= nums[i] <= 1000
         */
        return 0;
    }

    /**
     * 446、等差数列划分 II - 子序列
     */
    public int numberOfArithmeticSlicesII(int[] nums) {
        /**
         * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
         *
         * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
         *
         * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
         * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
         * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
         *
         * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
         * 题目数据保证答案是一个 32-bit 整数。
         *
         *  
         *
         * 示例 1：
         *
         * 输入：nums = [2,4,6,8,10]
         * 输出：7
         * 解释：所有的等差子序列为：
         * [2,4,6]
         * [4,6,8]
         * [6,8,10]
         * [2,4,6,8]
         * [4,6,8,10]
         * [2,4,6,8,10]
         * [2,6,10]
         * 示例 2：
         *
         * 输入：nums = [7,7,7,7,7]
         * 输出：16
         * 解释：数组中的任意子序列都是等差子序列。
         *  
         *
         * 提示：
         *
         * 1  <= nums.length <= 1000
         * -231 <= nums[i] <= 231 - 1
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        int ans = 0;
        int n = nums.length;
        Map<Long, Integer>[] f = new Map[n];
        for (int i = 0; i < n; ++i) {
            f[i] = new HashMap<Long, Integer>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = 1L * nums[i] - nums[j];
                int cnt = f[j].getOrDefault(d, 0);
                ans += cnt;
                f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1);
            }
        }
        return ans;
    }

    /**
     * 516、最长回文子序列
     */
    public int longestPalindromeSubseq(String s) {
        /**
         *  给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
         * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
         *
         * 示例 1：
         * 输入：s = "bbbab"
         * 输出：4
         * 解释：一个可能的最长回文子序列为 "bbbb" 。
         * 示例 2：
         * 输入：s = "cbbd"
         * 输出：2
         * 解释：一个可能的最长回文子序列为 "bb" 。
         *
         * 提示：
         * 1 <= s.length <= 1000
         * s 仅由小写英文字母组成
         */
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 233、数字1的个数
     */
    public int countDigitOne(int n) {
        /**
         * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
         * 示例 1：
         *
         * 输入：n = 13
         * 输出：6
         * 示例 2：
         *
         * 输入：n = 0
         * 输出：0
         *
         * 提示：
         * 0 <= n <= 109
         */

        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

    /**
     * 1583、统计不开心的朋友
     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        /**
         *  给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
         * 对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。换句话说，排在列表前面的朋友与 i 的亲近程度比排在列表后面的朋友更高。
         * 每个列表中的朋友均以 0 到 n-1 之间的整数表示。
         * 所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对。
         * 但是，这样的配对情况可能会使其中部分朋友感到不开心。在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，x 就会不开心：
         *
         * x 与 u 的亲近程度胜过 x 与 y，且
         * u 与 x 的亲近程度胜过 u 与 v
         * 返回 不开心的朋友的数目 。
         *
         * 示例 1：
         * 输入：n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
         * 输出：2
         * 解释：
         * 朋友 1 不开心，因为：
         * - 1 与 0 配对，但 1 与 3 的亲近程度比 1 与 0 高，且
         * - 3 与 1 的亲近程度比 3 与 2 高。
         * 朋友 3 不开心，因为：
         * - 3 与 2 配对，但 3 与 1 的亲近程度比 3 与 2 高，且
         * - 1 与 3 的亲近程度比 1 与 0 高。
         * 朋友 0 和 2 都是开心的。
         * 示例 2：
         * 输入：n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
         * 输出：0
         * 解释：朋友 0 和 1 都开心。
         * 示例 3：
         * 输入：n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
         * 输出：4
         *
         *
         * 提示：
         *
         * 2 <= n <= 500
         * n 是偶数
         * preferences.length == n
         * preferences[i].length == n - 1
         * 0 <= preferences[i][j] <= n - 1
         * preferences[i] 不包含 i
         * preferences[i] 中的所有值都是独一无二的
         * pairs.length == n/2
         * pairs[i].length == 2
         * xi != yi
         * 0 <= xi, yi <= n - 1
         * 每位朋友都 恰好 被包含在一对中
         */
        return 0;
    }

    /**
     * 576、出界的路径数
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        /**
         *  给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内
         * （可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
         * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余
         * 后的结果。
         *
         * 示例 1：
         * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
         * 输出：6
         * 示例 2：
         * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
         * 输出：12
         * 提示：
         *
         * 1 <= m, n <= 50
         * 0 <= maxMove <= 50
         * 0 <= startRow < m
         * 0 <= startColumn < n
         */
        return 0;
    }

    /**
     * 526、优美的排列
     */
    public int countArrangement(int n) {
        /**
         *  假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
         * perm[i] 能够被 i 整除
         * i 能够被 perm[i] 整除
         * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
         *
         * 示例 1：
         *
         * 输入：n = 2
         * 输出：2
         * 解释：
         * 第 1 个优美的排列是 [1,2]：
         *     - perm[1] = 1 能被 i = 1 整除
         *     - perm[2] = 2 能被 i = 2 整除
         * 第 2 个优美的排列是 [2,1]:
         *     - perm[1] = 2 能被 i = 1 整除
         *     - i = 2 能被 perm[2] = 1 整除
         * 示例 2：
         * 输入：n = 1
         * 输出：1
         *
         * 提示：
         * 1 <= n <= 15
         */
        return 0;
    }

    /**
     * 551、学生出勤记录I
     */
    public boolean checkRecord(String s) {
        /**
         *  给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
         *
         * 'A'：Absent，缺勤
         * 'L'：Late，迟到
         * 'P'：Present，到场
         * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
         *
         * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
         * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
         * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
         *
         *
         *
         * 示例 1：
         *
         * 输入：s = "PPALLP"
         * 输出：true
         * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
         * 示例 2：
         *
         * 输入：s = "PPALLL"
         * 输出：false
         * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
         *
         *
         * 提示：
         *
         * 1 <= s.length <= 1000
         * s[i] 为 'A'、'L' 或 'P'
         */
        return false;
    }

    /**
     * 552、学生出勤记录II
     */
    public int checkRecord(int n) {
        /**
         *  可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
         * 'A'：Absent，缺勤
         * 'L'：Late，迟到
         * 'P'：Present，到场
         * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
         *
         * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
         * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
         * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
         *
         *
         *
         * 示例 1：
         *
         * 输入：n = 2
         * 输出：8
         * 解释：
         * 有 8 种长度为 2 的记录将被视为可奖励：
         * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
         * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
         * 示例 2：
         *
         * 输入：n = 1
         * 输出：3
         * 示例 3：
         *
         * 输入：n = 10101
         * 输出：183236316
         *
         *
         * 提示：
         *
         * 1 <= n <= 105
         */
        return 0;
    }

    /**
     * 345、反转字符串中的元音字母
     */
    public String reverseVowels(String s) {
        /**
         *  给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
         *
         * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
         * 示例 1：
         *
         * 输入：s = "hello"
         * 输出："holle"
         * 示例 2：
         *
         * 输入：s = "leetcode"
         * 输出："leotcede"
         *
         *
         * 提示：
         *
         * 1 <= s.length <= 3 * 105
         * s 由 可打印的 ASCII 字符组成
         */
        return "";
    }

    /**
     * 541、反转字符串 II
     */
    public String reverseStr(String s, int k) {
        /**
         *  给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
         *
         * 如果剩余字符少于 k 个，则将剩余字符全部反转。
         * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
         *
         * 示例 1：
         * 输入：s = "abcdefg", k = 2
         * 输出："bacdfeg"
         * 示例 2：
         * 输入：s = "abcd", k = 2
         * 输出："bacd"
         *
         * 提示：
         * 1 <= s.length <= 104
         * s 仅由小写英文组成
         * 1 <= k <= 104
         */
        return "";
    }

    /**
     * 443、压缩字符串
     */
    public int compress(char[] chars) {
        /**
         *  给你一个字符数组 chars ，请使用下述算法压缩：
         *
         * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
         *
         * 如果这一组长度为 1 ，则将字符追加到 s 中。
         * 否则，需要向 s 追加字符，后跟这一组的长度。
         * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars
         * 数组中会被拆分为多个字符。
         * 请在 修改完输入数组后 ，返回该数组的新长度。
         * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
         *
         * 示例 1：
         * 输入：chars = ["a","a","b","b","c","c","c"]
         * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
         * 解释：
         * "aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
         * 示例 2：
         *
         * 输入：chars = ["a"]
         * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
         * 解释：
         * 没有任何字符串被替代。
         * 示例 3：
         *
         * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
         * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
         * 解释：
         * 由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
         * 注意每个数字在数组中都有它自己的位置。
         *
         * 提示：
         * 1 <= chars.length <= 2000
         * chars[i] 可以是小写英文字母、大写英文字母、数字或符号
         */
        return 0;
    }

    /**
     * 789、逃脱阻碍者
     */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        /**
         *  你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，你的目的地是 target = [xtarget, ytarget] 。地图上有一些阻碍者，
         * 以数组 ghosts 给出，第 i 个阻碍者从 ghosts[i] = [xi, yi] 出发。所有输入均为 整数坐标 。
         * 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到距离原位置 1 个单位 的新位置。当然，也可以选择 不动 。
         * 所有动作 同时 发生。
         * 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置
         * （包括目的地）都不算是逃脱成功。
         * 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
         *
         * 示例 1：
         * 输入：ghosts = [[1,0],[0,3]], target = [0,1]
         * 输出：true
         * 解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。
         * 示例 2：
         * 输入：ghosts = [[1,0]], target = [2,0]
         * 输出：false
         * 解释：你需要走到位于 (2, 0) 的目的地，但是在 (1, 0) 的阻碍者位于你和目的地之间。
         * 示例 3：
         * 输入：ghosts = [[2,0]], target = [1,0]
         * 输出：false
         * 解释：阻碍者可以和你同时达到目的地。
         * 示例 4：
         * 输入：ghosts = [[5,0],[-10,-2],[0,-5],[-2,-2],[-7,1]], target = [7,7]
         * 输出：false
         * 示例 5：
         * 输入：ghosts = [[-1,0],[0,1],[-1,0],[0,1],[-1,0]], target = [0,0]
         * 输出：true
         *
         * 提示：
         * 1 <= ghosts.length <= 100
         * ghosts[i].length == 2
         * -104 <= xi, yi <= 104
         * 同一位置可能有 多个阻碍者 。
         * target.length == 2
         * -104 <= xtarget, ytarget <= 104
         */
        return false;
    }

    /**
     * 1646、获取生成数组中的最大值
     */
    public int getMaximumGenerated(int n) {
        /**
         *  给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
         * nums[0] = 0
         * nums[1] = 1
         * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
         * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
         * 返回生成数组 nums 中的 最大 值。
         * 示例 1：
         *
         * 输入：n = 7
         * 输出：3
         * 解释：根据规则：
         *   nums[0] = 0
         *   nums[1] = 1
         *   nums[(1 * 2) = 2] = nums[1] = 1
         *   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
         *   nums[(2 * 2) = 4] = nums[2] = 1
         *   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
         *   nums[(3 * 2) = 6] = nums[3] = 2
         *   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
         * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
         * 示例 2：
         *
         * 输入：n = 2
         * 输出：1
         * 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
         * 示例 3：
         *
         * 输入：n = 3
         * 输出：2
         * 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
         * 提示：
         * 0 <= n <= 100
         */
        return 0;
    }

    /**
     * 787、K 站中转内最便宜的航班
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /**
         *  有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，
         * 以价格 pricei 抵达 toi。
         * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜
         * ，并返回该价格。 如果不存在这样的路线，则输出 -1。
         *
         * 示例 1：
         * 输入:
         * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
         * src = 0, dst = 2, k = 1
         * 输出: 200
         * 解释:
         * 城市航班图如下
         *
         * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
         * 示例 2：
         *
         * 输入:
         * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
         * src = 0, dst = 2, k = 0
         * 输出: 500
         * 解释:
         * 城市航班图如下
         * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
         *
         * 提示：
         * 1 <= n <= 100
         * 0 <= flights.length <= (n * (n - 1) / 2)
         * flights[i].length == 3
         * 0 <= fromi, toi < n
         * fromi != toi
         * 1 <= pricei <= 104
         * 航班没有重复，且不存在自环
         * 0 <= src, dst, k < n
         * src != dst
         */
        return 0;
    }

    /**
     * 797、所有可能的路径
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /**
         *  给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
         * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
         * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
         * 示例 1：
         *
         * 输入：graph = [[1,2],[3],[3],[]]
         * 输出：[[0,1,3],[0,2,3]]
         * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
         * 示例 2：
         *
         * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
         * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
         * 示例 3：
         *
         * 输入：graph = [[1],[]]
         * 输出：[[0,1]]
         * 示例 4：
         *
         * 输入：graph = [[1,2,3],[2],[3],[]]
         * 输出：[[0,1,2,3],[0,2,3],[0,3]]
         * 示例 5：
         *
         * 输入：graph = [[1,3],[2],[3],[]]
         * 输出：[[0,1,2,3],[0,3]]
         *
         * 提示：
         * n == graph.length
         * 2 <= n <= 15
         * 0 <= graph[i][j] < n
         * graph[i][j] != i（即，不存在自环）
         * graph[i] 中的所有元素 互不相同
         * 保证输入为 有向无环图（DAG）
         */
        return null;
    }

    /**
     * 881、救生艇
     */
    public int numRescueBoats(int[] people, int limit) {
        /**
         *  第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
         * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
         * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
         *
         * 示例 1：
         *
         * 输入：people = [1,2], limit = 3
         * 输出：1
         * 解释：1 艘船载 (1, 2)
         * 示例 2：
         *
         * 输入：people = [3,2,2,1], limit = 3
         * 输出：3
         * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
         * 示例 3：
         *
         * 输入：people = [3,5,3,4], limit = 5
         * 输出：4
         * 解释：4 艘船分别载 (3), (3), (4), (5)
         * 提示：
         *
         * 1 <= people.length <= 50000
         * 1 <= people[i] <= limit <= 30000
         */
        return 0;
    }

    /**
     * 295、数据流的中位数
     */
    public void testMedianFinder() {

        /**
         * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
         * 例如，
         * [2,3,4] 的中位数是 3
         * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
         *
         * 设计一个支持以下两种操作的数据结构：
         * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
         * double findMedian() - 返回目前所有元素的中位数。
         *
         * 示例：
         * addNum(1)
         * addNum(2)
         * findMedian() -> 1.5
         * addNum(3)
         * findMedian() -> 2
         */

    }

    /**
     * 1480、一维数组的动态和
     */
    public int[] runningSum(int[] nums) {
        /**
         *  给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
         * 请返回 nums 的动态和。
         * 示例 1：
         * 输入：nums = [1,2,3,4]
         * 输出：[1,3,6,10]
         * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
         * 示例 2：
         *
         * 输入：nums = [1,1,1,1,1]
         * 输出：[1,2,3,4,5]
         * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
         * 示例 3：
         *
         * 输入：nums = [3,1,2,10,1]
         * 输出：[3,4,6,16,17]
         *
         * 提示：
         * 1 <= nums.length <= 1000
         * -10^6 <= nums[i] <= 10^6
         */

        /**
         * 思路：
         * 简单题，暴力就完事了。其实就是前缀和
         */

        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    /**
     * 1588、所有奇数长度子数组的和
     */
    public int sumOddLengthSubarrays(int[] arr) {
        /**
         * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
         * 子数组 定义为原数组中的一个连续子序列。
         * 请你返回 arr 中 所有奇数长度子数组的和 。
         *
         * 示例 1：
         * 输入：arr = [1,4,2,5,3]
         * 输出：58
         * 解释：所有奇数长度子数组和它们的和为：
         * [1] = 1
         * [4] = 4
         * [2] = 2
         * [5] = 5
         * [3] = 3
         * [1,4,2] = 7
         * [4,2,5] = 11
         * [2,5,3] = 10
         * [1,4,2,5,3] = 15
         * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
         * 示例 2：
         *
         * 输入：arr = [1,2]
         * 输出：3
         * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
         * 示例 3：
         *
         * 输入：arr = [10,11,12]
         * 输出：66
         *
         * 提示：
         * 1 <= arr.length <= 100
         * 1 <= arr[i] <= 1000
         */

        /**
         * 思路：递归+滑动窗口
         * 根据题目，长度为奇数的连续数组的和。可以每次生成一个奇数，以这个数作为长度递归找到数组，然后将他们相加即可。
         */

        int sum = 0;
        for (int i = 1; i <= arr.length; i += 2) {
            sum += dfs1588(0, i, arr);
        }
        return sum;
    }

    private int dfs1588(int index, int length, int[] arr) {
        if (index + length > arr.length) return 0;
        int sum = 0;
        for (int i = index; i < (index + length); i++) {
            sum += arr[i];
        }
        return sum + dfs1588(index + 1, length, arr);
    }

    /**
     * 528、按权重随机选择
     */
    public void pickIndex() {
        WeightSolution ws = new WeightSolution(new int[]{1, 3});
        ws.pickIndex();
    }

    /**
     * 1109、航班预订统计
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        /**
         *  这里有 n 个航班，它们分别从 1 到 n 进行编号。
         * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti
         * （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
         * 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
         *
         * 示例 1：
         * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
         * 输出：[10,55,45,25,25]
         * 解释：
         * 航班编号        1   2   3   4   5
         * 预订记录 1 ：   10  10
         * 预订记录 2 ：       20  20
         * 预订记录 3 ：       25  25  25  25
         * 总座位数：      10  55  45  25  25
         * 因此，answer = [10,55,45,25,25]
         * 示例 2：
         *
         * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
         * 输出：[10,25]
         * 解释：
         * 航班编号        1   2
         * 预订记录 1 ：   10  10
         * 预订记录 2 ：       15
         * 总座位数：      10  25
         * 因此，answer = [10,25]
         *
         * 提示：
         * 1 <= n <= 2 * 104
         * 1 <= bookings.length <= 2 * 104
         * bookings[i].length == 3
         * 1 <= firsti <= lasti <= n
         * 1 <= seatsi <= 104
         */

        /**
         * 思路：模拟，根据意思依次相加即可得出正确答案。但是时间复杂度高，并且设置为中等题，不太应该
         * 使用常规办法。实际考点是差分+前缀和。
         */
        // 常规方法
        int[] result = new int[n];
//        for (int i = 0; i < bookings.length; i++) {
//            for (int j = bookings[i][0]; j <= bookings[i][1]; j++) {
//                result[j - 1] = result[j - 1] + bookings[i][2];
//            }
//        }

        /**
         * 差分思想：将对区间内修改转变成对区间头尾2个位置修改。
         */
        //1、计算差分数组
        for (int i = 0; i < bookings.length; i++) {
            result[bookings[i][0] - 1] += bookings[i][2]; //一加
            if (bookings[i][1] < n)//防止数组越界
                result[bookings[i][1]] -= bookings[i][2]; //一减
        }
        //2、前缀和
        for (int i = 1; i < result.length; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }

    /**
     * 165、比较版本号
     */
    public static int compareVersion(String version1, String version2) {
        /**
         *  给你两个版本号 version1 和 version2 ，请你比较它们。
         * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。
         * 修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
         * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001
         * 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1
         * 的修订号分别为 0 和 1 ，0 < 1 。
         * 返回规则如下：
         * 如果 version1 > version2 返回 1，
         * 如果 version1 < version2 返回 -1，
         * 除此之外返回 0。
         *
         * 示例 1：
         * 输入：version1 = "1.01", version2 = "1.001"
         * 输出：0
         * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
         *
         * 示例 2：
         * 输入：version1 = "1.0", version2 = "1.0.0"
         * 输出：0
         * 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
         *
         * 示例 3：
         * 输入：version1 = "0.1", version2 = "1.1"
         * 输出：-1
         * 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
         *
         * 示例 4：
         * 输入：version1 = "1.0.1", version2 = "1"
         * 输出：1
         *
         * 示例 5：
         * 输入：version1 = "7.5.2.4", version2 = "7.5.3"
         * 输出：-1
         *
         * 提示：
         * 1 <= version1.length, version2.length <= 500
         * version1 和 version2 仅包含数字和 '.'
         * version1 和 version2 都是 有效版本号
         * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
         */

        /**
         * 思路
         * 版本号仅包含数字和.。只需要判断.前后每个数字谁大谁小即可。但是存在前导0的情况。比如"1.01"、 "1.001"，其中01与001就是前导0。
         * 这里是没有作用的，即：01==001。所以可以优先处理版本号的前导0情况，剩下都是有效数字，直接比较大小即可。
         * 还有一种更加简便的方法，通过split版本号的.，再将每一位转成int比较。这样可以无视前导0。
         */

        String[] split1 = version1.split("\\."); // 不加转移符号，split的数组是空的
        String[] split2 = version2.split("\\.");

        int length = split1.length > split2.length ? split1.length : split2.length;

        int l1;
        int l2;
        for (int i = 0; i < length; i++) {
            l1 = 0;
            l2 = 0;
            if (i < split1.length) l1 = Integer.parseInt(split1[i]);
            if (i < split2.length) l2 = Integer.parseInt(split2[i]);

            if (l1 > l2) return 1;
            if (l1 < l2) return -1;
        }

        return 0;
    }

    /**
     * 剑指 Offer 22、链表中倒数第k个节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        /**
         * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
         * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
         *
         * 示例：
         * 给定一个链表: 1->2->3->4->5, 和 k = 2.
         * 返回链表 4->5.
         */

        /**
         * 思路：快慢指针，让快指针先走k步，到最后快指针到达终点时，慢指针指向倒数第K个节点
         */

        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (k == 0)
                head = head.next;
            else
                k--;
        }
        return head;
    }

    /**
     * 面试题 17.14、最小K个数
     */
    public int[] smallestK(int[] arr, int k) {
        /**
         * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
         *
         * 示例：
         * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
         * 输出： [1,2,3,4]
         * 提示：
         * 0 <= len(arr) <= 100000
         * 0 <= k <= min(100000, len(arr))
         */

        /**
         * 此题没有难度。
         */

        Arrays.sort(arr);
        int[] result = new int[k];

        // 复制数组。效率高一点点
        System.arraycopy(arr, 0, result, 0, k);

//        for (int i = 0; i < k; i++) {
//            result[i] = arr[i];
//        }
        return result;
    }

    /**
     * 470、用 Rand7() 实现 Rand10()
     */
    public int rand10() {

        /**
         * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
         * 不要使用系统的 Math.random() 方法。
         *
         * 示例 1:
         * 输入: 1
         * 输出: [7]
         *
         * 示例 2:
         * 输入: 2
         * 输出: [8,4]
         *
         * 示例 3:
         * 输入: 3
         * 输出: [8,1,10]
         *
         * 提示:
         * rand7 已定义。
         * 传入参数: n 表示 rand10 的调用次数。
         * 进阶:
         * rand7()调用次数的 期望值 是多少 ?
         * 你能否尽量少调用 rand7() ?
         */
//        while (true) {
//            int ans = (rand7() - 1) * 7 + (rand7() - 1); // 进制转换
//            if (1 <= ans && ans <= 10) return ans;
//        }
        return 0;
    }

    /**
     * 502、IPO
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        /**
         * 假设 力扣（LeetCode）即将开始IPO 。为了以更高的价格将股票卖给风险投资公司，力扣希望在IPO 之前开展一些项目以增加其资本。由于资源有限，它只能在
         * IPO 之前完成最多 k个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
         * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
         * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
         * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
         * 答案保证在 32 位有符号整数范围内。
         *
         * 示例 1：
         * 输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
         * 输出：4
         * 解释：
         * 由于你的初始资本为 0，你仅可以从 0 号项目开始。
         * 在完成后，你将获得 1 的利润，你的总资本将变为 1。
         * 此时你可以选择开始 1 号或 2 号项目。
         * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
         * 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
         *
         * 示例 2：
         * 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
         * 输出：6
         *
         * 提示：
         * 1 <= k <= 105
         * 0 <= w <= 109
         * n == profits.length
         * n == capital.length
         * 1 <= n <= 105
         * 0 <= profits[i] <= 104
         * 0 <= capital[i] <= 109
         */

        /**
         * 思路：贪心+排序(优先队列)
         * 根据题意可以知道就是在k次内取到profits内最大的元素即可。除此在外还有其他考虑条件：资本与capital数组的关系。
         * 因为profits 与capital 存在对应关系，既不能只排序profits，更不能去排序profits数组(充当条件而已)。那么可以
         * 构建一个二维数组int[capital][profits]。针对二维数组排序，然后再每次取profits中最大的元素(排序)即可。
         */

        //构建二维数组、排序
        int[][] arr = new int[capital.length][2];
        for (int i = 0; i < capital.length; i++) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        //排序
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int index = 0;
        //使用优先队列来取最大值
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1); //默认是小根堆，要变成大根堆。

        for (int i = 0; i < k; i++) {
            // 将可以选择的项目添加到堆中。
            while (index < capital.length && arr[index][0] <= w) { //因为对arr排序过，所以每次只会选择一部分符合的capital。知道所有被访问过
                queue.add(arr[index][1]);
                index++;
            }

            if (!queue.isEmpty()) {
                w += queue.poll();
            } else break;
        }
        return w;
    }

    /**
     * 68、文本左右对齐
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        /**
         * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
         * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
         * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
         * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
         *
         * 说明:
         * 单词是指由非空格字符组成的字符序列。
         * 每个单词的长度大于 0，小于等于 maxWidth。
         * 输入单词数组 words 至少包含一个单词。
         * 示例:
         *
         * 输入:
         * words = ["This", "is", "an", "example", "of", "text", "justification."]
         * maxWidth = 16
         * 输出:
         * [
         *    "This    is    an",
         *    "example  of text",
         *    "justification.  "
         * ]
         *
         * 示例 2:
         * 输入:
         * words = ["What","must","be","acknowledgment","shall","be"]
         * maxWidth = 16
         * 输出:
         * [
         *   "What   must   be",
         *   "acknowledgment  ",
         *   "shall be        "
         * ]
         * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
         *      因为最后一行应为左对齐，而不是左右两端对齐。
         *      第二行同样为左对齐，这是因为这行只包含一个单词。
         *
         * 示例 3:
         * 输入:
         * words = ["Science","is","what","we","understand","well","enough","to","explain",
         *          "to","a","computer.","Art","is","everything","else","we","do"]
         * maxWidth = 20
         * 输出:
         * [
         *   "Science  is  what we",
         *   "understand      well",
         *   "enough to explain to",
         *   "a  computer.  Art is",
         *   "everything  else  we",
         *   "do                  "
         * ]
         */

        /**
         * 思路：
         * 1、确定每一行的单词数量以及空格数量，保证 length[words] + length[空格] =  maxWidth
         * 2、确定空格摆放位置
         */

        List<String> lines = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; ) {
            lines.clear();
            // 确定单词数量
            int width = 0;
            while (i < words.length && width + words[i].length() <= maxWidth) {
                lines.add(words[i]);
                width += words[i].length();
                width++; //作为拼接下一个单词添加的空格。
                i++;
            }

            if (i == words.length || lines.size() == 1) {
                //最后一行,或者刚刚好一个单词接一个空格这样格式满足一行，或者只有一个单词，直接拼接，不用计算其他空格
                StringBuilder sb = new StringBuilder(lines.get(0));
                for (int j = 1; j < lines.size(); j++) {
                    sb.append(" ").append(lines.get(j));
                }
                //计算补充的空格
                while (maxWidth > sb.length()) {
                    sb.append(" ");
                }
                result.add(sb.toString());
                continue;
            }

            // 计算剩下空格。
            int all = maxWidth - width; //总共剩下的空格
            int point = lines.size() - 1; //要插入空格的位置
            int num = all / point; //每个位置能插入空格的数量。

            String space = "";
            for (int j = 0; j < num; j++) space += " ";

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < lines.size(); j++) {
                sb.append(lines.get(j));
                if (j == lines.size() - 1) break;

                sb.append(" ").append(space);
                // 判断是否要多加空格，因为num的值不一定是整除。
                if (all - (j + 1) * num > (point - j - 1) * num) {
                    sb.append(" ");
                }
            }
            result.add(sb.toString());
        }

        // 上面题目存在空格不一样的问题，没有排查，思路差不多，看这个答案：
//        class Solution {
//            public List<String> fullJustify(String[] words, int maxWidth) {
//                List<String> ans = new ArrayList<>();
//                int n = words.length;
//                List<String> list = new ArrayList<>();
//                for (int i = 0; i < n; ) {
//                    // list 装载当前行的所有 word
//                    list.clear();
//                    list.add(words[i]);
//                    int cur = words[i++].length();
//                    while (i < n && cur + 1 + words[i].length() <= maxWidth) {
//                        cur += 1 + words[i].length();
//                        list.add(words[i++]);
//                    }
//
//                    // 当前行为最后一行，特殊处理为左对齐
//                    if (i == n) {
//                        StringBuilder sb = new StringBuilder(list.get(0));
//                        for (int k = 1; k < list.size(); k++) {
//                            sb.append(" ").append(list.get(k));
//                        }
//                        while (sb.length() < maxWidth) sb.append(" ");
//                        ans.add(sb.toString());
//                        break;
//                    }
//
//                    // 如果当前行只有一个 word，特殊处理为左对齐
//                    int cnt = list.size();
//                    if (cnt == 1) {
//                        String str = list.get(0);
//                        while (str.length() != maxWidth) str += " ";
//                        ans.add(str);
//                        continue;
//                    }
//
//                    /**
//                     * 其余为一般情况
//                     * wordWidth : 当前行单词总长度;
//                     * spaceWidth : 当前行空格总长度;
//                     * spaceItem : 往下取整后的单位空格长度
//                     */
//                    int wordWidth = cur - (cnt - 1);
//                    int spaceWidth = maxWidth - wordWidth;
//                    int spaceItemWidth = spaceWidth / (cnt - 1);
//                    String spaceItem = "";
//                    for (int k = 0; k < spaceItemWidth; k++) spaceItem += " ";
//                    StringBuilder sb = new StringBuilder();
//                    for (int k = 0, sum = 0; k < cnt; k++) {
//                        String item = list.get(k);
//                        sb.append(item);
//                        if (k == cnt - 1) break;
//                        sb.append(spaceItem);
//                        sum += spaceItemWidth;
//                        // 剩余的间隙数量（可填入空格的次数）
//                        int remain = cnt - k - 1 - 1;
//                        // 剩余间隙数量 * 最小单位空格长度 + 当前空格长度 < 单词总长度，则在当前间隙多补充一个空格
//                        if (remain * spaceItemWidth + sum < spaceWidth) {
//                            sb.append(" ");
//                            sum++;
//                        }
//                    }
//                    ans.add(sb.toString());
//                }
//                return ans;
//            }
//        }

        return result;
    }

    /**
     * 1894、找到需要补充粉笔的学生编号
     */
    public static int chalkReplacer(int[] chalk, int k) {
        /**
         * 一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到
         * 编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
         * 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗
         * chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
         * 请你返回需要 补充 粉笔的学生 编号 。
         *
         * 示例 1：
         * 输入：chalk = [5,1,5], k = 22
         * 输出：0
         * 解释：学生消耗粉笔情况如下：
         * - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
         * - 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
         * - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
         * - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
         * - 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
         * - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
         * 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
         *
         * 示例 2：
         * 输入：chalk = [3,4,1,2], k = 25
         * 输出：1
         * 解释：学生消耗粉笔情况如下：
         * - 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
         * - 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
         * - 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
         * - 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
         * - 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
         * - 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
         * - 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
         * - 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
         * - 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。 23
         * 编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
         *
         * 提示：
         * chalk.length == n
         * 1 <= n <= 105
         * 1 <= chalk[i] <= 105
         * 1 <= k <= 109
         */

        /**
         * 思路：
         * 1、模拟: 直接循环累加，知道累加值大于k。(效率略低)
         * 2、求出一轮所需要的数量，对总数取模。用模值在走一遍chalk即可。(效率极高)
         */

        int index = 0;
        int sum = chalk[0];
        while (true) {
            if (sum > k) break;
            index++;
            if (index == chalk.length) index = 0;
            sum += chalk[index];
        }

        // 取模方法
        long all = 0; // 防止数超过int范围
        for (int i : chalk) {
            all += i;
        }
        k = (int) (k % all);
        for (int i = 0; i < chalk.length; i++) {
            if (chalk[i] > k) {
                return i;
            }
            k -= chalk[i];
        }
        return index;
    }

    /**
     * 447、回旋镖的数量
     */
    public int numberOfBoomerangs(int[][] points) {
        /**
         * 给定平面上 n对互不相同的点points ，其中points[i] = [xi, yi] 。回旋镖是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和
         * i 和 k 之间的距离相等（需要考虑元组的顺序）。
         * 返回平面上所有回旋镖的数量。
         *
         * 示例 1：
         * 输入：points = [[0,0],[1,0],[2,0]]
         * 输出：2
         * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
         *
         * 示例 2：
         * 输入：points = [[1,1],[2,2],[3,3]]
         * 输出：2
         *
         * 示例 3：
         * 输入：points = [[1,1]]
         * 输出：0
         *
         * 提示：
         * n == points.length
         * 1 <= n <= 500
         * points[i].length == 2
         * -104 <= xi, yi <= 104
         * 所有点都 互不相同
         */

        /**
         * 思路: 求出距离相等的3个点的所有组合方法的数量。借鉴数据中排列组合公式。先计算出每2个点之间的距离，使用一个map保存，然后再
         * 在距离相同的数组套用公式求出不同的组合数量，最后相加得到最终结果。
         */

        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                //计算2点间的距离
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                int d = x * x + y * y; // 这里d是x到y的距离的平方，但其实开不开方不会影响的后续的组合，所以可以不开方。
                map.put(d, map.getOrDefault(d, 0) + 1);
            }
            // 求出以当前点i为中间点，找出距离相同的2个点。
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                int v = e.getValue();
                sum += v * (v - 1);
            }
        }
        return sum;
    }

    /**
     * 162、寻找峰值
     */
    public int findPeakElement(int[] nums) {
        /**
         * 峰值元素是指其值严格大于左右相邻值的元素。
         * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
         * 你可以假设 nums[-1] = nums[n] = -∞ 。
         * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
         *
         * 示例 1：
         * 输入：nums = [1,2,3,1]
         * 输出：2
         * 解释：3 是峰值元素，你的函数应该返回其索引 2。
         *
         * 示例 2：
         * 输入：nums = [1,2,1,3,5,6,4]
         * 输出：1 或 5
         * 解释：你的函数可以返回索引 1，其峰值元素为 2；
         * 或者返回索引 5， 其峰值元素为 6。
         *
         * 提示：
         * 1 <= nums.length <= 1000
         * -231 <= nums[i] <= 231 - 1
         * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
         */

        /**
         * 思路：题目要求时间复杂度为O(log n)。很明确意思是二分了。
         * 峰值是中间的数比左右两边的数都要大。所以可以根据这个条件作为二分的依据。
         * 如果mid>right，说明右半区是下坡，峰值点要么在左半区内，要么就是当前的mid点(可取)。要更新右边界为当前mid。如果mid<right，说明右半区是上坡，
         * 因为题目说“nums[-1] = nums[n] = -∞ ”得出左右两边端点处必定是一边上坡一边下坡。所以当mid<right时，必有峰值在右边，更新左边界为当前mid+1处。
         * 注意，这里取mid+1。因为当mid<right,mid肯定不是峰值，所以左边界从mid+1开始。
         */

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 212、单词搜索 II
     */
    public List<String> findWords(char[][] board, String[] words) {

        /**
         * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
         * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允
         * 许被重复使用。
         *
         * 示例 1：
         * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
         * words = ["oath","pea","eat","rain"]
         * 输出：["eat","oath"]
         *
         * 示例 2：
         * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
         * 输出：[]
         *
         * 提示：
         * m == board.length
         * n == board[i].length
         * 1 <= m, n <= 12
         * board[i][j] 是一个小写英文字母
         * 1 <= words.length <= 3 * 104
         * 1 <= words[i].length <= 10
         * words[i] 由小写英文字母组成
         * words 中的所有字符串互不相同
         */

        /**
         * 思路： 回溯+递归，前面有个类似题目{@link com.example.testlink.calculate.sword_for_offer.T63}，同样的原理
         */

        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (find212(board, words[i])) {
                list.add(words[i]);
            }
        }
        return list;
    }

    private boolean find212(char[][] board, String word) {
        boolean[][] visit = new boolean[board.length][board[0].length];
        int length = 0;

        for (int j = 0; j < board.length; j++) {
            for (int k = 0; k < board[0].length; k++) {
                if (dfs212(board, word, j, k, visit, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs212(char[][] board, String target, int x, int y, boolean[][] visit, int length) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visit[x][y])
            return false;
        if (target.charAt(length) != board[x][y]) return false;

        if (length == target.length() - 1) return true;

        length++;
        visit[x][y] = true;

        //上下左右四个方向各自找
        boolean b = dfs212(board, target, x - 1, y, visit, length) ||
                dfs212(board, target, x + 1, y, visit, length) ||
                dfs212(board, target, x, y + 1, visit, length) ||
                dfs212(board, target, x, y - 1, visit, length);

        // 回溯的奥妙，将状态回归。
        length--;
        visit[x][y] = false;

        return b;
    }

    /**
     * 650、只有两个键的键盘
     */
    public int minSteps(int n) {
        /**
         * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
         * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
         * Paste（粘贴）：粘贴 上一次 复制的字符。
         * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
         *
         * 示例 1：
         * 输入：3
         * 输出：3
         * 解释：
         * 最初, 只有一个字符 'A'。
         * 第 1 步, 使用 Copy All 操作。
         * 第 2 步, 使用 Paste 操作来获得 'AA'。
         * 第 3 步, 使用 Paste 操作来获得 'AAA'。
         *
         *  示例 2：
         * 输入：n = 1
         * 输出：0
         *
         * 提示：
         * 1 <= n <= 1000
         */
        /**
         * 思路：分解质因数公式，记住理解记住即可
         */

        int ans = 0;
        for (int i = 2; i * i <= n; ++i) {
            while (n % i == 0) {
                n /= i;
                ans += i;
            }
        }
        if (n > 1) {
            ans += n;
        }
        return ans;
    }

    /**
     * 300、最长递增子序列
     */
    public int lengthOfLIS(int[] nums) {
        /**
         * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
         * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
         *
         * 示例 1：
         * 输入：nums = [10,9,2,5,3,7,101,18]
         * 输出：4
         * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
         *
         * 示例 2：
         * 输入：nums = [0,1,0,3,2,3]
         * 输出：4
         *
         * 示例 3：
         * 输入：nums = [7,7,7,7,7,7,7]
         * 输出：1
         *
         * 提示：
         * 1 <= nums.length <= 2500
         * -104 <= nums[i] <= 104
         *
         * 进阶：
         * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
         * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
         */

        /**
         * 思路：动态规划。
         * 步骤：
         * 1、确定dp数组
         * 2、确定base case
         * 3、转移方程
         * 4、边界情况
         * 5、状态压缩
         *
         * 首先，从原则上看，只要数组的后一个数比前面一个数大的话，就能组成一个更加长的序列。这就是转移方程一个切入点。设dp数组，
         * dp[i]表示当前i个数内最长的递增子序列长度。转移方程无非是dp[i] = dp[i-1]或者 dp[i-1]+1。取决于当前数是否大于前面一个数。
         * 为了能将前i个数的所有情况罗列，需要使用到双层循环。外层为i，内层为0到i的所有可能性。对外层的数来说，只要内层的数比它小，那么
         * 在内层dp[i]就等于它原生，或者是dp[j]的基础上+1。最后保存一个最大值作为最终结果。它的核心思想是通过选定一个数为范围边界i，每次
         * 循环从0到i范围的数j，与i比较，只要i比j大，那么对于j的递增序列就能+1，产生一个新的序列长度，再与i的原来长度比较得出一个结果替代
         * 为dp[i]。
         */

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // 以每个元素开头的序列，初始最长序列长度都是1。
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) //找到一个数比当前这个数大，那么就可以形成一个更长的子序列，对于j就是dp[j]+1。最后还要比较取最长的。
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            // 不能保证后面的子序列一定比前面某部分的最长长度长，所以最终结果使用一个变量保存最长的
            max = Math.max(max, dp[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 673、最长递增子序列的个数
     */
    public static int findNumberOfLIS(int[] nums) {

        /**
         * 给定一个未排序的整数数组，找到最长递增子序列的个数。
         * 示例 1:
         * 输入: [1,3,5,4,7]
         * 输出: 2
         * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
         *
         * 示例 2:
         * 输入: [2,2,2,2,2]
         * 输出: 5
         * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
         * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
         */

        /**
         * 思路：参考题目:300。dp[i]表示当前i个数中最大递增子序列长度，使用一个新的dp数组cu表示当前i个数中最大
         * 递增子序列数量个数。统计数量时注意分清楚情况，具体看注释
         *
         */
        int[] dp = new int[nums.length];
        int[] cu = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(cu, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] == dp[j] + 1) { //原来的i个数量就有这么多个最长序列数量dp[i]，现在又来了一种dp[j]+1，
                        // 所以总的cn[i]应该是这两种情况之和。对于dp[j]+1下的序列数量其实就是cu[j]。
                        cu[i] += cu[j];
                    } else if (dp[i] < dp[j] + 1) { //说明对于dp数组来说，dp[i]的值发生了修改，产生了一个新的序列长
                        // 度，新的序列长度自然要使用新的cu数据。因为增加的这个长度是固定的，cu[i] = cu[j]。后面修改dp[i]
                        // 的值也是属于这个范畴的。(注意在判断之前不能先修改dp[i]的值)。
                        cu[i] = cu[j];

                    } else { //不用更新cu[i]数量,因为dp[i]也不用更新。这里只是列举出来方便理解

                    }
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        int count = 0;
        for (int i = 0; i < cu.length; i++) {
            if (dp[i] == max) count += cu[i];
        }
        return count;
    }

    /**
     * 326、3的幂
     */
    public boolean isPowerOfThree(int n) {
        /**
         * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
         * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
         * <p>
         * 示例 1：
         * 输入：n = 27
         * 输出：true
         * <p>
         * 示例 2：
         * 输入：n = 0
         * 输出：false
         * <p>
         * 示例 3：
         * 输入：n = 9
         * 输出：true
         * <p>
         * 示例 4：
         * 输入：n = 45
         * 输出：false
         * <p>
         * 提示：
         * -231 <= n <= 231 - 1
         * 进阶：
         * 你能不使用循环或者递归来完成本题吗？
         */
        if (n <= 2) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;

        // 不用循环的办法 (原理：一个数的低次幂一定可以被它的高次幂整除。比如：2^1 = 2;2^3=8;)
        // 32位以内大的3的幂为 3^19 = 1162261467
//        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 639、解码方法 II ?
     */
    public int numDecodings2(String s) {
        /**
         * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
         *
         * 'A' -> 1
         * 'B' -> 2
         * ...
         * 'Z' -> 26
         * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
         * "AAJF" 对应分组 (1 1 10 6)
         * "KJF" 对应分组 (11 10 6)
         * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
         *
         * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*"
         * 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
         * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
         * 由于答案数目可能非常大，返回对 109 + 7 取余 的结果。
         *
         * 示例 1：
         * 输入：s = "*"
         * 输出：9
         * 解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
         * 可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
         * 因此，"*" 总共有 9 种解码方法。
         *
         * 示例 2：
         * 输入：s = "1*"
         * 输出：18
         * 解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
         * 每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
         * 因此，"1*" 共有 9 * 2 = 18 种解码方法。
         *
         * 示例 3：
         * 输入：s = "2*"
         * 输出：15
         * 解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
         * "21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
         * 因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
         *
         * 提示：
         * 1 <= s.length <= 105
         * s[i] 是 0 - 9 中的一位数字或字符 '*'
         */
        return 0;
    }

    /**
     * 437、路径总和 III
     */
    public int pathSum(TreeNode root, int targetSum) {
        /**
         * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
         * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
         *
         * 示例 1：
         * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
         * 输出：3
         * 解释：和等于 8 的路径有 3 条，如图所示。
         *
         * 示例 2：
         * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
         * 输出：3
         *
         * 提示:
         * 二叉树的节点个数的范围是 [0,1000]
         * -109 <= Node.val <= 109
         * -1000 <= targetSum <= 1000
         */
        /**
         * 思路：双层递归处理，要完全理解题目意思。
         */
        if (root == null) return 0;
        dfs437(root, 0, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return count437;
    }

    private int count437 = 0;

    private void dfs437(TreeNode root, int num, int target) {
        if (root == null) return;
        if (num + root.val == target) count437++;

        dfs437(root.left, num + root.val, target);
        dfs437(root.right, num + root.val, target);
    }

    /**
     * 187、重复的DNA序列
     */
    public List<String> findRepeatedDnaSequences(String s) {
        /**
         * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA
         * 中的重复序列有时会对研究非常有帮助。
         * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
         * 示例 1：
         * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
         * 输出：["AAAAACCCCC","CCCCCAAAAA"]
         *
         * 示例 2：
         * 输入：s = "AAAAAAAAAAAAA"
         * 输出：["AAAAAAAAAA"]
         * 提示：
         * 0 <= s.length <= 105
         * s[i] 为 'A'、'C'、'G' 或 'T'
         */
        /**
         * 遍历所有可能出现的长度为10的子串，利用hash保存，当数量等于2时添加到结果集合即可。不能大于1，会导致保存多个重复子串。
         */
        List<String> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String str = s.substring(i, 10 + i);
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) {
                list.add(str);
            }
        }
        return list;
    }

    /**
     * 352、将数据流变为多个不相交区间
     */
    public void test352() {
        /**
         *  给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
         * 实现 SummaryRanges 类：
         * SummaryRanges() 使用一个空数据流初始化对象。
         * void addNum(int val) 向数据流中加入整数 val 。
         * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
         * 示例：
         * 输入：
         * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum",
         * "getIntervals", "addNum", "getIntervals"]
         * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
         * 输出：
         * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]],
         * null, [[1, 3], [6, 7]]]
         *
         * 解释：
         * SummaryRanges summaryRanges = new SummaryRanges();
         * summaryRanges.addNum(1);      // arr = [1]
         * summaryRanges.getIntervals(); // 返回 [[1, 1]]
         * summaryRanges.addNum(3);      // arr = [1, 3]
         * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
         * summaryRanges.addNum(7);      // arr = [1, 3, 7]
         * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
         * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
         * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
         * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
         * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
         *
         * 提示：
         * 0 <= val <= 104
         * 最多调用 addNum 和 getIntervals 方法 3 * 104 次
         * 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
         */

        SummaryRanges s = new SummaryRanges();
    }

    /**
     * 441、排列硬币
     */
    public int arrangeCoins(int n) {
        /**
         * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
         * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
         */
        /**
         * 简单题，模拟即可
         */
        int i = 1;
        while (true) {
            n = n - i;
            i++;
            if (n == i) return i;
            if (n < i) return i - 1;
        }
    }

}


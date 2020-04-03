package com.example.testlink.calculate;

import android.util.JsonReader;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxx on 2019/4/27.
 * Use by 数组分类
 */

public class ArraysCalculates {

    private static void showResult(int[][] A) {
        System.out.print("[");
        for (int i = 0; i < A.length; i++) {
            System.out.print("[");
            for (int j = 0; j < A[i].length; j++) {
                if (j == A[i].length - 1) {
                    System.out.print(A[i][j] + "]");
                } else {
                    System.out.print(A[i][j] + ",");
                }
            }
            if (i == A.length - 1) {
                System.out.print("]");
            } else {
                System.out.print(",");
            }
        }
    }

    private static void showResult(int[] A) {
        System.out.print("[");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
            if (i == A.length - 1) {
                System.out.print("]");
            } else {
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
//        int[] ints = new int[]{2, 1, 1, 1, 4, 3, 16, 6, 6, 6};
//        sort3(ints);
//        System.out.print("无重复数组长度是： " + sort);
//        int[] is = new int[]{1, 1, 4, 2, 1, 3};
//        int[] is = new int[]{1, 4, 2, 3};
//        System.out.print(problem561(is));
//        char[][] ins = new char[][]{
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', 'p', '.', '.', '.', '.'},
//                {'.', '.', '.', 'p', '.', '.', '.', '.'},
//                {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', 'B', '.', '.', '.', '.'},
//                {'.', '.', '.', 'p', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'}};

//        int[] ins = new int[]{7, 6, 4, 3, 1};
        int[][] ins = new int[][]{{1, 2}, {3, 4}, {2, 1, 1, 1, 4, 3, 16, 6, 6}};
//        String[] strs = new String[]{/*"bella", "label", "roller"*/"cool", "lock", "cook"};
        int[] arr1 = {0, 1, 2, 4}, arr2 = {7, 4, 19, 6};


        int[] a1 = {1, 0, 1, 0, 3, 12, 0, 0, 0, 0, 0, 1};
//        problem283(a1);
//        int[] a2 = {3, 1, 4, 1, 5};
        int[] a2 = {2, 6, 4, 8, 10, 9, 15};   // 5
        int[] a4 = {6, 2, 4, 8, 10, 15, 9};  // 7
        int[] a3 = {1, 2, 3, 4, 5};     // 0
        int[] a5 = {2, 1};     // 0
        int[] a6 = {1, 3, 2, 2, 2};     // 4
        System.out.println(problem581(a6));

    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1:
     * <p>
     * 给定数组 nums = [1,1,2],
     * <p>
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public static int sort(int[] nums) {
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[p] != nums[i]) {
                p++;
                nums[p] = nums[i];
            }
        }
        return p + 1;

    }

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 示例 1:
     * 给定 nums = [3,2,2,3], val = 3,
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * 注意这五个元素可为任意顺序。
     * 你不需要考虑数组中超出新长度后面的元素
     *
     * @return
     */
    public static int sort2(int[] nums, int value) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != value) {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }

    /**
     * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
     * 你可以返回满足此条件的任何数组作为答案.输入：[3,1,2,4]
     * 输出：[2,4,3,1]
     * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     */
    public static void sort3(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 给定 N，计算 F(N)。
     * <p>
     * <p>
     * <p>
     * <p>
     * 看到这个脑子里第一反应就是递归。当然也是可以实现目的。只是效率非常低。
     *
     * @param n
     */
    public static int problem509(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        //递归法，效率最低，最省代码
//        执行用时 :19 ms, 在所有 Java 提交中击败了 10.92% 的用户
//        内存消耗 :33.5 MB, 在所有 Java 提交中击败了 51.35% 的用户

//        return problem509(n - 1) + problem509(n - 2);

        //备忘录方法
//        HashMap<Integer, Integer> map = new HashMap();
//        map.put(0, 0);
//        map.put(1, 1);

//        执行用时 :1 ms, 在所有 Java 提交中击败了60.45% 的用户
//        内存消耗 :33.9 MB, 在所有 Java 提交中击败了 48.05% 的用户

//        return getValue509(map, n);

        //基于备忘录法，再次改造
//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        map.put(0, 0);
//        map.put(1, 1);
//        for (int i = 2; i <= n; i++) {
//            map.put(i, map.get(n - 1) + map.get(n - 2));
//        }
//        执行用时 :1 ms, 在所有 Java 提交中击败了60.45% 的用户
//        内存消耗 :33.9 MB, 在所有 Java 提交中击败了 48.05% 的用户
//        return map.get(n);

        //再次进化(有点动态规划的味道)
//        int bef = 0;
//        int aft = 1;
//        for (int i = 0; i < n - 1; i++) {
//            int sum = bef + aft;
//            bef = aft;
//            aft = sum;
//        }
//        执行用时 :1 ms, 在所有 Java 提交中击败了60.45% 的用户
//        内存消耗 :33.9 MB, 在所有 Java 提交中击败了 58.06% 的用户
//        return aft;

        //原来基础上再次进化(最终版)
        int bef = 0;
        int aft = 1;
        for (int i = 0; i < n - 1; i++) {
            bef = aft - bef;
            aft = bef + aft;
        }
//        执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
//        内存消耗 :33.2 MB, 在所有 Java 提交中击败了 59.16% 的用户

        return aft;

        /**
         * 提交记录最优解法
         *  // 当前位数的值,下一位的值
         *int cur = 0, next = 1;
         *while (n-- > 0) {
         * // 循环往下走
         * next = next + cur;
         *cur = next - cur;
         *}
         *return cur;
         */
    }

    private static int getValue509(HashMap<Integer, Integer> map, int n) {
        //必要条件，否则会死循环导致栈溢出
        if (n == 0 || n == 1) {
            return n;
        }
        if (null == map.get(n) || map.get(n) == 0) {
            int result = getValue509(map, n - 1) + getValue509(map, n - 2);
            map.put(n, result);
            return result;
        } else {
            return map.get(n);
        }
    }

    /**
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [[1,1,0],[1,0,1],[0,0,0]]
     * 输出: [[1,0,0],[0,1,0],[1,1,1]]
     * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     * 示例 2:
     * <p>
     * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 说明:
     * <p>
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
     *
     * @param A
     * @return
     */
    public static int[][] problem832(int[][] A) {
        for (int a = 0; a < A.length; a++) {
            int[] as = A[a];
            int mid = 0;
            if (as.length % 2 == 0) {
                mid = as.length / 2;
            } else {
                mid = as.length / 2 + 1;
            }
            for (int j = 0; j < mid; j++) {

                as[j] = as[j] == 0 ? 1 : 0;
                if (as.length % 2 == 0 || j != mid - 1) {
                    as[as.length - 1 - j] = as[as.length - 1 - j] == 0 ? 1 : 0;
                }
                if (as[j] != as[as.length - 1 - j]) {
                    as[j] = as[j] ^ as[as.length - 1 - j];
                    as[as.length - 1 - j] = as[j] ^ as[as.length - 1 - j];
                    as[j] = as[j] ^ as[as.length - 1 - j];
                }
                //1,2
                //as[0] = as[0] ^ as[1];// 1^2 = 3
                //3,2
                //as[1] = as[0] ^ as[1]; // 3^2 = 1^2^2 = 1
                //3,1
                //as[0] = as[0] ^ as[1]; // 3^1 = 1^2^1 = 2
                //2,1

            }
        }
//        执行用时 :2 ms, 在所有 Java 提交中击败了 64.49% 的用户
//        内存消耗 :38.9 MB, 在所有 Java 提交中击败了 82.29% 的用户

//        提交记录上最优解法:
//        for (int i = 0; i < A.length; i++) {
//            for (int j = 0, k = A[0].length - 1; j < (A[0].length + 1) / 2; j++, k--) {
//                int temp = A[i][j] ^ 1;
//                A[i][j] = A[i][k] ^ 1;
//                A[i][k] = temp;
//            }
//        }

        //显示输出结果
        showResult(A);

        return A;
    }

    /**
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     * 请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
     * 示例：
     * <p>
     * 输入：[1,1,4,2,1,3]:[1,1,1,2,3,4]
     * 输出：3
     * 解释：
     * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
     * <p>
     * 提示：
     * <p>
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     * <p>
     * 一开始没有申明题意，走入了一个错误的方向，怎么提交都是错误的。跟很多人一样认为“非递减顺序”的话，例子结果
     * 应该是2，只要4,2排列错了嘛,所以才怎么去提交都是错误的。经过评论圈的提醒，才发现:原来它的这个结果是跟顺序
     * 后的结果作对比，得到不同的位置，就是这个题的结果。
     *
     * @param heights
     */
    public static int problem1051(int[] heights) {
        int count = 0;
        if (heights.length < 2) {
            return count;
        }
        int[] newInts = heights.clone();// Arrays.copyOf(heights, heights.length); ----耗时为4ms???
        Arrays.sort(newInts);

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != newInts[i]) {
                count++;
            }
        }

//        执行用时 :3 ms, 在所有 Java 提交中击败了70.03%的用户
//        内存消耗 :35.3 MB, 在所有 Java 提交中击败了100.00%的用户

        /**
         * 双耗击败率100%的解法：
         int[] arr = new int[101];
         for (int height : heights) {
         arr[height]++;
         }
         int count = 0;
         for (int i = 1, j = 0; i < arr.length; i++) {
         while (arr[i]-- > 0) {
         if (heights[j++] != i) count++;
         }
         }
         */

        return count;
    }

    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
     * 使得从1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,4,3,2]
     * <p>
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     * 提示:
     * <p>
     * n 是正整数,范围在 [1, 10000].
     * 数组中的元素范围在 [-10000, 10000].
     * <p>
     * 解题: 长度为2n，两两一对，取两者中的最小值，然后所以最小值取和。要保证这个最小值的和要最大，
     * 那必然是最大的数跟第二大的数组合，依此类推。于是这题就变成了简单的排序题了。
     *
     * @return
     */
    public static int problem561(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i += 2) {
            count += nums[i];
        }
        return count;

//         执行用时 :34 ms, 在所有 Java 提交中击败了70.80%的用户
//         内存消耗 :51.3 MB, 在所有 Java 提交中击败了22.35%的用户

    }

    /**
     * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。
     * 它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     * <p>
     * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、
     * 到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
     * <p>
     * 返回车能够在一次移动中捕获到的卒的数量。
     * <p>
     * 提示：
     * board.length == board[i].length == 8
     * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
     * 只有一个格子上存在 board[i][j] == 'R'
     * <p>
     * <p>
     * 理解了题意就好了，还有要注意那个表示黑卒的是小写的p，不是大写的。这个我没有注意到，琢磨好久了。
     *
     * @param strs
     * @return
     */
    public static int problem999(char[][] strs) {
        int count = 0;
        int k = 0;
        int l = 0;
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length; j++) {
                if (strs[i][j] == ('R')) {
                    System.out.println(i + ' ' + j);
                    k = j;
                    l = i;
                }
            }
        }
        int m = k;
        while (--k >= 0 && strs[l][k] != 'B') {
            if (strs[l][k] == ('p')) {
                count++;
                System.out.println("找到了一个车");
                break;
            }
        }
        while (++k <= 7 && strs[l][k] != ('B')) {
            if (strs[l][k] == ('p')) {
                count++;
                break;
            }
        }
        while (--l >= 0 && strs[l][m] != ('B')) {
            if (strs[l][m] == ('p')) {
                count++;
                break;
            }
        }
        while (++l <= 7 && strs[l][m] != ('B')) {
            if (strs[l][m] == ('p')) {
                count++;
                break;
            }
        }
//        执行用时 :2 ms, 在所有 Java 提交中击败了 55.47% 的用户
//        内存消耗 :35 MB, 在所有 Java 提交中击败了 45.91% 的用户

        return count;
    }

    /**
     * 给定一个矩阵 A， 返回 A 的转置矩阵。
     * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * 示例 2：
     * <p>
     * 输入：[[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     * 提示：
     * 1 <= A.length <= 1000
     * 1 <= A[0].length <= 1000
     * <p>
     * 理解转换前后矩阵变化规律，结合题目“交换矩阵的行索引与列索引”便可得出解法
     *
     * @param A
     * @return
     */
    public int[][] problem867(int[][] A) {
        int[][] newA = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                newA[j][i] = A[i][j];
            }
        }

//        执行用时 :1 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗 :48.8 MB, 在所有 Java 提交中击败了 61.46% 的用户
        return newA;
    }

    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * <p>
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * <p>
     * 你可以返回任何满足上述条件的数组作为答案。
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     * 提示：
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     *
     * @param A
     * @return
     */
    public static int[] problem922(int[] A) {

        int j = A.length - 1;
        int i = 0;
        while (i < A.length && j > i) {
            if ((i % 2 == 0 && A[i] % 2 == 0) || (i % 2 != 0 && A[i] % 2 != 0)) {
                i++;
                j = A.length - 1;
            } else {
                if (A[i] % 2 == 0 && A[j] % 2 != 0 || A[i] % 2 != 0 && A[j] % 2 == 0) {
                    A[i] = A[i] ^ A[j];
                    A[j] = A[i] ^ A[j];
                    A[i] = A[i] ^ A[j];
                    i++;
                    j = A.length - 1;
                } else {
                    j--;
                }
            }
        }

//        执行用时 :9 ms, 在所有 Java 提交中击败了 18.24% 的用户
//        内存消耗 :40.6 MB, 在所有 Java 提交中击败了 94.83% 的用户

        //提交记录最优解:
        /*
        * int i=0;
        int temp=0;
        int j=1;
        for(i=0;i<A.length-1;i+=2){
            if((A[i]&1)!=0){
                while((A[j]&1)!=0){
                    j+=2;
                }
                temp=A[i];
                A[i]=A[j];
                A[j]=temp;
            }
        } */


        for (int k = 0; k < A.length; k++) {
            System.out.print(A[k] + " , ");
        }

        return A;
    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     * <p>
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     *
     * @param A
     * @return
     */
    public static List<String> problem1002(String[] A) {

        ArrayList<String> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        String common = A[0];

        for (int i = 1; i < A.length; i++) {
            String s = A[i];
            for (int j = 0; j < s.length(); j++) {
                if (common.contains(s.charAt(j) + "")) {
                    sb.append(s.charAt(j) + "");
                    common = common.replaceFirst(s.charAt(j) + "", "");
                }
            }
            common = sb.toString();
            sb.delete(0, sb.length());
        }

        for (int i = 0; i < common.length(); i++) {
            list.add(common.charAt(i) + "");
        }

//        执行用时 :99 ms, 在所有 Java 提交中击败了 5.27% 的用户
//        内存消耗 :50.9 MB, 在所有 Java 提交中击败了 6.92% 的用户

        /**
         * 提交记录上比较良好的方案：
         * (思路)
         * 分两步：1：都出现，2：出现次数。
         * 先找出每个字符串的每个字符出现的次数，然后对所有每个字符出现的次数取最小值，最后得出每个字符对应的次数。
         * 分析：
         * 题目要求找出每个字符串都出现的字符组合成集合，那么必然有某个字符在每个字符串出现的次数都大于1，可以记录下来每个字符串出现字符的数量。
         * 首先的想到的的是哈希表，因为他有着键值对的关系。但鉴于A数组仅有小写字母组成的，字符串里的char本身就对应的一个数值。因此可以使用数组代替。
         * 于是["bella","label","roller"]
         * 就有
         * a[1,1,0]
         * b[1,1,0]
         * e[1,1,1]
         * l[2,2,2]
         * o[0,0,1]
         * r[0,0,1]
         * 很明显题目结果就是 e[1,1,1],l[2,2,2]了，最后需要将l转换成2个l。
         * 可以定义一个代表26个小写字母组成的行跟A数组长度组成的二维数组，将每个字符串出现的字符的数量统计起来。然后根据上面规律取最小值(共同出现的次数)
         * 最后将共同出现的字符跟出现数量组合的数组转换成题目要求的list即可。
         */

        return list;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例:
     * <p>
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * <p>
     * 题解:
     * 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
     * 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
     * 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
     * 时间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int problem53(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            max = max > sum ? max : sum;
        }

//        执行用时 :2 ms, 在所有 Java 提交中击败了 94.70% 的用户
//        内存消耗 :39.7 MB, 在所有 Java 提交中击败了 69.49% 的用户
        return max;
    }

    /**
     * 杨辉三角
     * 输入: 5
     * 输出:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1],
     * [1,5,10,10,5,1],
     * [1,6,15,20,15,6,1]
     * ]
     */
    public static List<List<Integer>> problem118(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows <= 0) {
            return list;
        }

        List<Integer> childList = new ArrayList<>();
        childList.add(1);
        list.add(childList);
        int i = 1;
        while (i < numRows) {
            List<Integer> newChildList = new ArrayList<>();
            List<Integer> tempList = list.get(i - 1);
            newChildList.add(1);
            for (int j = 0; j < tempList.size() - 1; j++) {
                newChildList.add(tempList.get(j) + tempList.get(j + 1));
            }
            newChildList.add(1);
            list.add(newChildList);
            i++;
        }


        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int j = 0; j < list.size(); j++) {
            List<Integer> list1 = list.get(j);
            sb.append("[");
            for (int k = 0; k < list1.size(); k++) {
                if (k == list1.size() - 1) {
                    sb.append(list1.get(k) + "]");
                } else {
                    sb.append(list1.get(k) + ",");
                }
            }
            if (j == list.size() - 1) {
                sb.append("]");
            } else {
                sb.append(",");
            }
        }

//        执行用时 :1 ms, 在所有 Java 提交中击败了 99.90% 的用户
//        内存消耗 :34.5 MB, 在所有 Java 提交中击败了 25.70% 的用户

        System.out.println(sb.toString());
        return list;
    }

    /**
     * 杨辉三角升级版: 传入参数K，返回三角的第K行。(k<= 33)
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> problem119(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        List<Integer> newList = new ArrayList<>();
        if (rowIndex <= 0) {
            return list;
        }

        list.add(1);
        int i = 1;
        while (i <= rowIndex) {
            newList.add(1);
            for (int j = 0; j < list.size() - 1; j++) {
                newList.add(list.get(j) + list.get(j + 1));
            }
            newList.add(1);

            list.clear();
            list.addAll(newList);
            newList.clear();
            i++;
        }

//        虽然能够通过，但是效率太低。
//        执行用时 :4 ms, 在所有 Java 提交中击败了 27.26% 的用户
//        内存消耗 :34.2 MB, 在所有 Java 提交中击败了 22.20% 的用户
        /**
         *
         * 提交记录为0ms的解法：通项公式法
         * List<Integer> list = new ArrayList<>(rowIndex+1);
         * int cur = 1;
         * for(int i=0;i<=rowIndex;i++){
         *   list.add(cur);
         *   cur = cur * (rowIndex-i)/(i+1);
         *  }
         */

        return list;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * 注意你不能在买入股票前卖出股票。
     * <p>
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices [1,2]
     * @return
     */
    public static int problem121(int[] prices) {

        int min = 0;
        int sub = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[min]) {
                min = i;
                continue;
            }
            if (prices[i] > prices[min] && i > min) {
                sub = prices[i] - prices[min] > sub ? prices[i] - prices[min] : sub;
            }
        }
        System.out.println(sub);

//        执行用时 :3 ms, 在所有 Java 提交中击败了 82.71% 的用户
//        内存消耗 :38.7 MB, 在所有 Java 提交中击败了 37.80% 的用户

        return sub;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public static int problem122(int[] prices) {

        /**
         * 题解：(来自大佬的点拨)
         * [7, 1, 5, 6] 第二天买入，第四天卖出，收益最大（6-1），所以一般人可能会想，怎么判断不是第三天就卖出了呢? 这里就把问题复杂化了，
         * 根据题目的意思，当天卖出以后，当天还可以买入，所以其实可以第三天卖出，第三天买入，第四天又卖出（（5-1）+ （6-5） === 6 - 1）。
         * 所以算法可以直接简化为只要今天比昨天大，就卖出。
         */
        int sub = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sub += prices[i] - prices[i - 1];
            }
        }
        System.out.println(sub);

//        执行用时 :2 ms, 在所有 Java 提交中击败了 97.16% 的用户
//        内存消耗 :39 MB, 在所有 Java 提交中击败了 25.91% 的用户
        return sub;
    }

    /**
     * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     * <p>
     * 示例 1:
     * 输入:
     * nums =[[1,2],[3,4]],r = 1, c = 4
     * 输出:
     * [[1,2,3,4]]
     * 解释:
     * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
     * 示例 2:
     * <p>
     * 输入:
     * nums =[[1,2],[3,4]],r = 2, c = 4
     * 输出:
     * [[1,2],
     * [3,4]]
     * 解释:
     * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
     * 注意：
     * <p>
     * 给定矩阵的宽和高范围在 [1, 100]。
     * 给定的 r 和 c 都是正数。
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public static int[][] problem566(int[][] nums, int r, int c) {
        int[][] ars = new int[r][c];
        int y = 0;
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (x >= c) {
                    y++;
                    x = 0;
                }
                ars[y][x] = nums[i][j];
                x++;
            }
        }

        System.out.println("x= " + x + "  y= " + y);
        showResult(ars);

        if (y != r - 1 || x != c) {
            return nums;
        }

//        执行用时 :3 ms, 在所有 Java 提交中击败了 90.03% 的用户
//        内存消耗 :50.8 MB, 在所有 Java 提交中击败了 45.28% 的用户

        return ars;
    }

    /**
     * 如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是托普利茨矩阵。
     * 给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。
     * 示例 1:
     * <p>
     * 输入:
     * matrix = [
     *   [1,2,3,4],
     *   [5,1,2,3],
     *   [9,5,1,2]
     * ]
     * 输出: True
     * <p>
     * 示例 2:
     * 输入:
     * matrix = [
     *   [1,2],
     *   [2,2]
     * ]
     * 输出: False
     * <p>
     * 说明:
     *  matrix 是一个包含整数的二维数组。
     * matrix 的行数和列数均在 [1, 20]范围内。
     * matrix[i][j] 包含的整数在 [0, 99]范围内。
     *
     * @param matrix
     * @return
     */
    public boolean problem766(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i < matrix.length - 1 && j < matrix[i + 1].length - 1 &&
                        matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }

//        执行用时 :2 ms, 在所有 Java 提交中击败了 98.68% 的用户
//        内存消耗 :41.6 MB, 在所有 Java 提交中击败了 86.84% 的用户

        return true;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * @param nums
     * @return
     */
    public int problem169(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        Map.Entry<Integer, Integer> entryTemp = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entryTemp == null) {
                entryTemp = entry;
            }
            entryTemp = entry.getValue() > entryTemp.getValue() ? entry : entryTemp;
        }

//        执行用时 :39 ms, 在所有 Java 提交中击败了 17.58% 的用户
//        内存消耗 :50.9 MB, 在所有 Java 提交中击败了 22.06% 的用户

//        return entryTemp.getKey();

        /**
         * 解法2:
         * 审题：必然存在众数，必然出现次数大于 ⌊ n/2 ⌋ 。
         * 执行用时 :4 ms, 在所有 Java 提交中击败了69.27%的用户
         * 内存消耗 :50.8 MB, 在所有 Java 提交中击败了23.45%的用户
         *
         */
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 给你两个数组，arr1 和 arr2，
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * <p>
     * 示例：
     * <p>
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     * <p>
     * 提示：
     * <p>
     * arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     * arr2 中的元素 arr2[i] 各不相同
     * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */
    public static int[] problem1122(int[] arr1, int[] arr2) {
        int p = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = p; j < arr1.length; j++) {
                if (arr2[i] == arr1[j]) {
                    if (p != j) {
                        arr1[p] = arr1[p] ^ arr1[j];
                        arr1[j] = arr1[p] ^ arr1[j];
                        arr1[p] = arr1[p] ^ arr1[j];
                    }
                    p++;
                }
            }
        }
        if (arr1.length - p >= 2) {
            for (int i = p; i < arr1.length - 1; i++) {
                for (int j = i + 1; j < arr1.length; j++) {
                    if (arr1[i] > arr1[j]) {
                        arr1[i] = arr1[i] ^ arr1[j];
                        arr1[j] = arr1[i] ^ arr1[j];
                        arr1[i] = arr1[i] ^ arr1[j];
                    }
                }
            }
        }
        showResult(arr1);

//        执行用时 :13 ms, 在所有 Java 提交中击败了 27.63% 的用户
//        内存消耗 :36.3 MB, 在所有 Java 提交中击败了 100.00% 的用户

        /**
         * 提交记录为0ms的解法：
         * 因为题目明确说明数组长度为1000，并且数组的内容为0-1000。所以可以使用一个恰好的数组arr3。将arr1数组内容
         * 做模板(arr1每个元素内容对应arr3的下标，arr1每个元素出现的次数对应arr3此下标的值)。因为要按照arr2的顺序
         * 来输出并且arr2的元素全部来自arr1。所以，创建结果数组arr4，遍历arr2，找到arr3对应arr2的对应下标的值，arr4开始依次添加。当arr2循环
         * 完毕后，arr4已经添加了arr2在arr1的所有数据了，剩下的再次遍历arr3，添加补齐。
         *
         * 可以将这种具有边界，且按照一定顺序输出的题目，都可以通过创建一个模板来处理



         int[] cnt = new int[1001];
         for (int n: arr1) cnt[n]++;

         int[] res = new int[arr1.length];
         int i = 0;
         for (int n: arr2) {
         for (int j = 0; j < cnt[n]; j++) {
         res[i++] = n;
         }
         cnt[n] = 0;
         }

         for (int j = 0; j <= 1000; j++) {
         for (int k = 0; k < cnt[j]; k++) {
         res[i++] = j;
         }
         }
         return res;
         */

        return arr1;
    }

    /**
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     * 示例 1:
     * <p>
     * 输入: [3,0,1]
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     * <p>
     * 说明:
     * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
     *
     * @param nums
     * @return
     */
    public static int problem268(int[] nums) {

        System.out.println(1 ^ 2);

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i)
                return i;
        }

//        执行用时 :16 ms, 在所有 Java 提交中击败了 19.54%的用户
//        内存消耗 :47.2 MB, 在所有 Java 提交中击败了 60.52% 的用户
        /**
         * 根源：由于异或运算满足结合律，并且对一个数进行两次完全相同的异或运算会得到原来的数，因此我们可以通过异或运算找到缺失的数字。
         *
         int result = nums.length;
         for (int i = 0; i < nums.length; i++) {
         result ^= i;
         result ^= nums[i];
         }
         return result;

         执行用时 :2 ms, 在所有 Java 提交中击败了 92.66% 的用户
         内存消耗 :48.5 MB, 在所有 Java 提交中击败了 33.17% 的用户*/

        return nums[nums.length - 1] + 1;
    }

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,1]
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: false
     * 示例 3:
     * <p>
     * 输入: [1,1,1,3,3,4,3,2,4,2]
     * 输出: true
     *
     * @param nums
     * @return
     */
    public boolean problem217(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

//        执行用时 :11 ms, 在所有 Java 提交中击败了 79.44% 的用户
//        内存消耗 :48.3 MB, 在所有 Java 提交中击败了 80.18% 的用户

        return false;
    }

    /**
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     * <p>
     * 输入：[1,1,1]
     * 输出：true
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length <= 50000
     * -100000 <= A[i] <= 100000
     *
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        boolean add = false;
        boolean min = false;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                add = true;
                if (min) return false;
            } else if (A[i] > A[i + 1]) {
                min = true;
                if (add) return false;
            }
        }
//        执行用时 :4 ms, 在所有 Java 提交中击败了 95.39% 的用户
//        内存消耗 :56.7 MB, 在所有 Java 提交中击败了 48.38% 的用户
        return true;
    }

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     * <p>
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * 输出: [1,2,2,3,5,6]
     */
    public static int[] problem88(int[] nums1, int m, int[] nums2, int n) {
        int index = 0;
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (nums1[i] != 0) {
                index = i + 1;
                break;
            }
        }

        for (int j = 0; j < nums2.length; j++) {
            nums1[index] = nums2[j];
            index++;
        }
        Arrays.sort(nums1);
//        执行用时 :2 ms, 在所有 Java 提交中击败了 25.66% 的用户
//        内存消耗 :36.4 MB, 在所有 Java 提交中击败了 75.72% 的用户
        //最简单的方法，效率较低。

        return nums1;
    }

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     * 说明:
     * <p>
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * 示例:
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] problem167(int[] numbers, int target) {
        int[] a = new int[2];
        int p = 0;
        int q = numbers.length - 1;
        while (p < q) {
            if (numbers[p] > target) return a;
            if (numbers[p] + numbers[q] == target) {
                a[0] = ++p;
                a[1] = ++q;
                System.out.println(p + "  " + q);
                return a;
            } else if (numbers[p] + numbers[q] < target) {
                p++;
            } else if (numbers[p] + numbers[q] > target) {
                q--;
            }
        }
//        执行用时 :4 ms, 在所有 Java 提交中击败了 29.83% 的用户
//        内存消耗 :39.2 MB, 在所有 Java 提交中击败了 18.48% 的用户
        return a;
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * <p>
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     * <p>
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的 原地 算法。
     *
     * @param nums
     * @param k
     */
    public static void problem189(int[] nums, int k) {
        while (k > 0) {
            int be = nums[0];
            int af;
            for (int i = 1; i < nums.length; i++) {
                af = nums[i];
                nums[i] = be;
                be = af;
            }
            nums[0] = be;
            k--;
            showResult(nums);
        }

//        执行用时 :139 ms, 在所有 Java 提交中击败了 19.35% 的用户
//        内存消耗 :39.4 MB, 在所有 Java 提交中击败了 22.25% 的用户
    }

    /**
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
     * 示例 1:
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean problem219(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int p = i + k < nums.length ? i + k : nums.length - 1;
            while (p > i) {
                if (nums[i] == nums[p]) {
                    return true;
                } else {
                    p--;
                }
            }
        }
        //题目有点抖机灵，一开始没审清楚题，以为俩个下标的差绝对值为K，其实是最大是K。提交了几个错误答案。
//        执行用时 :491 ms, 在所有 Java 提交中击败了 13.04% 的用户
//        内存消耗 :44.2 MB, 在所有 Java 提交中击败了 68.09% 的用户
        return false;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * @param nums
     */
    public static void problem283(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != index) {
                    nums[index] = nums[i];
                    nums[i] = 0;
                }
                index++;
            }
        }
        showResult(nums);

//        执行用时 :1 ms, 在所有 Java 提交中击败了 96.97% 的用户
//        内存消耗 :41.2 MB, 在所有 Java 提交中击败了 44.84% 的用户

    }

    public static int singleNumber(int[] nums) {
//        if (nums.length == 1) return nums[0];
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length; i += 2) {
//            if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
//                return nums[i];
//            }
//        }
//        return -1;

        int j = 1 ^ 2 ^ 3 ^ 1 ^ 3;
        System.out.println(j);

        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            a = nums[i] ^ a;
        }
        return a;
    }

    /**
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     * 示例 1:
     * 输入:
     * bits = [1, 0, 0]
     * 输出: True
     * 解释:
     * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
     * 示例 2:
     * 输入:
     * bits = [1, 1, 1, 1,1,1,1,1,1,1,1,1,1,1,0]
     * 输出: False
     * 解释:
     * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
     * 注意:
     * 1 <= len(bits) <= 1000.
     * bits[i] 总是0 或 1.
     */
    public static boolean problem717(int[] bits) {
        if (bits.length <= 1) {
            return bits[0] == 0;
        }
        if (bits[bits.length - 2] == 0) {
            return true;
        }
        for (int i = 0; i < bits.length; i++) {
            if (i == bits.length - 2 && bits[i] == 1) {
                return false;
            }
            if (bits[i] == 1) {
                i++;
            }
        }
        return true;

        /**
         执行用时 :0 ms, 在所有 java 提交中击败了100.00%的用户
         内存消耗 :36.3 MB, 在所有 java 提交中击败了87.85%的用户
         */
    }

    /**
     * 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
     * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
     * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
     *
     * @return
     */
    public static int problem1184(int[] distance, int start, int destination) {


        return 0;
    }


    //----------------分割线-----------------//

    /**
     * 寻找第三大的数
     * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
     * <p>
     * 解法关键:确立3个数，依次为最大，第二大，第三大。如果比较是大于并且不大于前一位的情况，默认将当前值给下一位，这个比较好处理;
     * 如果是小于的或者等于的情况，需要再次判断这个值是不是新出现的值(确立3个数时以第一个数作为初始化，便利到后面时需要修改，除非它没有第三大的数。)
     *
     * @param nums
     */
    public static int problem414(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        int first = nums[0];
        int second = nums[0];
        int third = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
                System.out.println(first + " " + second + " " + third);
                continue;
            }
            //大于第二个值
            if (nums[i] > second && nums[i] < first) {
                third = second;
                second = nums[i];
                System.out.println(first + " " + second + " " + third);
                continue;
            } else {
                //出现了一个新的值，要重新赋值(这个数未被初始化)
                if (first == second) {
                    second = nums[i];
                    System.out.println(first + " " + second + " " + third);
                    continue;
                }
            }
            if (nums[i] > third && nums[i] < second) {
                third = nums[i];
            } else {
                //出现了一个新值
                if (third == first || third == second) {
                    third = nums[i];
                }
            }
            System.out.println(first + " " + second + " " + third);
        }
        return (first == second || second == third) ? first : third;

//        执行用时 :80 ms, 在所有 java 提交中击败了 5.12% 的用户
//        内存消耗 :39 MB, 在所有 java 提交中击败了 48.62% 的用户
    }

    /**
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * <p>
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * <p>
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内
     * <p>
     *
     * @param nums
     * @return
     */
    public static List<Integer> problem448(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] > 0)
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
                System.out.println(i + 1);
            }
        }
        return list;
    }

    /**
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     * 找到所有出现两次的元素。
     * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
     *
     * @param nums
     * @return
     */
    public static List<Integer> problem442(int[] nums) {
        /**
         * 分析：
         *  第一个条件：“出现两次而其他元素出现一次”。对于出现2次的数，可以对它进行变换(取负数/乘个数(除个数))
         *  第二个条件：1 ≤ a[i] ≤ n （n为数组长度）。每个数都不会大于他的长度，另一层意思是每个数的值就对应
         *  数组中的那个下标。
         *
         *  通过修改每个值在数组中对应下标位置的数(出现两次的数会修改到两次那个)
         */


        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] > 0) {
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
            } else {
                list.add(Math.abs(nums[i]));
                System.out.print(" " + nums[i]);
            }
        }
        return list;
//        执行用时 :11 ms, 在所有 java 提交中击败了 43.11% 的用户
//        内存消耗 :47.4 MB, 在所有 java 提交中击败了 97.97% 的用户
    }

    /**
     * 给定一个二进制数组， 计算其中最大连续1的个数。
     * 示例 1:
     * <p>
     * 输入: [1,1,0,1,1,1]
     * 输出: 3
     * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
     *
     * @param nums
     */
    public static int problem485(int[] nums) {
        /**
         * 碰到1就开始计数，直到不为1，保存一个max值(与原来比较)，计数器重新计数
         */

        int max = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                max = Math.max(count, max);
                count = 0;
            }
        }
        max = Math.max(count, max);
        System.out.println(max);
        return max;
//        执行用时 :2 ms, 在所有 java 提交中击败了 100.00% 的用户
//        内存消耗 :39.6 MB, 在所有 java 提交中击败了 92.19% 的用户
    }

    /**
     * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j),
     * 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
     * 示例 1:
     * <p>
     * 输入: [3, 1, 4, 1, 5], k = 2
     * 输出: 2
     * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
     * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
     * 示例 2:
     * <p>
     * 输入:[1, 2, 3, 4, 5], k = 1
     * 输出: 4
     * 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
     * 示例 3:
     * <p>
     * 输入: [1, 3, 1, 5, 4], k = 0
     * 输出: 1
     * 解释: 数组中只有一个 0-diff 数对，(1, 1)。
     * <p>
     * 注意:
     * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
     * 数组的长度不超过10,000。
     * 所有输入的整数的范围在 [-1e7, 1e7]。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int problem532(int nums[], int k) {
        /**
         * 题目漏洞
         *  1. nums长度问题
         *  2. k值问题(等于0，小于0)
         */

//        //解法1
//        int result = 0;
//        if (k < 0) {
//            return result;
//        }
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int num : nums) {
//            //先将数组的中的数放到map中的key中保存，默认value的值为0，如果相同的value加1
//            Integer count = map.get(num);
//            map.put(num, count == null ? 1 : count + 1);
//        }
//        for (int key : map.keySet()) {
//            //如果k值为0，在map中找那些value > 1的key，如果不为0，寻找差值为K的的key
//            if (k == 0 && map.get(key) > 1) {
//                result++;
//                continue;
//            }
//            if (k > 0 && map.containsKey(key + k)) {
//                result++;
//            }
//        }
//        return result;


        if (nums.length < 2 || k < 0) return 0;
        Arrays.sort(nums);
        int count = 0;
        //用于保存前一个计算过的数字,如果前面统计过了就不再统计，换下一次
        int tem = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (tem == nums[i] && i != 0) {
                continue;
            }
            //保存当前统计过的数
            tem = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    count++;
                    break;
                }

                //这个值都已经大于k了，后面没必要在寻找了
                if (nums[j] - nums[i] > k) {
                    break;
                }
            }
        }
        return count;

//        执行用时 :5 ms, 在所有 Java 提交中击败了 98.23% 的用户
//        内存消耗 :42.5 MB, 在所有 Java 提交中击败了 5.24% 的用户
    }

    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 你找到的子数组应是最短的，请输出它的长度。
     * <p>
     * 示例 1:
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 0  1  2  3  4   5   6
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     * <p>
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     */
    public static int problem581(int[] nums) {

//        int[] tems = nums.clone();
//        Arrays.sort(tems);
//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == tems[i]) {
//                count++;
//            } else {
//                break;
//            }
//        }
//        if (count == nums.length) {
//            return 0;
//        }
//        for (int i = nums.length - 1; i > 0; i--) {
//            if (nums[i] == tems[i]) {
//                count++;
//            } else {
//                break;
//            }
//        }
//        执行用时 :7 ms, 在所有 Java 提交中击败了 63.18% 的用户
//        内存消耗 :41.7 MB, 在所有 Java 提交中击败了 15.46% 的用户

//        return nums.length - count;


        int len = nums.length;
        int max = nums[0];
        int min = nums[len - 1];
        int l = 0, r = -1;
        for (int i = 0; i < len; i++) {
            if (max > nums[i]) {
                r = i;
            } else {
                max = nums[i];
            }
            if (min < nums[len - i - 1]) {
                l = len - i - 1;
            } else {
                min = nums[len - i - 1];
            }
        }
        return r - l + 1;
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。
     * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    public static int[] problem40(int[] arr, int k) {
        int[] result = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;

//        执行用时 :7 ms, 在所有 Java 提交中击败了 72.81% 的用户
//        内存消耗 :42.6 MB, 在所有 Java 提交中击败了 100.00% 的用户
    }
}


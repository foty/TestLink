package com.example.testlink.calculate.dailyari.other;

import java.util.Random;

/**
 * Create by lxx
 * Date : 2021/9/1 14:59
 * Use by
 */
public class WeightSolution528 {
    /**
     * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标
     * i，选取下标 i 的概率与 w[i] 成正比。
     * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)
     * = 0.75（即，75%）。
     * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
     * <p>
     * 示例 1：
     * 输入：
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * 输出：
     * [null,0]
     * 解释：
     * Solution solution = new Solution([1]);
     * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
     * <p>
     * 示例 2：
     * 输入：
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * 输出：
     * [null,1,1,1,1,0]
     * 解释：
     * Solution solution = new Solution([1, 3]);
     * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
     * <p>
     * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
     * [null,1,1,1,1,0]
     * [null,1,1,1,1,1]
     * [null,1,1,1,0,0]
     * [null,1,1,1,0,1]
     * [null,1,0,1,0,0]
     * ......
     * 诸若此类。
     * <p>
     * 提示：
     * 1 <= w.length <= 10000
     * 1 <= w[i] <= 10^5
     * pickIndex 将被调用不超过 10000 次
     */

    /**
     * 思路：
     * 题目解析：1、要返回一个下标。2、符合要求的下标概率对应数组w中的权重。3、这个下标是随机的。
     * 第一点放到最后。先看第二点，返回的下标的选取概率对应数组w的权重。这里就是会迷惑人，一时间不太懂到底什么意思。换种思维处理一下：
     * 假如w数组[1,3]，对应的下标[0,1]。额外构建一个长度为w元素之和的数组t，里面元素从小到大排有t=[1,2,3,4]。现在定义如果从t中返回1，代表下标为0；
     * 返回2、3、4代表下标1。到这里可以看到返回下标0,1的概率是与w相对应的。所以，从这里可以知道题目关键点就是要创建t这样的一个数组。那么对于给出一个
     * 数组w要求下标，就不可能全部一一定义下标对应的区间范围，这样太麻烦。可以使用前缀和创建数组t。t[i]为对应下标i所在区间的最大值。只要随机值x小于或
     * 等于t[i]就能表示属于i这个区间，即：
     * t[i-1] +1 < x <= t[i]
     * 这里只需要判断x是否<=t[i]即可。(优化方案：使用二分在t中找)
     */

    int pre[];
    Random r;

    public WeightSolution528(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            pre[i] = w[i] + pre[i - 1];
        }

        r = new Random();
    }

    public int pickIndex() {
        //生成一个随机数,注意要加1，避免生成的随机数为0
        int s = r.nextInt(pre[pre.length - 1]) + 1;
        //找到随机数s对应的区间i
        for (int i = 0; i < pre.length; i++) { //这里可以使用二分优化
            if (s <= pre[i]) return i;
        }
        return 0;
    }
}

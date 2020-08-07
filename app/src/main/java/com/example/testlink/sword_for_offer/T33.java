package com.example.testlink.sword_for_offer;

import android.os.TokenWatcher;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/7/31 14:50
 * Use by 把数组中的数排成一个最小的数
 */
public class T33 {

    /**
     * 输入: [10,2]
     * 输出: "102"
     * <p>
     * <p>
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     */


    /**
     * 排序。
     *
     * 思路：从题目给的例子其实可以看出来这题的关键。例子：[10,2]:正确结果为102.
     * 于是可以得出这样思路：每一个数从它的第一位开始，最小的优先级越高(排在前面)，
     * 0的优先级高于位数不够的。例子[10.2]，第一位是分别是1,2。所以最小排法为102，
     * 即10 + 2。如果是[3,30]。第一位都相同，看第二位,。一个没有，一个为0，0的优先
     * 级是要高于位数不够的，所以最小排法为303，即30 + 3。到这里其实解决方案已经出
     * 来了：比较俩个数A,B，判断A+B与B+A组合大小即可。因为最后是要返回string，所以
     * 可以使用string的compareTo()方法定义比较规则。
     *
     * @param nums
     * @return
     */
    public String solve(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (o1, o2) ->
                (o1 + o2).compareTo(o2 + o1)
        );
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }
}

package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/9/4 11:07
 * Use by 将字符串转换为整数。
 * 描述:
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。当我们寻找
 * 到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数
 * 的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函
 * 数不需要进行转换。若函数不能进行有效的转换时，请返回 0,如果数值超过 [−2^31,2^31 − 1]这个范围，请返
 * 回INT_MAX(2^31 −1)或INT_MIN(−2^31) 。
 */
public class T48 {

    public int solve(String str) {
        if (str == null) return 0;
        //去掉所有空格。
        str = str.trim();
        if (str.length() <= 0) return 0;

        int p = 0;
        int flag = 1;
        int result = 0;

        if (str.charAt(p) == '-') {
            flag = -1;
            p++;
        } else if (str.charAt(p) == '+') {
            p++;
        }
        //进行拼接当前数的前最大值(因为拼接时是x10的，所以这个数最大值要除以10)
        int maxValue = Integer.MAX_VALUE / 10;

        while (p < str.length()) {

            if (str.charAt(p) < '0' || str.charAt(p) > '9') break; // 跳出循环，返回当前计算的数字。
            //边界处理:
            // 这里表面上只判断了最大临界值2147483647(前面除以了10)，而没有判断最小临界值。但其实判
            // 不判断最终结果都是一样的：都是返回Integer.MIN_VALUE(-2147483648)。即最后一个值大于7还
            // 是大于8或等于8，返回值都是同一个。
            if (result > maxValue || result == maxValue && (str.charAt(p) - '0') > 7) {
                return flag == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            //计算数字。
            result = result * 10 + (str.charAt(p) - '0');

            p++;
        }
        return result;
    }

}

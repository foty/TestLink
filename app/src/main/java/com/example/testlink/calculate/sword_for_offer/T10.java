package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/8 16:53
 * Use by 统计二进制中1的个数
 *
 * <p>
 * 位运算操作:
 * 左移（ << ） 整体左移，右边空出位补零，左边位舍弃 （-4 << 1 = -8） a<<b = a * 2^b
 * 右移（ >> ） 整体右移，左边空出位补零或补1（负数补1，整数补0），右边位舍弃 （-4 >> 1 = -2）a>>b = a/2^b
 * 无符号右移（ >>> ）同>>,但不管正数还是负数都左边位都补0 （-4 >>> 1 = 2147483646）
 * 与（ & ）每一位进行比较，两位都为1，结果为1，否则为0（-4 & 1 = 0）
 * 或（ | ）每一位进行比较，两位有一位是1，结果就是1（-4 | 1 = -3）
 * 非（ ~ ） 每一位进行比较，按位取反（符号位也要取反）（~ -4 = 3）  ~a = -(a+1), ~ -a = a-1
 * 异或（ ^ ）每一位进行比较，相同为0，不同为1（^ -4 = -3）
 */
public class T10 {

    /**
     * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数
     * 。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     */


    /**
     * 解法1 使用jdk里面的方法
     *
     * @param n
     * @return
     */
    private int solute1(int n) {

        return Integer.bitCount(n);
    }

    /**
     * 根据与运算性质，统计。(两位都为1，结果为1，否则为0)。
     * 对每一位数都与1做&运算。
     * 问：如何让n的每一位都与1进行&运算，二进制中又没有索引什么的，如何移动?
     * 答: 作右移运算。
     *
     * @param i
     * @return
     */
    private int solute2(int i) {
        int sum = 0;
        while (i != 0) {
            //使用1 & 上 n。
            sum += (i & 1);
            // n往右移(无符号)
            i >>>= 1;
        }
        return sum;
    }


    /**
     * 根据: 任意一个数n，与(&)上比自己小1的数(n-1)，都会将数n中最低位的1变为0。
     * 这条性质消除1。每次消除1，计数器 +1。直到n == 0。
     *
     * @param n
     */
    private int solve3(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n = n & (n - 1);
        }
        return sum;
    }
}

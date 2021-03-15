package com.example.testlink.calculate.dailyari;

/**
 * Create by lxx
 * Date : 2021/3/13
 * Use by
 */
public class MyHashSet {
    /**
     * 题目说明：
     * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
     * <p>
     * 实现 MyHashSet 类：
     * <p>
     * void add(key) 向哈希集合中插入值 key 。
     * bool contains(key) 返回哈希集合中是否存在这个值 key 。
     * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
     * <p>
     * 提示：
     * 0 <= key <= 10^6
     * 最多调用 104 次 add、remove 和 contains 。
     */

    /**
     * 解法：
     * 可以搞一个偷鸡的方式。 key的类型限定为int，并且set的长度为10^6。可以创建一个相同大小的数组来保存数据。
     * yysy时间效率还是很高的。
     */

    boolean[] save;

    MyHashSet() {
        save = new boolean[1000001];
    }

    public void add(int key) {
        save[key] = true;
    }

    public void remove(int key) {
        save[key] = false;
    }

    public boolean contains(int key) {
        return save[key];
    }

//    执行用时：16 ms, 在所有 Java 提交中击败了97.37%的用户
//    内存消耗：46.9 MB, 在所有 Java 提交中击败了19.93%的用户
}

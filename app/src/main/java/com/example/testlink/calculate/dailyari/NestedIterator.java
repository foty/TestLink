package com.example.testlink.calculate.dailyari;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class NestedIterator implements Iterator<Integer> {

    /**
     * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
     * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
     *
     * 示例 1:
     * 输入: [[1,1],2,[1,1]]
     * 输出: [1,1,2,1,1]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
     * 示例 2:
     * 输入: [1,[4,[6]]]
     * 输出: [1,4,6]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
     */

    /**
     * 思路：
     * 借助队列来展开列表保存。
     */

    LinkedList<Integer> linkedList;

    public NestedIterator(List<NestedInteger> nestedList) {
        linkedList = new LinkedList<>();
        dfs( nestedList);
    }

    @Override
    public Integer next() {
        return linkedList.pollLast();
    }

    @Override
    public boolean hasNext() {
        return linkedList.size() > 0;
    }

    private void dfs( List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            if (nestedList.get(i).isInteger()) {
                linkedList.push(nestedList.get(i).getInteger());
            } else {
                dfs( nestedList.get(i).getList());
            }
        }
    }

    // 慢就慢点吧，又不是不能用
//    执行用时：6 ms, 在所有 Java 提交中击败了7.81%的用户
//    内存消耗：40.8 MB, 在所有 Java 提交中击败了60.59%的用户
}
package com.example.testlink.calculate.dailyari.other;

public class Trie208 {

    /**
     * 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动
     * 补完和拼写检查。
     * <p>
     * 请你实现 Trie 类：
     * <p>
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word)如果字符串word在前缀树中，返回true（即，在检索之前已经插入）;否则，返回false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为prefix ，返回 true ；
     *否则，返回 false 。
     * <p>
     */

    /**
     * 思路： 查看类 {@link com.example.testlink.data_structure.Trie}的实现
     */
    TNode root;

    public Trie208() {
        root = new TNode();

    }

    public void insert(String word) {
        TNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a'; // 默认以a开头，1=b，2=c的格式
            if (node.child[c] == null) node.child[c] = new TNode();
            node = node.child[c];
        }
        node.end = true;
    }

    public boolean search(String word) {
        TNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (node.child[c] == null) return false;
            node = node.child[c];
        }
        return node.end; // 如果node.end为真，表示存在这个字符串，否则只是存在以此为前缀的字符串
    }

    public boolean startsWith(String prefix) {
        TNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if (node.child[c] == null) return false;
            node = node.child[c];
        }
        return true;
    }

    /**
     * 节点实现
     */
    class TNode {
        boolean end;
        // 题目声明全是小写英文字母。
        TNode[] child = new TNode[26];
    }
}
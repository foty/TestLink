package com.example.testlink.data_structure;

/**
 * 前缀树/字典树
 */
public class Trie {

    /**
     * 根节点
     */
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * 插入
     *
     * @param s
     */
    public void insert(String s) {
        TrieNode n = root;
        for (int i = 0; i < s.length(); i++) {
            // 遍历每一个字符
            int c = s.charAt(i) - 'a';
            //如果不存在则在对应位置创建节点
            if (n.tns[c] == null) n.tns[c] = new TrieNode();
            // 移动指针指向该节点
            n = n.tns[c];
        }
        // 完成了最后一个字符的插入，要将尾结点设置为true，表示字典树存在了该字符串。
        n.end = true;
    }

    /**
     * 查询
     *
     * @param s
     * @return
     */
    public boolean search(String s) {
        TrieNode n = root;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (n.tns[c] == null) return false;
            n = n.tns[c];
        }
        // 如果n.end为真，表示存在这个字符串，否则只是存在以此为前缀的字符串
        return n.end;
    }

    /**
     * 以某字符为前缀
     *
     * @param s
     * @return
     */
    public boolean startsWith(String s) {
        TrieNode n = root;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (n.tns[c] == null) return false;
            n = n.tns[c];
        }
        // 这里是求是否有这个前缀，前面没有直接return，这里可以直接为true。
        return true;
    }
}
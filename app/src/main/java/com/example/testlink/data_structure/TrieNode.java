package com.example.testlink.data_structure;

/**
 * 前缀树/字典树 节点类
 */
class TrieNode {
    /**
     * 是否为某字符串的结尾字符
     */
    boolean end;
    /**
     * 指向子节点的指针数组，一般是固定数组大小的，这里以26个字母纯大(小)写字母表示。
     */
    TrieNode[] tns = new TrieNode[26];
}
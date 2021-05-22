
回溯:
```
/**
     * 朴素模式匹配算法
     *
     * 在字符匹配的过程中，不断的往回移动主串的指针来和模式串进行对比，这种行为称为回溯。
     *
     * @param s1 a,b,c,d,e,f,t,y,q,w,s,d
     * @param s2 f,t,y,q,w
     */
    private void math(String s1, String s2) {

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int count = chars1.length - chars2.length;//外层循环次数

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (chars1[i + j] != chars2[j]) {  //内层依次比较主串与模版串
                    break;
                }
                //成功条件(匹配到了最后一个，没有跳出循环，说明这个是完全匹配的2个子串)
                if (j == chars2.length-1){

                }
            }
        }
    }
```

递归

KMP算法: 字符串模式匹配算法。解决的问题就是在字符串（也叫主串）中的模式（pattern）定位问题。

深度优先搜索(DFS-Depth First Search)

广度优先搜索(BFS-Breadth First Search)

排序(8大排序算法)

二分法 (操作有序数列)

斐波那契数列思想(递归 经典问题-青蛙爬台阶)。

动态规划 (DP-Dynamic Programming )

快速幂算法: 快速算出指数非常大的幂。经典题目: 求数值x的整数n次方。

乾坤大挪移(链表的删除节点操作)。

快慢指针

分治法

统计法

摩尔投票法(基于某元素数量超过总数的一半。核心思想“正负抵消”)

埃氏筛(厄拉多啦筛、线性筛)<p204:计算质数>

博弈类型(数学推理)<810、黑板异或游戏>
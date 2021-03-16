package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2021/2/26 17:05
 * Use by 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每
 * 一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在
 * 下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * <p>
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * <p>
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进
 * 入这个格子。
 * <p>
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 */
public class T63 {

    public static void main(String[] args) {
        T63 t = new T63();
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board1 = new char[][]{{'A', 'B'}, {'C', 'D'}};
        char[][] board2 = new char[][]{{'A', 'A'}};

        boolean b = t.exist(board1, "ABCD");
        System.out.println(b);
    }

    /**
     * 回溯+dfs
     * <p>
     * 解法分析：
     * 因为路径可以从矩阵中的任意一格开始，所以char[][]的每一个元素都要访问到，所以大框架就是2层for循环。接下来是
     * 每一步都可上下左右四个方向遍历，所以每个位置的最终是否可行都是由四个方向结果合并而成，只要有一方成功即为成功。这
     * 里有一个很关键的因素就是，如何能够保存到4个方向的结果集。这里就是使用递归的方式进行处理。只要这个位置是符合路径的
     * 那就从这个位置开始找下一个位置，4个方向没有找到，回退到这个位置，换个方向继续找。因为涉及到每个元素只能访问一次，所
     * 以还需要有一个记录访问路径的变量。这里也是选择一个二维数组。访问过一种表示，未访问一种表示。最后回退时初始化访问状态。
     * 还有就是回退时，对比的路径字符也要回退(因为这点开始没有想到，导致一直不能AC)
     */

    int[][] visit;
    int index;

    public boolean exist(char[][] board, String word) {
        visit = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                index = 0;
                if (dfs(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] source, int i, int j, String word) {
        //访问过或者越界
        if (i < 0 || j < 0 || i >= source.length || j >= source[0].length || visit[i][j] != 0)
            return false;
        // 不匹配
        if (source[i][j] != word.charAt(index)) return false;
        //成功找完路径
        if (index == word.length() - 1) return true;

        //已经找到一个，开始找下一个字符。
        visit[i][j] = 1; //表示访问过
        index++;

        //四个方向找。
        boolean result = dfs(source, i, j + 1, word);// 右边找
        result = result || dfs(source, i, j - 1, word);// 左边找
        result = result || dfs(source, i - 1, j, word);// 上边找
        result = result || dfs(source, i + 1, j, word);// 下边找

        // 重置状态，方便下一次选择节点规划路径。
        visit[i][j] = 0;
        index--;
        return result;
    }

//    执行用时：8 ms, 在所有 Java 提交中击败了26.47%的用户
//    内存消耗：39.5 MB, 在所有 Java 提交中击败了92.49%的用户
}

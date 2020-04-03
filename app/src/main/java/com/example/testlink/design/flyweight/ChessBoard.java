package com.example.testlink.design.flyweight;

import android.util.SparseArray;

/**
 * Created by lxx on 2019/10/28.
 * Use by
 */

public class ChessBoard {
    private SparseArray<Chess> map;
    public static int WHITE_CHESS = 10000;
    public static int BLACK_CHESS = 10001;

    public ChessBoard() {
        map = new SparseArray<>();
        WhiteChess w = new WhiteChess();
        map.put(WHITE_CHESS, w);
        BlackChess b = new BlackChess();
        map.put(BLACK_CHESS, b);
    }

    public Chess getChess(int key) {
        return map.get(key);
    }
}

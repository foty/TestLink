package com.example.testlink.design.flyweight;

import android.util.Log;

/**
 * Created by lxx on 2019/10/28.
 * Use by
 */

public abstract class Chess {
    public abstract String color();

    public void showColor() {
        Log.d("TAG", color() + "棋");
    }
}

package com.example.testlink.design.bridge;

import android.util.Log;

/**
 * Created by lxx on 2019/10/28.
 * Use by
 */

public abstract class Flower {
    private Color color;

    public Flower(Color color) {
        this.color = color;
    }

    /**
     * 声明一个公共部分的方法
     * @return
     */
    public abstract String getFlower();

    public void show() {
        Log.d("TAG", "一朵" + color.getColor() + "色的" + getFlower());
    }
}

package com.example.testlink.design.bridge;

/**
 * Created by lxx on 2019/10/28.
 * Use by
 */

public class ChineseRose extends Flower {

    public ChineseRose(Color color) {
        super(color);
    }

    @Override
    public String getFlower() {
        return "月季";
    }
}

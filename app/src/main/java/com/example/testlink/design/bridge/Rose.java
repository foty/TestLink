package com.example.testlink.design.bridge;

/**
 * Created by lxx on 2019/10/28.
 * Use by
 */

public class Rose extends Flower {

    public Rose(Color color) {
        super(color);
    }

    @Override
    public String getFlower() {
        return "玫瑰";
    }
}

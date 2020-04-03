package com.example.testlink.design.decorate;

/**
 * Created by lxx on 2019/11/18.
 * Use by
 */

public abstract class UpFood implements Food {

    /**
     * 抽象装饰器(加料)
     */

    private Food food;

    public UpFood(Food food){
        this.food = food;
    }

    @Override
    public void make() {
        food.make();
    }
}

package com.example.testlink.design.decorate;

/**
 * Create by lxx
 * Date : 2020/1/16 11:38
 * Use by
 */
public abstract class MakeFood implements Food {
    /**
     * 制作装饰器
     */
    protected Food food;

    public MakeFood(Food food) {
        this.food = food;
    }

    @Override
    public void make() {
        food.make();
    }
}

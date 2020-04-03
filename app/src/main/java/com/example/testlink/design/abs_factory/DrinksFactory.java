package com.example.testlink.design.abs_factory;

/**
 * Created by lxx on 2019/5/16.
 * Use by
 */

public interface DrinksFactory {
    //饮料
    Drinks dBuild();

    //面包
    Bread bBuild();

    void buyWhat();
}

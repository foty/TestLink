package com.example.testlink.design.factory;

/**
 * Created by lxx on 2019/5/16.
 * Use by
 */

public class LemonFactory implements DrinksFactory {
    @Override
    public Drinks build() {
        return new LemonDrinks();
    }
}

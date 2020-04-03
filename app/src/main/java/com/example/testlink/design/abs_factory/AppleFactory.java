package com.example.testlink.design.abs_factory;


import android.util.Log;

/**
 * Created by lxx on 2019/5/16.
 * Use by
 */

public class AppleFactory implements DrinksFactory {

    @Override
    public Drinks dBuild() {
        return new AppleDrinks();
    }

    @Override
    public Bread bBuild() {
        return new AppleBread();
    }

    @Override
    public void buyWhat() {
        Log.d("buyWhat", "buyWhat: "+dBuild().otherDoSing()+", "+bBuild().buy());
    }

}

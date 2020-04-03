package com.example.testlink.design.decorate;

import android.util.Log;

/**
 * Create by lxx
 * Date : 2020/1/16 11:26
 * Use by
 */
public  class Hamburger extends MakeFood {


    public Hamburger(Food food) {
        super(food);
    }
    @Override
    public void make() {
        super.make();
        Log.d("LXX", "加鸡腿，加蔬菜");
    }
}

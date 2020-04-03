package com.example.testlink.design.factory;

import android.util.Log;

/**
 * Created by lxx on 2019/5/16.
 * Use by
 */

public abstract class Drinks {
    public String doSing() {
        return "水, " + "糖, ";
    }

    public abstract String otherDoSing();

    public void make() {
        Log.d("制作配方", doSing() + otherDoSing());
    }
}

package com.example.testlink.design.decorate;

import android.util.Log;

/**
 * Created by lxx on 2019/11/18.
 * Use by
 */

public class Bread implements Food {

    /**
     * 具体产品
     */
    @Override
    public void make() {
        Log.d("LXX", "加水，奶油，面粉");
    }
}

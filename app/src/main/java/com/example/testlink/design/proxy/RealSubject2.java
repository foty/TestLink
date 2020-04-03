package com.example.testlink.design.proxy;

import android.util.Log;

/**
 * Created by lxx on 2019/6/10.
 * Use by
 */

public class RealSubject2 implements Subject {
    @Override
    public void buyIphoneX() {
        Log.d("lxx", "buyIphoneX: 我去买iPhone X了,高配版，白色");
    }
}

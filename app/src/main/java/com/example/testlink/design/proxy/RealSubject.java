package com.example.testlink.design.proxy;

import android.util.Log;

/**
 * Created by lxx on 2019/6/10.
 * Use by
 */

public class RealSubject implements Subject {
    @Override
    public void buyIphoneX() {
        Log.d("ProxySubject", "buyIphoneX: 我去买iPhone X了，标准版");
    }
}

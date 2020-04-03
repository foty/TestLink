package com.example.testlink.design.proxy;

import android.util.Log;

/**
 * Created by lxx on 2019/6/10.
 * Use by
 */

public class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject() {
        subject = new RealSubject();
    }

    @Override
    public void buyIphoneX() {
        this.goToAmerica();
        subject.buyIphoneX();
        this.goBack();
    }

    private void goToAmerica() {
        Log.d("ProxySubject", "goToAmerica: 去美国");
    }

    private void bargain() {
        Log.d("ProxySubject", "bargain: 讲价");
    }

    private void goBack() {
        Log.d("ProxySubject", "goBack: 返程");
    }
}

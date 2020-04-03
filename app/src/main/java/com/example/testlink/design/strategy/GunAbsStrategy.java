package com.example.testlink.design.strategy;

import android.util.Log;

/**
 * Created by lxx on 2019/10/25.
 * Use by 行为型设计模式
 */

public class GunAbsStrategy implements AbsStrategy {
    @Override
    public void fight() {
        Log.d("TAG", "FIGHT WITH GUN");
    }
}

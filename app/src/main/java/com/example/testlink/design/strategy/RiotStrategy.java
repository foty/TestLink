package com.example.testlink.design.strategy;

import android.util.Log;

/**
 * Created by lxx on 2019/10/25.
 * Use by
 */

public class RiotStrategy implements AbsStrategy {
    @Override
    public void fight() {
        Log.d("TAG", "FIGHT WITH RIOT");
    }
}

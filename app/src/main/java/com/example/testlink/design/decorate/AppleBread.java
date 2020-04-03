package com.example.testlink.design.decorate;

import android.util.Log;

/**
 * Created by lxx on 2019/11/18.
 * Use by
 */

public class AppleBread extends UpFood {

    /**
     * 具体装饰
     */

    public AppleBread(Food food) {
        super(food);
    }

    @Override
    public void make() {
        super.make();
        add();
    }

    private void add() {
        Log.d("LXX", "加苹果酱");
    }

}

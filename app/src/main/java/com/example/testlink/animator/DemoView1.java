package com.example.testlink.animator;

import android.view.View;

/**
 * Create by lxx
 * Date : 2020/4/9 14:22
 * Use by
 */
public class DemoView1 {

    private View view;

    public DemoView1(View view){
        this.view = view;
    }

    public int getHeight() {
        return view.getLayoutParams().height;
    }

    public void setHeight(int height){
        view.getLayoutParams().height = height;
        view.requestLayout();
    }
}

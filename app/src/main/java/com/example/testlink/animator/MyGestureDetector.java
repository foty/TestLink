package com.example.testlink.animator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.example.testlink.R;

/**
 * Create by lxx
 * Date : 2020/4/7 16:11
 * Use by 手势检测 包括 单击、双击、长按、缩放、滑动
 */
public class MyGestureDetector extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_flying_ball);
    }
}

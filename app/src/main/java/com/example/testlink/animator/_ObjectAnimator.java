package com.example.testlink.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.testlink.R;

/**
 * Created by lxx on 2019/6/14.
 * Use by ObjectAnimator的介绍。
 */

public class _ObjectAnimator extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ImageView img = findViewById(R.id.img);

        //加载xml动画
//        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.my_object_animation);
//        animator.setTarget(img);
//        animator.start();

        //代码实现动画  alpha  rotationX  translationX  scaleX
//        //旋转动画
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, "scaleX", 1, 2).setDuration(3000);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setRepeatCount(-1);
        animator1.start();

        //自定义view属性动画(本质就是通过该属性的set，get方法改变属性值)
//        DemoView1 view = new DemoView1(img);
//        ObjectAnimator animator1 = ObjectAnimator.ofInt(view, "height", 120, 0).setDuration(3000);
//        animator1.setRepeatMode(ValueAnimator.REVERSE);
//        animator1.setRepeatCount(-1);
//        animator1.start();


    }
}

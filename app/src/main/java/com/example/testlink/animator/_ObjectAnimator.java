package com.example.testlink.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.my_object_animation);
        animator.setTarget(img);
        animator.start();


        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, "rotation", 0, 360).setDuration(3000);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setRepeatCount(-1);
        animator1.start();
    }
}

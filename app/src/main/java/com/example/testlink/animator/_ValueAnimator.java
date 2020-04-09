package com.example.testlink.animator;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.testlink.R;

/**
 * Created by lxx on 2019/6/14.
 * Use by ValueAnimator的介绍
 */

public class _ValueAnimator extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ImageView img = findViewById(R.id.img);

//        startByXml(img);

        startByCode(img);
    }

    private void startByCode(ImageView img) {
        PropertyValuesHolder holder =  PropertyValuesHolder.ofFloat("rotation",0,360);
        ValueAnimator animator1 = ValueAnimator.ofPropertyValuesHolder(holder)
                .setDuration(3000);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               float f = (float) animation.getAnimatedValue();
                img.setRotation(f);
            }
        });
        animator1.setTarget(img);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setRepeatCount(-1);
        animator1.start();
    }

    private void startByXml(ImageView img) {
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.my_animator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                img.setRotation(f);
            }
        });
        animator.setTarget(img);
        animator.start();
    }
}

package com.example.testlink.animator;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.testlink.R;

/**
 * Create by lxx
 * Date : 2020/4/8 15:21
 * Use by
 */
public class MyAnimation extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ImageView image = findViewById(R.id.img);

//        startByXml(image);


        //
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_launcher_background), 1000);
        animationDrawable.setOneShot(true);
        image.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }

    /**
     * xml动画播放
     * @param image
     */
    private void startByXml(ImageView image) {
        image.setImageResource(R.drawable.my_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
        animationDrawable.start();
        //animationDrawable.stop();停止播放
    }
}

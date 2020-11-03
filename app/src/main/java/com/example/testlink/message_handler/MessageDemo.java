package com.example.testlink.message_handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Create by lxx
 * Date : 2020/11/2 16:25
 * Use by
 */
public class MessageDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().post(() -> {

        });
    }
}

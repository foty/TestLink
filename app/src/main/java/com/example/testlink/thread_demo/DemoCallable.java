package com.example.testlink.thread_demo;

import java.util.concurrent.Callable;

/**
 * Created by lxx on 2019/12/2.
 * Use by
 */

public class DemoCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }
}

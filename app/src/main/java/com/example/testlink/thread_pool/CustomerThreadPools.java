package com.example.testlink.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by lxx
 * Date : 2020/7/1 18:10
 * Use by 自定义线程池
 */
public class CustomerThreadPools extends ThreadPoolExecutor {

    public CustomerThreadPools() {
        super(3, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
    }



    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(Thread.interrupted());

        System.out.println(t.isInterrupted());

        System.out.println(t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }
}

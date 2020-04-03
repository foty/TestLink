package com.example.testlink.thread_demo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lxx on 2019/12/2.
 * Use by
 */

public class ThreadDemo {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    private final ThreadPoolExecutor executor;
    private List<Future<String>> futures;


    public ThreadDemo() {

        /**
         * corePoolSize : 核心线程数线程数定义了最小可以同时运行的线程数量。
         * maximumPoolSize : 当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
         * keepAliveTime:当线程池中的线程数量大于 corePoolSize 的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，
         *               而是会等待，直到等待的时间超过了 keepAliveTime才会被回收销毁；
         * unit : keepAliveTime 参数的时间单位。
         * workQueue: 当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，信任就会被存放在队列中。
         * handler :饱和策略,共有4种。
         */
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 使用 runnable执行。
     * <p>
     * Runnable 接口不会返回结果或抛出检查异常，但是Callable 接口可以。
     * 所以，如果任务不需要返回结果或抛出异常推荐使用 Runnable 接口，这样代码看起来会更加简洁。
     */
    public void executRunnable() {
        executor.execute(new DemoRunnable());
    }


    /**
     * 使用Callable执行
     */
    public void executCall() {
        if (futures == null) {
            futures = new ArrayList<>();
        }

        Callable<String> callable = new DemoCallable();
        Future<String> future = executor.submit(callable);
        futures.add(future);
    }

    public void look() {
        if (futures == null) return;
        for (Future<String> future : futures) {
            try {
                Log.d("LXX", future.get());


            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭线程池，线程池的状态变为 SHUTDOWN。线程池不再接受新任务了，但是队列里的任务得执行完毕。
     */
    public void stop() {
        executor.shutdown();
    }

    /**
     * 关闭线程池，线程的状态变为 STOP。线程池会终止当前正在运行的任务，并停止处理排队的任务并返回正在等待执行的 List。
     */
    public void qiut() {
        List<Runnable> runnables = executor.shutdownNow();
    }

}

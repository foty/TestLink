package com.example.testlink.rx;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author lxx
 * @date 2019/3/14
 * <p>
 * 创建操作符。作用：延时，定时，循环遍历，范围内操作，最近更新
 */
public class Create {
    static int i = 0;

    public static void create() {
        /**
         *  create
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logs("create", "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Logs("create", "接受到的数字是： " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Logs("create", "onError");
            }

            @Override
            public void onComplete() {
                Logs("create", "onComplete");
            }
        });

        /**
         * 默认最基本的操作，没什么好说的
         * 2019-03-15 09:47:18.097 3945-3945/com.example.testlink D/create: onSubscribe
         * 2019-03-15 09:47:18.102 3945-3945/com.example.testlink D/create: 接受到的数字是： 1
         */
    }

    public static void defer() {
        /**
         * 延迟 defer
         */
        i = 100;
        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        i = 1000;
        Observer<Integer> observer1 = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logs("defer1", "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Logs("defer1", "onNext---->" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Logs("defer1", "onError");
            }

            @Override
            public void onComplete() {
                Logs("defer1", "onComplete");
            }
        };
        defer.subscribe(observer1);
        /**
         * 输出结果：是1000，而不是原来的100
         * 2019-03-14 15:59:22.310 4015-4015/com.example.testlink D/defer1: onSubscribe
         * 2019-03-14 15:59:22.311 4015-4015/com.example.testlink D/defer1: oonNext---->1000
         * 2019-03-14 15:59:22.311 4015-4015/com.example.testlink D/defer1: onComplete
         *
         * tips
         * 这个defer操作符会以被订阅后的最终的为结果，场景：一些变量在被订阅前可能会被重复修改，但是只要求最后的一次修改结果。
         */
    }

    public static void fromArray() {
        Integer[] is = {1, 2, 3, 4, 5, 6};
        Observable.fromArray(is).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Logs("fromArray", "integer----> " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * fromArray 数组的遍历
         * 2019-03-15 09:47:18.138 3945-3945/com.example.testlink D/fromArray: integer----> 1
         * 2019-03-15 09:47:18.139 3945-3945/com.example.testlink D/fromArray: integer----> 2
         * 2019-03-15 09:47:18.139 3945-3945/com.example.testlink D/fromArray: integer----> 3
         * 2019-03-15 09:47:18.140 3945-3945/com.example.testlink D/fromArray: integer----> 4
         * 2019-03-15 09:47:18.140 3945-3945/com.example.testlink D/fromArray: integer----> 5
         * 2019-03-15 09:47:18.140 3945-3945/com.example.testlink D/fromArray: integer----> 6
         */
    }

    public static void fromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Logs("fromIterable", "integer----> " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * fromIterable 集合的遍历
         * 2019-03-15 09:47:18.149 3945-3945/com.example.testlink D/fromIterable: integer----> 1
         * 2019-03-15 09:47:18.149 3945-3945/com.example.testlink D/fromIterable: integer----> 2
         * 2019-03-15 09:47:18.150 3945-3945/com.example.testlink D/fromIterable: integer----> 3
         */
    }

    public static void fromCallback() {

        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int k = 111;
                k++;
                return k;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Logs("fromCallable", "接受的数字是---> " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * fromCallable emmmmm,没什么好说的
         * 2019-03-15 09:47:18.173 3945-3945/com.example.testlink D/fromCallable: 接受的数字是---> 1111
         * 2019-03-15 10:36:40.637 4439-4439/com.example.testlink D/fromCallable: 接受的数字是---> 112
         *
         */
    }

    public static void fromFuture() {
        Observable.fromFuture(new Future<Integer>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                Logs("fromFuture", "" + mayInterruptIfRunning);
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Integer get() throws ExecutionException, InterruptedException {
                return 2;
            }

            @Override
            public Integer get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return 1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Logs("fromFuture", "接收的数据---->  " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * 2019-03-15 11:46:23.995 6175-6175/com.example.testlink D/fromFuture: 接收的数据---->  2
         */

        Observable.fromFuture(Observable.just(1).toFuture(), 2000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logs("fromFuture", "接收的数据---->  " + integer);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        /**
         * fromFuture 用法差别也不大，接收Future对象，可以设置延时。
         */
    }

    public static void interval() {

        Observable.interval(3000, TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long aLong) {
                Logs("interval", "aLong ----> " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        /**
         * 从时间上可以看到每隔3秒发送了一次事件
         * 2019-03-15 14:08:10.469 7488-7506/com.example.testlink D/interval: aLong ----> 0
         * 2019-03-15 14:08:13.467 7488-7506/com.example.testlink D/interval: aLong ----> 1
         * 2019-03-15 14:08:16.468 7488-7506/com.example.testlink D/interval: aLong ----> 2
         * 2019-03-15 14:08:19.468 7488-7506/com.example.testlink D/interval: aLong ----> 3
         * 2019-03-15 14:08:22.467 7488-7506/com.example.testlink D/interval: aLong ----> 4
         * 2019-03-15 14:08:25.468 7488-7506/com.example.testlink D/interval: aLong ----> 5
         * 2019-03-15 14:08:28.468 7488-7506/com.example.testlink D/interval: aLong ----> 6
         */

        /*Observable.interval(3, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("interval", "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d("interval", "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("interval", "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("interval", "对Complete事件作出响应");
                    }
                });*/


        /**
         * interval 延迟时间定时发送无限个事件
         */
    }

    public static void intervalRage() {
        Observable.intervalRange(10, 5, 1000, 1000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logs("intervalRage", "接收的数据------>  " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void just() {
        Observable.just("s", "s", "s", "s").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Logs("just", "接收的数据--->  " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * 2019-03-15 14:25:16.729 7800-7800/com.example.testlink D/just: 接收的数据--->  s
         * 2019-03-15 14:25:16.729 7800-7800/com.example.testlink D/just: 接收的数据--->  s
         * 2019-03-15 14:25:16.730 7800-7800/com.example.testlink D/just: 接收的数据--->  s
         * 2019-03-15 14:25:16.730 7800-7800/com.example.testlink D/just: 接收的数据--->  s
         */

    }

    public static void range() {
        Observable.range(1, 10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.rangeLong(1, 12).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public static void repeat() {

        Observable.range(1, 5).repeat().subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void timer() {
        outTime();
        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Logs("timer","接收的数据---->  "+aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        /**
         * timer 延迟时间后发送一个事件
         * 2019-03-15 14:48:17.556 8255-8255/com.example.testlink D/timer: ----------------------时间分割线----------------------
         * 2019-03-15 14:48:20.569 8255-8274/com.example.testlink D/timer: 接收的数据---->  0
         */
    }

    public static void Logs(String tag, String o) {
        Log.d(tag, o);
    }

    public static void outTime() {
        Log.d("timer", "----------------------时间分割线----------------------");
    }
}

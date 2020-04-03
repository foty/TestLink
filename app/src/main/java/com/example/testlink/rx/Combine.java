package com.example.testlink.rx;

import android.util.Log;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by lxx on 2019/4/22.
 * Use by
 */

public class Combine {

    public static void combineLatest() {
        Observable<Long> o1 = Observable.interval(1, TimeUnit.MILLISECONDS).take(3);
        Observable<String> o2 = Observable.just("qqq", "www", "eee", "rrr"
                , "ttt", "yyy", "uuu", "iii", "ooo", "ppp");

        Observable.combineLatest(o1, o2, new BiFunction<Long, String, String>() {
            @Override
            public String apply(Long s, String s2) throws Exception {
                return s + s2 + "--";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("combineLatest", "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.d("combineLatest", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                Log.d("combineLatest", "onComplete: ");
            }
        });
    }

    public static void join() {
        Observable<String> o1 = Observable.just("111", "222", "333");
        Observable.just("aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh"
                , "jjj", "kkk")
                .join(o1, new Function<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> apply(String s) throws Exception {
                        Log.d("join", "apply1: " + s);
                        return Observable.timer(1, TimeUnit.MILLISECONDS);
                    }
                }, new Function<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> apply(String s) throws Exception {
                        Log.d("join", "apply2: " + s);
                        return Observable.timer(1, TimeUnit.MILLISECONDS);
                    }
                }, new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + "_" + s2;
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Log.d("join", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public static void concat() {
        Observable<String> o1 = Observable.just("111", "222", "333");
        Observable<String> o2 = Observable.just("aaa", "bbb", "ccc");
        Observable<Long> o3 = Observable.interval(1000, TimeUnit.MILLISECONDS).take(3);
        Observable<String> o4 = Observable.just("x", "xx", "xxx");
        Observable.concat(o1, o2, o3, o4).subscribe(new Consumer<Serializable>() {
            @Override
            public void accept(Serializable serializable) throws Exception {
                Log.d("concat", "accept: " + serializable.toString());
            }
        });
    }

    public static void merge() {
        Observable<String> o1 = Observable.just("111", "222", "333", "444");
        Observable<String> o2 = Observable.just("aaa", "bbb", "ccc", "ddd");
        Observable.merge(o1, o2).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("merge", "accept: " + s);
            }
        });

    }

    public static void zip() {
        Observable<String> o1 = Observable.just("111", "222", "333");
//        Observable<String> o2 = Observable.just("aaa");
        Observable<Long> o3 = Observable.interval(1000, TimeUnit.MILLISECONDS).take(2);
        Observable.zip(o1, o3, new BiFunction<String, Long, Integer>() {
            @Override
            public Integer apply(String s, Long s2) throws Exception {
                Log.d("zip", "apply: s= " + s + " s2= " + s2);
                return (s + s2).length();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("zip", "accept: " + integer);
            }
        });
    }

    public static void startWith() {
        Observable.just("111")
                .startWith("qqqq")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("startWith", "accept: " + s);
                    }
                });
        /**
         * D/startWith: accept: qqqq
         * D/startWith: accept: 111
         */
    }

    public static void switchOnNext() {
        Observable<String> o1 = Observable.just("111", "222", "333");
        Observable<String> o2 = Observable.just("aaa");
        Observable.switchOnNext(
                Observable.interval(2000, TimeUnit.MILLISECONDS)
                        .map(new Function<Long, Observable<Long>>() {
                            @Override
                            public Observable<Long> apply(Long aLong) throws Exception {
                                Log.d("switchOnNext", "apply1: " + aLong);
                                return Observable.interval(1000, TimeUnit.MILLISECONDS)
                                        .map(new Function<Long, Long>() {
                                            @Override
                                            public Long apply(Long aLong) throws Exception {
                                                Log.d("switchOnNext", "apply2: " + aLong);
                                                return aLong;
                                            }
                                        });
                            }
                        }).take(4))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("switchOnNext", "accept: " + aLong);
                    }
                });
    }
}

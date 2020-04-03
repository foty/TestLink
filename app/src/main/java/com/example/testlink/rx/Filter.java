package com.example.testlink.rx;


import android.util.Log;

import com.example.testlink.brleee.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxx on 2019/4/9.
 * Use by 过滤操作符
 */

public class Filter {

    public static void debounce() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("debounce", "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("debounce", "onNext: 接收的数据--> " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("debounce", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("debounce", "onComplete");
                    }
                });
        /**
         * 04-09 14:20:24.866 14445-14445/com.example.testlink D/debounce: onSubscribe
         * 04-09 14:20:24.866 14445-14445/com.example.testlink D/debounce: onNext: 接收的数据--> 7
         * 04-09 14:20:24.866 14445-14445/com.example.testlink D/debounce: onComplete
         */
    }

    public static void distinct() {

        User user1 = new User("15");
        User user2 = new User("16");
        User user3 = new User("17");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user1);
        list.add(user2);
        list.add(user2);
        list.add(user3);
        list.add(user3);
        list.add(user3);

        Observable.fromIterable(list)
                .distinct()
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("distinct", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(User integer) {
                        Log.d("distinct", "onNext: " + integer.age);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("distinct", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("distinct", "onComplete: ");
                    }
                });
        /**
         *04-09 17:16:31.456 25133-25133/com.example.testlink D/distinct: onSubscribe:
         04-09 17:16:31.456 25133-25133/com.example.testlink D/distinct: onNext: 15
         04-09 17:16:31.456 25133-25133/com.example.testlink D/distinct: onNext: 16
         04-09 17:16:31.456 25133-25133/com.example.testlink D/distinct: onNext: 17
         04-09 17:16:31.456 25133-25133/com.example.testlink D/distinct: onComplete:
         */
    }

    public static void elementAt() {
        Observable.just(1, 2, 3, 4, 5, 6)
//                .elementAt(10,500)
//                .subscribe(new SingleObserver<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Integer integer) {
//                        Log.d("elementAt", "onSuccess:  " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
                .elementAt(10)
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("elementAt", "onSubscribe: ");

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.d("elementAt", "onSuccess:  " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("elementAt", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("elementAt", "onComplete: ");
                    }
                });
        /**
         *04-09 17:57:13.416 23283-23283/? D/elementAt: onSubscribe:
         04-09 17:57:13.416 23283-23283/? D/elementAt: onSuccess:  4
         */
    }

    public static void filter() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .filter(integer -> integer > 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("filter", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("filter", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("filter", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("filter", "onComplete: ");
                    }
                });
        /**
         * 04-09 14:22:53.576 16809-16809/com.example.testlink D/filter: onSubscribe:
         04-09 14:22:53.576 16809-16809/com.example.testlink D/filter: onNext: 4
         04-09 14:22:53.576 16809-16809/com.example.testlink D/filter: onNext: 5
         04-09 14:22:53.576 16809-16809/com.example.testlink D/filter: onNext: 6
         04-09 14:22:53.576 16809-16809/com.example.testlink D/filter: onNext: 7
         04-09 14:22:53.576 16809-16809/com.example.testlink D/filter: onComplete:
         */
    }

    public static void first() {
        Observable.just("1", "qwer", "qqq", "rqwaer")
//                .first("qwer")
                .first("456")
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("first", "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.d("first", "onSuccess: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("first", "onError: ");
                    }
                });
        /**
         * 04-09 14:22:53.576 16809-16809/com.example.testlink D/first: onSubscribe:
         04-09 14:22:53.576 16809-16809/com.example.testlink D/first: onSuccess: 1
         */

    }

    public static void ignoreElements() {
        Observable.just(1, 2, 13)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("ignoreElements", "onSubscribe: ");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("ignoreElements", "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ignoreElements", "onError: ");
                    }
                });
    }

    public static void last() {
        Observable.just(1, 2, 3, 4)
                .last(0)
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("last", "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.d("last", "onSuccess: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("last", "onError: ");
                    }
                });
    }

    public static void sample() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Thread.sleep(1000);
                e.onNext(2);
                Thread.sleep(1000);
                e.onNext(3);
                Thread.sleep(1000);
                e.onNext(4);
                Thread.sleep(1000);
                e.onNext(5);
                Thread.sleep(1000);
                e.onNext(6);
            }
        })
                .sample(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("sample", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("sample", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sample", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("sample", "onComplete: ");
                    }
                });
    }

    public static void skip() {
        Observable.just(1, 2, 3, 4, 5)
//                .skip(3)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("skip", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("skip", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("skip", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("skip", "onComplete: ");
                    }
                });
    }

    public static void skipLast() {
        Observable.just(1, 2, 3, 4, 5)
                .skipLast(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("skipLast", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("skipLast", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("skipLast", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("skipLast", "onComplete: ");
                    }
                });
    }

    public static void take() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
//                .take(3)
                .takeLast(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("take", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("take", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("integer", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("integer", "onComplete: ");
                    }
                });
    }

}

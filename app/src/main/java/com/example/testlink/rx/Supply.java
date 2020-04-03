package com.example.testlink.rx;


import android.util.Log;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

/**
 * Created by lxx on 2019/4/26.
 * Use by
 */

public class Supply {

    /**
     * do系列
     *
     * doOnEach
     * doOnNext
     * doOnComplete
     * doOnError
     * doOnTerminate
     * doAfterTerminate
     * doAfterNex
     * doFinally
     * doOnLifecycle
     * doOnDispose
     * doOnSubscribe
     */

    /**
     * 在下游onNext之后注册一个动作。
     */
    public static void doAfterNext() {
        Observable.just("111", "222")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("doAfterNext", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("doAfterNext", "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("doAfterNext", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("doAfterNext", "onComplete: ");
                    }
                });
    }

    /**
     * 注册一个结束动作。
     */

    public static void doAfterTerminate() {
        Observable.just(1, 2, 3, 4)
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doAfterTerminate", "run: -------------");
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doAfterTerminate", "run: -----2222222--------");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doAfterTerminate", "run: -----333333--------");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("doAfterTerminate", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("doAfterTerminate", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("doAfterTerminate", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("doAfterTerminate", "onComplete: ");
                    }
                });
    }

    /**
     * 几乎与doAfterTerminate一样，但是doFinally会在下游取消订阅时注册一个事件。
     */
    public static void doFinally() {
        Observable.just("111", "222")
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doFinally", "run: ------------");
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
                Log.d("doFinally", "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.d("doFinally", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("doFinally", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d("doFinally", "onComplete: ");
            }
        });
    }

    public static void doOnSubscribe() {

        Book book1 = new Book(1, 1);
        Book book2 = new Book(2, 2);
        Book book3 = new Book(3, 3);

        Observable.just(book1, book2, book3)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("doOnSubscribe", "doOnSubscribe: ------");
                    }
                })
                .doOnNext(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) throws Exception {
                        if (book.id == 2) {
                            book.id = 10086;
                        } else if (book.id == 3) {
                            book.id = 10087;
                        }
                        Log.d("doOnSubscribe", "doOnNext: ----");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doOnSubscribe", "doOnComplete: -----");
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doOnSubscribe", "doOnTerminate: -----");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doOnSubscribe", "doAfterTerminate: -----");
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("doOnSubscribe", "doOnError: -----");
                    }
                })
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("doOnSubscribe", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Book integer) {
                        Log.d("doOnSubscribe", "onNext: " + integer.id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("doOnSubscribe", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("doOnSubscribe", "onComplete: ");
                    }
                });
    }

    public static void doOnDispose() {
        Observable.just("11")
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doOnDispose", "run: ----------");
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("doOnDispose", "onSubscribe: ");
                d.dispose();
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void doOnEach() {
        Observable.just("11", "22", "33")
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(Notification<String> stringNotification) throws Exception {
                        String value = stringNotification.getValue();
                        value = value + "___";
                        Log.d("doOnEach", "accept: " + value);

                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("doOnEach", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("doOnEach", "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("doOnEach", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("doOnEach", "onComplete: ");
                    }
                });
    }

    public static void doOnLifecycle() {
        Observable.just("111", "222")
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("doOnLifecycle", "accept: !!!!!!!!!");
                        disposable.dispose();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("doOnLifecycle", "run: ——————————————");
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("doOnLifecycle", "onSubscribe:" + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.d("doOnLifecycle", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("doOnLifecycle", "onError:");
            }

            @Override
            public void onComplete() {
                Log.d("doOnLifecycle", "onComplete: ");
            }
        });
    }

    public static void delay() {
        Observable.just(1, 2, 3, 4)
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("delay", "accept: " + integer);
                    }
                });
    }

    public static void materialize() {
        Observable.just(1, 2, 3)
                .materialize()
                .subscribe(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Integer value = integerNotification.getValue();
                        Log.d("materialize", "accept: " + value);
                    }
                });
    }

    public static void timeInterval() {
        Observable.just("11", "22", "33", "44")
                .timeInterval(TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Timed<String>>() {
                    @Override
                    public void accept(Timed<String> stringTimed) throws Exception {
                        String value = stringTimed.value();
                        long time = stringTimed.time();
//                        TimeUnit unit = stringTimed.unit();
                        Log.d("timeInterval", "accept: " + value + "  " + time);
                    }
                });

    }

    public static void timeout() {
        Observable.just("111")
                .delay(5000, TimeUnit.MILLISECONDS)
                .timeout(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("timeout", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("timeout", "onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("timeout", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("timeout", "onComplete: ");
                    }
                });
        /**
         * 04-26 16:58:18.543 ******** D/timeout: onSubscribe:
         04-26 16:58:20.553 ******** D/timeout: onError:
         */
    }

    /**
     * to
     * toFuture
     * toList
     * toMap
     * toMultimap
     * toSortedList
     */
    public static void toddd() {
        Single<List<String>> listSingle = Observable.just("111").toList();
        Future<String> stringFuture = Observable.just("111").toFuture();
        Single<Map<Integer, Collection<String>>> mapSingle = Observable.just("111").toMultimap(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {

                return 111;
            }
        });

        Single<Map<String, Integer>> mapSingle1 = Observable.just(1).toMap(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "";
            }
        });

        Single<List<Integer>> listSingle1 = Observable.just(1, 2, 5, 96).toSortedList();

    }

    public static int sort(int[] nums) {
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[p] != nums[i]) {
                p++;
            }
        }
        return p + 1;
    }

    public static class Book {
        int id;
        int date;

        Book(int id, int date) {
            this.id = id;
            this.date = date;
        }
    }
}

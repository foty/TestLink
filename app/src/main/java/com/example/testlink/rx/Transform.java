package com.example.testlink.rx;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * @author lxx
 * @date 2019/3/14
 * use by 转换操作符
 */
public class Transform {
    /**
     * map 转换对象 发送过来的String ，最终接收的是 integer
     */
    public void map() {
        Observable.just("100").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                Log.d("map", "apply: " + s);
                return 10086;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("map", "onNext:接收的数据--->  " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * 03-27 15:36:07.138 15066-15066/com.example.testlink D/apply: apply: 100
         * 03-27 15:36:07.138 15066-15066/com.example.testlink D/map: onNext:接收的数据--->  10086
         */

    }

    public void flatMap() {

        List<String> list = new ArrayList<>();
        list.add("资源1");
        list.add("资源2");
        list.add("资源3");
        List<String> list2 = new ArrayList<>();
        list2.add("资源a");
        list2.add("资源b");
        list2.add("资源c");
        list2.add("资源d");
        user user1 = new user("xx", "18", list);
        user user2 = new user("yy", "20", list2);

        Observable.just(user2).flatMap(new Function<user, ObservableSource<user>>() {
            @Override
            public ObservableSource<user> apply(user s) throws Exception {
                Log.d("flatMap", "apply: " + s.age);

                return Observable.just(new user(s.age));
            }
        }).subscribe(new Observer<user>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(user o) {
                Log.d("flatMap", "onNext:接收的数据---> " + o.name);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * 03-27 15:58:52.458 15434-15434/? D/flatMap: apply: 20
         * 03-27 15:58:52.458 15434-15434/? D/flatMap: onNext:接收的数据---> pp
         */
    }

    public static void buffer() {
       /* Observable.just("111", "222", "333", "444", "555")
                .buffer(3)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        Log.d("buffer", "onNext: 接收的数据--->" + strings.size() + "  " + strings);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

        int[] is = new int[]{1, 2, 3, 4, 5, 6, 7};
        Observable.fromArray(is).buffer(3)
                .subscribe(new Observer<List<int[]>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<int[]> ints) {
                        Log.d("buffer", "onNext: 接收的数据--->" + ints.size() + "  " + ints);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        /**
         * 03-27 14:24:30.048 14508-14508/com.example.testlink D/buffer: onNext: 接收的数据--->3  [111, 222, 333]
         * 03-27 14:24:30.048 14508-14508/com.example.testlink D/buffer: onNext: 接收的数据--->2  [444, 555]
         * <p>
         * 03-27 14:33:14.358 14596-14596/com.example.testlink D/buffer: onNext: 接收的数据--->1  [[I@41b4c990]
         */
    }

    public static void groupBy() {
        Observable.just(0, 1, 2, 3, 4, 5, 6)
                .groupBy(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                }).subscribe(new Observer<GroupedObservable<Boolean, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(GroupedObservable<Boolean, Integer> object) {
//                Log.d("groupBy", "accept1:接收的数据类型---> " + object.getKey());
                object.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("groupBy", "accept2:接收的数据类型---> " + object.getKey() + " --> " + integer);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
        /**
         * 03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> true --> 0
         03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> false --> 1
         03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> true --> 2
         03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> false --> 3
         03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> true --> 4
         03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> false --> 5
         03-27 16:27:08.778 15785-15785/com.example.testlink D/groupBy: accept2:接收的数据类型---> true --> 6
         */
    }

    public static void scan() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.d("scan", "integer: " + integer + "  integer2= " + integer2);
                        return integer + integer2;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("scan", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * /com.example.testlink D/scan: onNext: 1
         /com.example.testlink D/scan: integer: 1  integer2= 2
         /com.example.testlink D/scan: onNext: 3
         /com.example.testlink D/scan: integer: 3  integer2= 3
         /com.example.testlink D/scan: onNext: 6
         /com.example.testlink D/scan: integer: 6  integer2= 4
         /com.example.testlink D/scan: onNext: 10
         /com.example.testlink D/scan: integer: 10  integer2= 5
         /com.example.testlink D/scan: onNext: 15
         */
    }

    public static void window() {

        List list;

        int[][] sa = {{12}, {2, 3}};


        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .window(5)
                .subscribe(new Observer<Observable<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Observable<Integer> ob) {
                        ob.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.d("window", "accept: 接受的数据----> " + integer);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        /**
         * 03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 1
         03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 2
         03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 3
         03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 4
         03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 5
         03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 6
         03-27 17:42:07.418 16433-16433/com.example.testlink D/window: accept: 接受的数据----> 7
         */
    }


    public class user {
        public String name;
        public String age;
        public List<String> nets;

        public user(String name, String age, List<String> nets) {
            this.age = age;
            this.name = name;
            this.nets = nets;
        }

        public user(String age) {
            this.age = age;
            this.name = "pp";
            this.nets = null;
        }
    }
}

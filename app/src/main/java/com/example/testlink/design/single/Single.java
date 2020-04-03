package com.example.testlink.design.single;

/**
 * Created by lxx on 2019/5/15.
 * Use by
 * 单例模式
 */

public class Single {
    private Single() {
    }
//    private static Single instance = new Single();

    /**
     * 饿汉式进化版
     *
     * @return
     */
    public static Single getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static Single instance = new Single();
    }


//    /**
//     * 懒汉式
//     *
//     * @return
//     */
//    public static Single getInstance() {
//        if (instance == null) {
//            synchronized (Single.class) {
//                if (instance == null) {
//                    instance = new Single();
//                }
//            }
//        }
//        return instance;
//    }

}

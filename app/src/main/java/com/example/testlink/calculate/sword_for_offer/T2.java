package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/4/9 17:51
 * Use by 单例设计函数
 */
public class T2 {

    private T2() {
    }

    /**
     * 首推静态内部类 跟双重锁校验法。
     */

    //静态内部类
    private static class SingletonHolder {
        private static T2 ourInstance = new T2();
    }

    public static T2 getInstance() {
        return SingletonHolder.ourInstance;
    }

    private static T2 instance;


    //双重锁检验法
    public static T2 getInstance1() {
      if (instance == null){
          synchronized (T2.class){
              if (instance == null){
                  instance =  new T2();
              }
          }
      }
      return instance;
    }

}

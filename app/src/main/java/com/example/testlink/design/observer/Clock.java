package com.example.testlink.design.observer;


/**
 * Create by lxx
 * Date : 2020/7/23 16:24
 * Use by
 */
public class Clock implements Observer {

    @Override
    public void update() {
       System.out.println("咚~~~");
    }
}

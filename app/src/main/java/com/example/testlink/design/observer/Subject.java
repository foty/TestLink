package com.example.testlink.design.observer;


/**
 * Create by lxx
 * Date : 2020/7/23 16:05
 * Use by
 */
public interface Subject {
    void addObserver(Observer ob);

    void removeObserver(Observer ob);

     void notifyObserver();
}

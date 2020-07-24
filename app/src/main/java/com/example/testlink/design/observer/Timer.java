package com.example.testlink.design.observer;

/**
 * Create by lxx
 * Date : 2020/7/23 16:23
 * Use by
 */
public class Timer {

    private TimeSubject subject;

    public Timer(){
        subject = new TimeSubject();
    }

    public void setObserver(Observer observer) {
        subject.addObserver(observer);
    }

    public void ring() {
        subject.notifyObserver();
    }
}

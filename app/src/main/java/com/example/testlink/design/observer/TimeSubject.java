package com.example.testlink.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/7/23 16:22
 * Use by
 */
public class TimeSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer ob) {
        observers.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObserver() {
        for (Observer ob:observers) {
            ob.update();
        }
    }
}

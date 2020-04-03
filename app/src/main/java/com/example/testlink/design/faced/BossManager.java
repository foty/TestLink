package com.example.testlink.design.faced;

/**
 * Create by lxx
 * Date : 2020/1/16 16:18
 * Use by
 */
public class BossManager {

    private BusinessA a = new BusinessA();
    private BusinessB b = new BusinessB();
    private BusinessC c = new BusinessC();

    public void relax() {
        a.relax();
    }

    public void study() {
        b.study();
    }

    public void play() {
        c.play();
    }
}

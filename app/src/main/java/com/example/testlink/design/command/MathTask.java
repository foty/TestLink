package com.example.testlink.design.command;

/**
 * Create by lxx
 * Date : 2020/3/19 15:07
 * Use by
 */
public class MathTask implements Task {

    private ClassRepresentatives representatives;

    /**
     *
     */
    public MathTask() {
        representatives = new ClassRepresentatives();
    }

    @Override
    public void checkTask() {
        representatives.checkMath();
    }
}

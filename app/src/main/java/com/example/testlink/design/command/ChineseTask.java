package com.example.testlink.design.command;

/**
 * Create by lxx
 * Date : 2020/3/19 15:07
 * Use by
 */
public class ChineseTask implements Task {

    private ClassRepresentatives representatives;

    public ChineseTask() {
        representatives = new ClassRepresentatives();
    }

    @Override
    public void checkTask() {
        representatives.checkChinese();
    }
}

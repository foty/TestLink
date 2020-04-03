package com.example.testlink.design.command;

/**
 * Create by lxx
 * Date : 2020/3/19 15:08
 * Use by
 */
public class HeadTeacher {

    private Task task;

    public HeadTeacher(Task task) {
        this.task = task;
    }

    public void setTask(Task task){
        this.task = task;
    }

    public void checkTask() {
        task.checkTask();
    }
}

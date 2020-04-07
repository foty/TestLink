package com.example.testlink.design.combine;

/**
 * Create by lxx
 * Date : 2020/4/3 15:46
 * Use by
 */
public abstract class AbstractFile {

    /**
     * 公共属性- 文件名称
     */
    protected String name;

    /**
     * 公共方法- 显示内容
     */
    public abstract void showContent();

    AbstractFile(String name){
        this.name = name;
    }

}

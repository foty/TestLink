package com.example.testlink.design.combine;

/**
 * Create by lxx
 * Date : 2020/4/3 15:50
 * Use by
 */
public class TextFile extends AbstractFile {

    public TextFile(String name){
        super(name);
    }

    @Override
    public void showContent() {
        System.out.println("TextFile name= "+name);
    }
}

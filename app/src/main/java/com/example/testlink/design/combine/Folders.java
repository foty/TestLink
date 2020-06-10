package com.example.testlink.design.combine;

import java.util.ArrayList;

/**
 * Create by lxx
 * Date : 2020/4/3 15:51
 * Use by
 */
public class Folders extends AbstractFile {

    private ArrayList<AbstractFile> files;

    public Folders(String name) {
        super(name);
        files = new ArrayList<>();
    }

    public void addFile(AbstractFile file) {
        files.add(file);
    }

    public void deleteFile(AbstractFile file) {
        files.remove(file);
    }

    @Override
    public void showContent() {
        System.out.println("文件夹 name= "+name);
        for (AbstractFile file : files) {
            file.showContent();
        }
    }
}

package com.example.testlink.design.combine;

/**
 * Create by lxx
 * Date : 2020/4/7 14:59
 * Use by
 */
public class ImageFile extends AbstractFile {
    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void showContent() {
        System.out.println("ImageFile name= " + name);
        // dosomething ....
    }
}

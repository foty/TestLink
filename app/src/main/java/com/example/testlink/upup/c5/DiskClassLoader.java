package com.example.testlink.upup.c5;


import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create by lxx
 * Date : 2020/10/22 11:07
 * Use by
 *
 * .java 文件编译成 .class文件：
 * 1、找到需要编译的.java文件目录下
 * 2、打开命令窗口(左shift + 右键 --> 选择‘在此处打开命令窗口’)
 * 3、运行'javac xxx.java'，便能生成.class文件
 */
public class DiskClassLoader extends ClassLoader {

    private String filePath;

    public DiskClassLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String newPath = filePath + name + ".class";
        byte[] classByte = null;
        Path path = null;
        try {
            path = Paths.get(new URI(newPath));
            classByte = Files.readAllBytes(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name,classByte,0,classByte.length);
    }
}

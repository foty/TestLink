package com.example.testlink.upup.c5;

import java.lang.reflect.Method;

/**
 * Create by lxx
 * Date : 2020/10/22 11:23
 * Use by
 */
public class ReflectTest {
    public static void main(String[] args) {
        String s = "file://F:/lxxProject/TestLink/app/src/main/java/com/example/testlink/upup/c5/";
        DiskClassLoader diskClassLoader = new DiskClassLoader(s);
        try {
            // 加载类需要：包名 + 类名。
            Class<?> c = diskClassLoader.loadClass("com.example.testlink.upup.c5.Secret");
            if (c != null){
                Object o = c.newInstance();
                //反射调用类方法
                Method method = c.getDeclaredMethod("printSecret", null);
                method.invoke(o,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Secret secret = new Secret();
//        URL resource = secret.getClass().getResource("");
//        System.out.println(resource.getPath());
    }
}

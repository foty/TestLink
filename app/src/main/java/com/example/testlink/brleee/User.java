package com.example.testlink.brleee;

import java.util.List;

/**
 * @author lxx
 * @date 2019/3/18
 * use by
 */
public class User {
    public String name;
    public String age;
    public List<String> nets;

    public User() {
    }

    public User(String name, String age, List<String> nets) {
        this.age = age;
        this.name = name;
        this.nets = nets;
    }

    public User(String age) {
        this.age = age;
        this.name = "pp";
        this.nets = null;
    }
}

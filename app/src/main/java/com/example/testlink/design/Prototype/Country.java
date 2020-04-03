package com.example.testlink.design.Prototype;

import java.io.Serializable;

/**
 * Created by lxx on 2019/5/15.
 * Use by
 */

public class Country implements Serializable {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

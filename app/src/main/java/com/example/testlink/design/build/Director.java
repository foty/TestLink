package com.example.testlink.design.build;

/**
 * Created by lxx on 2019/10/26.
 * Use by
 */

public class Director {
    private Builder builder;

    public Director() {
        builder = new BuilderImpl();
    }

    public Product buildA() {
        builder.setProductA();
        builder.setProductD();
        builder.setProductC();
        // ...
        return builder.getProduct();
    }
}

package com.example.testlink.design.build;

/**
 * Created by lxx on 2019/10/26.
 * Use by
 */

public abstract class Builder {

    protected Product product;

    abstract void setProductA();

    abstract void setProductB();

    abstract void setProductC();

    abstract void setProductD();

    abstract void setProductE();

    public Product getProduct() {
        return product;
    }
}

package com.example.testlink.design.build;

/**
 * Created by lxx on 2019/10/26.
 * Use by
 */

public class BuilderImpl extends Builder {
    @Override
    void setProductA() {
        product.setA("a");
    }

    @Override
    void setProductB() {
        product.setA("b");
    }

    @Override
    void setProductC() {
        product.setA("c");
    }

    @Override
    void setProductD() {
        product.setA("d");
    }

    @Override
    void setProductE() {
        product.setA("e");
    }
}

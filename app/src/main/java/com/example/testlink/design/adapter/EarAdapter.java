package com.example.testlink.design.adapter;

/**
 * Create by lxx
 * Date : 2020/6/11 10:50
 * Use by
 */
public class EarAdapter implements MiMaxMold {

    /**
     * 适配者实例
     */
    private Mix8Mold mi8;

    public EarAdapter(Mix8Mold mold) {
        mi8 = mold;
    }

    @Override
    public void chargeAndEar() {
        mi8.charge();
    }
}

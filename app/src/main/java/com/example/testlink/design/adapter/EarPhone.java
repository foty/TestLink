package com.example.testlink.design.adapter;

/**
 * Create by lxx
 * Date : 2020/6/11 10:50
 * Use by
 */
public class EarPhone {

    private MiMaxMold max;

    /**
     * 这个耳机是max的原装耳机，默认是max手机。
     */
    public EarPhone (){
        max = new MiMax();
    }

    /**
     * 耳机听歌或充电
     */
    public void listen() {
        max.chargeAndEar();
    }


    /**
     * 使用转接头
     * @param mold
     */
    public void setAdapter(MiMaxMold mold){
        max = mold;
    }
}

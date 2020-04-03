package com.example.testlink.design.memo;

/**
 * Created by lxx on 2019/11/14.
 * Use by
 */

public class Memo {
    /**
     * 备忘录，备忘的地方
     */

    private int statue;

    public Memo(int statue) {
        this.statue = statue;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }
}

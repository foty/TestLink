package com.example.testlink.design.memo;

/**
 * Created by lxx on 2019/11/14.
 * Use by
 */

public class Statue {
    /**
     * 需要备忘的角色，创建备忘录，恢复备忘录
     */
    private int statue;

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    /**
     * 保存，创建备忘录
     */
    public Memo save2Memo() {
        return new Memo(statue);
    }

    /**
     * 恢复还原上次保存
     *
     * @param m
     */
    public void restoreStatue(Memo m) {
        statue = m.getStatue();
    }
}

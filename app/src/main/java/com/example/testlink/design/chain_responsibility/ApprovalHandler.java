package com.example.testlink.design.chain_responsibility;

import android.util.Log;

/**
 * Create by lxx
 * Date : 2021/4/16 15:08
 * Use by
 */
public abstract class ApprovalHandler {

    private ApprovalHandler handler;

    public abstract void approval(int day);

    public void setHandler(ApprovalHandler handler) {
        this.handler = handler;
    }

    protected void next(int day) {
        if (handler == null) {
            Log.d("TAG", "审批结束");
        } else {
            handler.approval(day);
        }
    }
}

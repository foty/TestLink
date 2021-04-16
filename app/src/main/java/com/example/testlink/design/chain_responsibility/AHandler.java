package com.example.testlink.design.chain_responsibility;

import android.util.Log;

/**
 * Create by lxx
 * Date : 2021/4/16 15:13
 * Use by
 */
public class AHandler extends ApprovalHandler {

    @Override
    public void approval(int day) {
        System.out.println("组长：同意");
        if (day < 3) {
            System.out.println("审批结束，同意请假" + day + "天");
        } else {
            next(day);
        }
    }
}

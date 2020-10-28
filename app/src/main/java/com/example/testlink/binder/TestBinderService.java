package com.example.testlink.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Create by lxx
 * Date : 2020/10/28 11:30
 * Use by 假设是服务端。
 */
public class TestBinderService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sub;
    }

    private ITestAidlBean.Stub sub = new ITestAidlBean.Stub() {
        @Override
        public void deCode(TestAidlBean bean) throws RemoteException {
            Log.d("lxx", "Pid " + android.os.Process.myPid()
                    + " Tid: " + android.os.Process.myTid());

            Log.d("lxx", "服务端: 服务正在运行中。。。。");
        }
    };
}

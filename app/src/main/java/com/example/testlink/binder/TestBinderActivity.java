package com.example.testlink.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testlink.R;

/**
 * Create by lxx
 * Date : 2020/10/28 11:41
 * Use by 客户端测试类
 */
public class TestBinderActivity extends Activity {

    private ITestAidlBean iTestAidlBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_test);

        Intent intent = new Intent(this,TestBinderService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        Log.d("lxx", "TestBinderActivity  Pid " + android.os.Process.myPid()
                + " Tid: " + android.os.Process.myTid());

        TextView textView = findViewById(R.id.tvTest);
        textView.setOnClickListener(v -> {
            if (iTestAidlBean == null) {
                Toast.makeText(this, "未连接", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    iTestAidlBean.deCode(new TestAidlBean("哈哈哈", 100));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iTestAidlBean = ITestAidlBean.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            unbindService(serviceConnection);
        }
    };
}

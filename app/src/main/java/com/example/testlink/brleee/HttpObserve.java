package com.example.testlink.brleee;

import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author lxx
 * @date 2019/3/18
 * use by
 */
abstract class HttpObserve<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        Log.e("HttpObserve","onComplete");
    }

    @Override
    public void onError(Throwable e) {
        Log.e("HttpObserve","onError");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        Log.e("HttpObserve","onComplete");
    }
}

package com.example.testlink.brleee;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lxx
 * @date 2019/3/15
 * use by 最顶层调用。
 */
public class Http {
    /**
     * 基础准备,添加依赖。
     * <p>
     * 创建接口类；
     * <p>
     * 创建OKhttp实例
     * <p>
     * 创建retrofit实例；
     * <p>
     * 创建调用器，将Java接口修改为HTTP调用；
     * <p>
     * 自定义Converter解析器；
     * <p>
     * 封装异步流；
     * <p>
     * 封装回调观察者，统一处理错误信息。
     */

    public void getPost() {
        HttpClientBuilder.getRetrofitInstance()
                .create(Api.class)
                .getUser2("10086")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserve<User>() {
                    @Override
                    public void onNext(User user) {

                    }
                });
    }

}

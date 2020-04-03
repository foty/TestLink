package com.example.testlink.brleee;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lxx
 * @date 2019/3/15
 * use by  Retrofit构建器
 */
public class RetrofitCreator {

    protected Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("自己的url")
                .client(HttpClientBuilder.getOkHttpInstance())
                // 支持RxJava平台
                //compile 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}

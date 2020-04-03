package com.example.testlink.brleee;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author lxx
 * @date 2019/3/15
 * use by Okhttp 创建器
 */
public class OkHttpCreator {

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3000, TimeUnit.SECONDS);//连接超时
        builder.writeTimeout(3000, TimeUnit.SECONDS);//写入超时
        builder.readTimeout(3000, TimeUnit.SECONDS);//读取超时
        builder.retryOnConnectionFailure(true);//错误重连

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        //请求头,cookie拦截器
        Interceptor header = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("token", "token");
                builder.addHeader("Cookie", "cookie");
                builder.addHeader("其他属性", "其他值");
                return chain.proceed(builder.build());
            }
        };
        builder.addInterceptor(header);
        //缓存拦截
        OkHttpClient client = builder.build();

        return builder.build();
    }
}

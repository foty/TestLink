package com.example.testlink.brleee;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author lxx
 * @date 2019/3/18
 * use by
 */
public class HttpClientBuilder {

    public static Retrofit retrofit;
    public static OkHttpClient client;

    private HttpClientBuilder() {
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            synchronized (HttpClientBuilder.class) {
                if (retrofit == null) {
                    retrofit = new RetrofitCreator().getRetrofit();
                }
            }
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttpInstance() {
        if (client == null) {
            synchronized (HttpClientBuilder.class) {
                if (client == null) {
                    client = new OkHttpCreator().getOkHttpClient();
                }
            }
        }
        return client;
    }
}

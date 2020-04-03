package com.example.testlink.brleee;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author lxx
 * @date 2019/3/15
 * use by 接口类
 */
interface Api {

    @GET("{url")
    Observable<BaseBody<User>> getPathData(@Path("mobile") String mobile);

    @POST("url")
    Observable<ResponseBody> getUser(@Field("id") String id,
                                     @Field("address") String address,
                                     @Field("mobile") String mobile);
    @POST("url")
    Observable<User> getUser2(@Field("id") String id);

    @POST("url")
    Call<ResponseBody> getUser(@Field("id") String id);
}

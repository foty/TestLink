package com.example.testlink.http;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Create by lxx
 * Date : 2019/12/11 14:36
 * Use by retrofit注解详细
 *
 * <p>
 * 1.请求方式类型注解.
 * POST
 * GET
 * PUT
 * DELETE
 * PATCH
 * HEAD
 * OPTIONS
 * HTTP(用于自定义请求，替换以上请求方式)
 * <p>
 * <p>
 * 2.标记注解.
 * FormUrlEncoded: 请求格式为“application/x-www-form-urlencoded”，使用Field或FieldMap注解参数提交数据必须加上
 * 此注解,否则会报错;
 * <p>
 * Multipart: 请求格式为“multipart/form-data”，上传文件时使用
 * <p>
 * Streaming: 下载大文件使用Streaming可以避免Retrofit将文件写入运行内存中避免OOM。
 * <p>
 * <p>
 * 3.参数注解
 * Body:  直接操作请求报文的body
 * <p>
 * use example：
 * RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "this is body");
 * <p>
 * Field
 * FiledMap:  表单上传数据,Field是上传单个数据,FieldMap是上传多个数据.并且需要使用FormUrlEncoded注解.
 * <p>
 * Header
 * HeaderMap: 添加请求头，Header是添加单个请求头,HeaderMap是添加多个请求头,请求头不会相互覆盖.要注解请求头可以使用专门的请求头注解 @Headers.
 * <p>
 * Query
 * QueryMap:   动态添加参数到url后面,Query添加单个.QueryMap添加多个.
 * QueryName:  使用很少,待研究.
 * <p>
 * use example:
 *
 * @POST("call") Observable<String> call(@Query("id") id: String, @Query("type") type: String);
 * <p>
 * 它的url就变成了 /call?id= id&type= type.类似get请求.
 * }
 * <p>
 * Path:  替换URL中相对应的部分.
 * <p>
 * use example:
 * @GET("call/{user}/getlist") Observable<String> call(@Path("user") user: String);
 * 然后我们调用它: call("vipuser");
 * 它的url就变成了 "call/vipuser/getlist".
 * <p>
 * Url: 传入url.
 * <p>
 * Part:   表示multi-part请求.使用这参数需要在接口方法使用Multipart注解.多用于上传文件时.它的参数类型有String, MultipartBody.Part, MultipartBody.
 * <p>
 * example:
 * File file = new File("......");
 * RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
 * MultipartBody.Part mbp =  MultipartBody.Part.createFormData("SDFile", file.name, rb);
 * <p>
 * PartMap:  表示multi-part请求,PartMap表示多个PartMap请求.使用这参数需要在接口方法使用Multipart注解.多用于上传文件时.参数类型可以是 RequestBody.
 */
public class RetrofitKnowledge {

    /**
     * addInterceptor()与addNetworkInterceptor()
     * FormBody与MultipartBody
     * socket
     * Http1Codec与Http2Codec(Http2Connection是什么 如果不是HTTPS为Http2Connection)
     */

    public void analyze() {
        // 获取到OkHttpClient对象
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(1000, TimeUnit.MILLISECONDS);
//        builder.retryOnConnectionFailure(true);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        //post的请求体 需要一个RequestBody对象，这是一个抽象类 具体子类有 FormBody 与 MultipartBody，其中MultipartBody使用比较多
        // RequestBody requestBody = RequestBody.create();
        MultipartBody.Builder multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加参数(普通键值对参数)
        multipartBody.addFormDataPart("", "");
        //文件、图片参数
        multipartBody.addFormDataPart("", "fileName",
                RequestBody.create(MediaType.parse("image/png"), new File("fileName")));

        //请求对象
        Request request = new Request.Builder()
                .addHeader("cookie", "10086")
                .post(multipartBody.build())
                .url("http://123456.com")
                .build();
        //异步请求
        OkHttpClient client = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        //同步请求
        try {
            Response response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

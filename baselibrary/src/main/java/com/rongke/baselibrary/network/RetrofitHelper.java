package com.rongke.baselibrary.network;

import com.rongke.baselibrary.util.SPHelper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jh352160 on 2018/2/5.
 */

public class RetrofitHelper {
    private static Retrofit retrofit;

    private static String BASE_URL;

    public static void setBaseUrl(String baseUrl){
        BASE_URL = baseUrl;
    }

    /**
     * @link com.zyf.fwms.commonlibrary.http.Api
     */
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request newRequest=chain.request().newBuilder()
                            .addHeader("Connection","close")
                            .addHeader("x-client-token", "xionglei")
                            .addHeader("cookie", "JSESSIONID="+ SPHelper.getString("token",""))
                            .build();
                    return chain.proceed(newRequest);
                })
                .retryOnConnectionFailure(false)
                .build();
    }
}

package com.young.library.net.download;


import com.young.library.net.http.RetrofitClient;
import com.young.library.net.interceptor.Transformer;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 为下载单独创建一个Retrofit实例
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/18
 */


public class DownloadRetrofit {

    private static DownloadRetrofit instance;
    private Retrofit mRetrofit;

    private static String baseUrl = "http://api.test.17zuoye.net";


    public DownloadRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static DownloadRetrofit getInstance() {

        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new DownloadRetrofit();
                }
            }

        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit
                .getInstance()
                .getRetrofit()
                .create(DownloadApi.class)
                .downloadFile(fileUrl)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }
}

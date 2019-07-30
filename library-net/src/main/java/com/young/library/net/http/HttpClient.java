package com.young.library.net.http;

import okhttp3.OkHttpClient;

/**
 * @author dupeng
 * @version 2.6.0, 2019/3/26  10:32 PM
 * @since android 17MiddleTeacher
 */
public class HttpClient {

    private static HttpClient instance;

    private OkHttpClient.Builder builder;

    public HttpClient() {
        builder = new OkHttpClient.Builder();
    }

    public static HttpClient getInstance() {

        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }

        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

}

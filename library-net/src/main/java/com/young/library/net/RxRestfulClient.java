package com.young.library.net;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.young.library.net.config.OkHttpConfig;
import com.young.library.net.cookie.CookieJarImpl;
import com.young.library.net.cookie.store.CookieStore;
import com.young.library.net.download.DownloadRetrofit;
import com.young.library.net.http.GlobalRxHttp;
import com.young.library.net.http.SingleRxHttp;
import com.young.library.net.manager.RxHttpManager;
import com.young.library.net.upload.UploadRetrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;


/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 封装后的网络请求
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/11/10
 */

public class RxRestfulClient {

    @SuppressLint("StaticFieldLeak")
    private static RxRestfulClient instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static RxRestfulClient getInstance() {
        if (instance == null) {
            synchronized (RxRestfulClient.class) {
                if (instance == null) {
                    instance = new RxRestfulClient();
                }
            }

        }
        return instance;
    }


    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public RxRestfulClient init(Application app) {
        context = app;
        return this;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxRestfulClient" +
                    ".getInstance().init(this) 初始化！");
        }
    }


    public GlobalRxHttp config() {
        checkInitialize();
        return GlobalRxHttp.getInstance();
    }


    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return GlobalRxHttp.createGApi(cls);
    }

    /**
     * 获取单个请求配置实例
     * 后续版本即将移除---推荐使用全局配置的请求
     *
     * @return SingleRxHttp
     */
    @Deprecated
    public static SingleRxHttp getSInstance() {

        return SingleRxHttp.getInstance();
    }


    /**
     * 下载文件
     *
     * @param fileUrl 地址
     * @return ResponseBody
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadRetrofit.uploadImage(uploadUrl, filePath);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths) {
        return UploadRetrofit.uploadImages(uploadUrl, filePaths);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param fileName  后台接收文件流的参数名
     * @param paramsMap 参数
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImagesWithParams(String uploadUrl, String
            fileName, Map<String, Object> paramsMap, List<String> filePaths) {
        return UploadRetrofit.uploadFilesWithParams(uploadUrl, fileName, paramsMap, filePaths);
    }

    /**
     * 获取全局的CookieJarImpl实例
     */
    private static CookieJarImpl getCookieJar() {
        return (CookieJarImpl) OkHttpConfig.getOkHttpClient().cookieJar();
    }

    /**
     * 获取全局的CookieStore实例
     */
    private static CookieStore getCookieStore() {
        return getCookieJar().getCookieStore();
    }

    /**
     * 获取所有cookie
     */
    public static List<Cookie> getAllCookie() {
        CookieStore cookieStore = getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        return allCookie;
    }

    /**
     * 获取某个url所对应的全部cookie
     */
    public static List<Cookie> getCookieByUrl(String url) {
        CookieStore cookieStore = getCookieStore();
        HttpUrl httpUrl = HttpUrl.parse(url);
        List<Cookie> cookies = cookieStore.getCookie(httpUrl);
        return cookies;
    }


    /**
     * 移除全部cookie
     */
    public static void removeAllCookie() {
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeAllCookie();
    }

    /**
     * 移除某个url下的全部cookie
     */
    public static void removeCookieByUrl(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeCookie(httpUrl);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    /**
     * 取消某个或某些请求
     */
    public static void cancel(Object... tag) {
        RxHttpManager.get().cancel(tag);
    }
}

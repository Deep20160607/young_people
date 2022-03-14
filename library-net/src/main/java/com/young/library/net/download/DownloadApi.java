package com.young.library.net.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 下载文件
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/17
 */


public interface DownloadApi {


    /**
     * 大文件官方建议用 @Streaming 注解，不然会出现IO异常导致的OOM，小文件可以忽略不注入
     *
     * @param fileUrl 地址
     * @return ResponseBody
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}

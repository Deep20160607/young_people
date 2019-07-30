package com.young.library.net.download;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 下载进度的监听
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/18
 */


public interface ProgressListener {

    /**
     * 载进度监听
     *
     * @param bytesRead     已经下载文件的大小
     * @param contentLength 文件的大小
     * @param progress      当前进度
     * @param done          是否下载完成
     * @param filePath      文件路径
     */
    void onResponseProgress(long bytesRead, long contentLength, int progress, boolean done, String filePath);


}

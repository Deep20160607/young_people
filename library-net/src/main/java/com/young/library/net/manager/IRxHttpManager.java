package com.young.library.net.manager;

import io.reactivex.disposables.Disposable;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 请求管理接口
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/11/5
 */

public interface IRxHttpManager<T> {

    /**
     * 添加
     *
     * @param tag        tag
     * @param disposable disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除请求
     *
     * @param tag tag
     */
    void remove(T tag);

    /**
     * 取消某个tag的请求
     *
     * @param tag tag
     */
    void cancel(T tag);

    /**
     * 取消某些tag的请求
     *
     * @param tags tags
     */
    void cancel(T... tags);

    /**
     * 取消所有请求
     */
    void cancelAll();
}

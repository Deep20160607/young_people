package com.young.library.net.interfaces;

import io.reactivex.disposables.Disposable;


/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 定义请求结果处理接口
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/11/2
 */

public interface ISubscriber<T> {

    /**
     * doOnSubscribe 回调
     *
     * @param d Disposable
     */
    void doOnSubscribe(Disposable d);

    /**
     * 错误回调信息
     *
     * @param errorMsg
     * @param e
     */
    void doOnError(String errorMsg, Throwable e);

    /**
     * 成功回调
     *
     * @param t 泛型
     */
    void doOnNext(T t);

    /**
     * 请求完成回调
     */
    void doOnCompleted();
}

package com.young.library.net.interfaces;

import com.young.library.net.bean.BaseResponse;

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
public interface IDataSubscriber<T> {

    /**
     * doOnSubscribe 回调
     *
     * @param d Disposable
     */
    void doOnSubscribe(Disposable d);

    /**
     * 错误回调
     *
     * @param errorMsg 错误信息
     */
    void doOnError(String errorMsg);

    /**
     * 成功回调
     *
     * @param baseData 基础泛型
     */
    void doOnNext(BaseResponse<T> baseData);

    /**
     * 请求完成回调
     */
    void doOnCompleted();
}

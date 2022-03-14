package com.young.library.net.base;

import com.young.library.net.bean.BaseResponse;
import com.young.library.net.exception.ApiException;
import com.young.library.net.interfaces.IDataSubscriber;
import com.young.library.net.manager.RxHttpManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 基类BaseObserver使用BaseData
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/23
 */
public abstract class BaseDataObserver<T> implements Observer<BaseResponse<T>>, IDataSubscriber<T> {

    /**
     * 是否隐藏toast
     *
     * @return boolean
     */
    protected boolean isHideToast() {
        return false;
    }

    /**
     * 标记网络请求的tag
     * tag下的一组或一个请求，用来处理一个页面的所以请求或者某个请求
     * 设置一个tag就行就可以取消当前页面所有请求或者某个请求了
     *
     * @return string
     */
    protected String setTag() {
        return null;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxHttpManager.get().add(setTag(), d);
        doOnSubscribe(d);
    }

    @Override
    public void onNext(BaseResponse<T> baseData) {
        doOnNext(baseData);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }


    private void setError(String errorMsg) {
        doOnError(errorMsg);
    }

}

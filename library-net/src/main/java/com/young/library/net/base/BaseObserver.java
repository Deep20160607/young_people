package com.young.library.net.base;

import com.young.library.net.exception.ApiException;
import com.young.library.net.interfaces.ISubscriber;
import com.young.library.net.manager.RxHttpManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/23
 */
public abstract class BaseObserver<T> implements Observer<T>, ISubscriber<T> {

    /**
     * 是否隐藏toast
     *
     * @return
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
    public void onSubscribe(@NonNull Disposable d) {
        RxHttpManager.get().add(setTag(), d);
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull T t) {
        doOnNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        String errorMsg = ApiException.handleException(e).getMessage();
        setError(errorMsg,e);
    }


    @Override
    public void onComplete() {
        doOnCompleted();
    }


    private void setError(String errorMsg , Throwable e) {
        doOnError(errorMsg,e);
    }

}

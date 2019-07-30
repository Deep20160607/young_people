package com.young.library.net.download;

import com.young.library.net.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;

import static com.young.library.net.utils.ToastUtils.showToast;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/17
 */


public abstract class BaseDownloadObserver implements Observer<ResponseBody> {

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void doOnError(String errorMsg);

    @Override
    public void onError(@NonNull Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    private void setError(String errorMsg) {
        showToast(errorMsg);
        doOnError(errorMsg);
    }

}

package com.young.library.net.utils;

import android.widget.Toast;

import com.young.library.net.RxRestfulClient;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 *
 * @author wuyuantao
 * @date 2018/11/9
 * @version 2.0
 *
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(RxRestfulClient.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}

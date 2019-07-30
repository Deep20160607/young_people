package com.young.library.net.interceptor;

import android.util.Log;

import com.young.library.net.utils.JsonUtils;

import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 日志打印格式化处理，refs:https://www.jianshu.com/p/e044cab4f530
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/31
 */

public class RxHttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuffer mMessage = new StringBuffer();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
            mMessage.append(" ");
            mMessage.append("\r\n");
        }
        if (message.startsWith("--> GET")) {
            mMessage.setLength(0);
            mMessage.append(" ");
            mMessage.append("\r\n");
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtils.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            Log.e("RxRestfulClient", mMessage.toString());
        }
    }
}

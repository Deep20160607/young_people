package com.young.library.net.exception;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * Copyright 2018 Shanghai one operation technology co., LTD. Beijing branch All rights reserved.
 * <p>
 * 自定义异常的类型
 *
 * @author wuyuantao
 * @version 2.0
 * @date 2018/10/18
 */
public class ApiException extends Exception {

    private final int code;
    private String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static ApiException handleException(Throwable e) {
        ApiException ae;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ae = new ApiException(httpException, httpException.code());
            ae.message = httpException.getMessage();
        } else if (e instanceof SocketTimeoutException) {
            ae = new ApiException(e, ERROR.TIMEOUT_ERROR);
            ae.message = "网络连接超时，请检查您的网络状态，稍后重试！";
        } else if (e instanceof ConnectException) {
            ae = new ApiException(e, ERROR.TIMEOUT_ERROR);
            ae.message = "网络连接异常，请检查您的网络状态，稍后重试！";
        } else if (e instanceof ConnectTimeoutException) {
            ae = new ApiException(e, ERROR.TIMEOUT_ERROR);
            ae.message = "网络连接超时，请检查您的网络状态，稍后重试！";
        } else if (e instanceof UnknownHostException) {
            ae = new ApiException(e, ERROR.TIMEOUT_ERROR);
            ae.message = "网络连接异常，请检查您的网络状态，稍后重试！";
        } else if (e instanceof NullPointerException) {
            ae = new ApiException(e, ERROR.NULL_POINTER_EXCEPTION);
            ae.message = "空指针异常";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ae = new ApiException(e, ERROR.SSL_ERROR);
            ae.message = "证书验证失败";
        } else if (e instanceof ClassCastException) {
            ae = new ApiException(e, ERROR.CAST_ERROR);
            ae.message = "类型转换错误";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            ae = new ApiException(e, ERROR.PARSE_ERROR);
            ae.message = "解析错误";
        } else if (e instanceof IllegalStateException) {
            ae = new ApiException(e, ERROR.ILLEGAL_STATE_ERROR);
            ae.message = e.getMessage();
        } else {
            ae = new ApiException(e, ERROR.UNKNOWN);
            ae.message = "未知错误";
        }
        return ae;
    }

    /**
     * 与服务端约定的异常类型
     */
    public static class ERROR {

//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_DUPLICATE_REQUEST_CODE = "300";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "400";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "410";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "420";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "500";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "501";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "800";
//
//
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "900";
//
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "999";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "901";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "902";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "903";
//        /**
//         * 先加上这个字段，app端做预处理。如果出现则跳到登录页面，重新登录。
//         */
//        public static final String RES_RESULT_NEED_RELOGIN_CODE = "910";
        /**
         * 未知错误
         */
//        public static final int UNKNOWN = "1000";
        public static final int UNKNOWN = 1000;
        /**
         * 连接超时
         */
//        public static final int TIMEOUT_ERROR = "1001";
        public static final int TIMEOUT_ERROR = 1001;
        /**
         * 空指针错误
         */
//        public static final int NULL_POINTER_EXCEPTION = "1002";
        public static final int NULL_POINTER_EXCEPTION = 1002;

        /**
         * 证书出错
         */
//        public static final int SSL_ERROR = "1003";
        public static final int SSL_ERROR = 1003;

        /**
         * 类转换错误
         */
//        public static final int CAST_ERROR = "1004";
        public static final int CAST_ERROR = 1004;

        /**
         * 解析错误
         */
//        public static final int PARSE_ERROR = "1005";
        public static final int PARSE_ERROR = 1005;

        /**
         * 非法数据异常
         */
//        public static final int ILLEGAL_STATE_ERROR = "1006";
        public static final int ILLEGAL_STATE_ERROR = 1006;


    }
}

package com.young.library.net.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author dupeng
 * @version 2.6.0, 2019/3/26  10:19 PM
 * @since android 17MiddleTeacher
 */
public class BaseResponse<T> {

    /**
     * 错误码
     */
    @SerializedName("code")
    public int code;
    /**
     * 错误描述
     */
    @SerializedName("message")
    public String msg;

    /**
     * 数据
     */
    @SerializedName("data")
    public T data;

}

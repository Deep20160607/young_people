package com.young.library.net.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by sunxiaodong on 2017/3/10.
 */

public class CustomGson {

    private static CustomGson sInstance;
    private Gson mGson;

    public static CustomGson getsInstance() {
        if (sInstance == null) {
            synchronized (CustomGson.class) {
                if (sInstance == null) {
                    sInstance = new CustomGson();
                }
            }
        }
        return sInstance;
    }

    private CustomGson() {
        mGson = new GsonBuilder()
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {//针对list类型处理
                    @Override
                    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        //处理问题：服务器端对于list为空的时候，可能会返回空{}
                        if (json.isJsonArray()) {
                            Gson newGson = new Gson();
                            return newGson.fromJson(json, typeOfT);
                        } else {
                            return Collections.EMPTY_LIST;
                        }
                    }
                }).registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

                    @Override
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                        //处理gson将Integer默认转换成Double的问题
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());
                        return new JsonPrimitive(src);
                    }
                })
                .create();
    }

    public Gson getGson() {
        if (mGson == null) {
            throw new IllegalArgumentException("mGson尚未初始化！");
        }
        return mGson;
    }

    /**
     * 将对象转换成字符串
     *
     * @param src 为null 返回""字符串
     */
    public String toJson(Object src) {
        String ret = "";
        if (src == null) return ret;
        // 该方法可能会在多线程下屌用
        // 又因为是单例模式，锁住mGson即可达到方法线程安全的效果
        synchronized (mGson) {
            ret = mGson.toJson(src);
        }
        return ret;
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        T t = null;
        try {
            t = mGson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> T fromJson(String json, Type typeOfT) {
        T t = null;
        try {
            t = mGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

}

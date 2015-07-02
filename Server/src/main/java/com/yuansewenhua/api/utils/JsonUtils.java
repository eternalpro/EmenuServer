package com.yuansewenhua.api.utils;

import com.google.gson.Gson;

/**
 * Created by gefangshuai on 2015/7/2.
 */
public class JsonUtils {

    /**
     * 从json中获取对象信息
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T getObjectFromJson(String json, Class<T> classOfT){
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }
}

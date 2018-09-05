/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.network.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

/**
 * Json工具类.
 */
public class GsonUtils {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return (T) gson.fromJson(json, typeOfT);
    }
}

/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.network.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public static Map<String, String> jsonToMap(String json) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                map.put(key, jsonObject.getString(key));
            }
        } catch (JSONException e) {
            Logger.E(e.getMessage());
        }
        return map;
    }
}

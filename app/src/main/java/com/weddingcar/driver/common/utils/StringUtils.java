package com.weddingcar.driver.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * String handling util
 */
public class StringUtils {
    public final static String UTF_8 = "utf-8";

    /**
     * To determine whether a string is empty(null, "", "null"), if is empty return true
     */
    public static boolean isEmpty(String value) {
        return value == null || "".equalsIgnoreCase(value.trim()) || "null".equalsIgnoreCase(value.trim());
    }

    /**
     * To determine whether a object is empty("null", "", null), if is empty return true
     */
    public static boolean isNull(Object obj) {
        return "null".equals(obj) || "".equals(obj) || null == obj;
    }

    /**
     * Check the string, make it not empty
     * @param str
     * @return result   After checking string
     */
    public static String checkString(String str) {
        return isEmpty(str) ? "" : str;
    }

    /**
     * 只要有一个为空, 就返回true
     *
     * @param args
     * @return
     */
    /**
     * Check if there have empty string
     * @param args
     * @return
     */
    public static boolean isEmpty(String... args) {
        for (String str : args) {
            if (StringUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if is equal for multiple strings
     */
    public static boolean equals(String... args) {
        String last = null;
        for (String str : args) {
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equals(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * Json string to object
     * @param jsonStr
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String jsonStr, Class<T> classOfT) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonStr);
        Gson gson = new Gson();
        return gson.fromJson(jsonElement, classOfT);
    }
}

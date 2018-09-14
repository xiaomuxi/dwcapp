package com.weddingcar.driver.common.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.weddingcar.driver.common.bean.UserInfo;

public class SPController {

    public static final String IS_APP_FIRST_INSTALL = "IS_APP_FIRST_INSTALL";
    public static final String IS_USER_AUTO_LOGIN = "IS_USER_AUTO_LOGIN";
    public static final String USER_LAST_FILLED_PHONE = "USER_LAST_FILLED_PHONE";

    public static final String ALI_PUSH_DEVICE_ID = "ALI_PUSH_DEVICE_ID";

    public static final String USER_INFO_USER_ID = "USER_INFO_USER_ID";
    public static final String USER_INFO_SEX = "USER_INFO_SEX";
    public static final String USER_INFO_NAME = "USER_INFO_NAME";


    private static SPController instance;

    private SharedPreferences sp;

    private SharedPreferences.Editor editor;

    private boolean isInitialed = false;

    private SPController() {
    }

    public static SPController getInstance() {
        if (instance == null) {
            instance = new SPController();
        }
        return instance;
    }

    public SPController init(Context c) {
        sp = c.getSharedPreferences(c.getPackageName(), 0);
        editor = sp.edit();
        isInitialed = true;
        return null;
    }

    public UserInfo getUserInfo() {
        UserInfo info = new UserInfo();
        info.setUserId(getInstance().getString(USER_INFO_USER_ID, ""));
        info.setName(getInstance().getString(USER_INFO_NAME, ""));
        info.setSex(getInstance().getString(USER_INFO_SEX, ""));

        return info;
    }

    public void saveUserInfo(UserInfo info) {
        if (info == null) {
            return;
        }

        getInstance().putString(USER_INFO_USER_ID, info.getUserId());
        getInstance().putString(USER_INFO_NAME, info.getName());
        getInstance().putString(USER_INFO_SEX, info.getSex());
    }

    public void cleanUserInfo() {
        getInstance().putString(USER_INFO_USER_ID, "");
        getInstance().putString(USER_INFO_NAME, "");
        getInstance().putString(USER_INFO_SEX, "");
    }

    public String getString(String key, String defValue) {
        if (!isInitialed) {
            return null;
        }
        return sp.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        if (!isInitialed) {
            return 0;
        }
        return sp.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        if (!isInitialed) {
            return false;
        }
        return sp.getBoolean(key, defValue);
    }

    public boolean putString(String key, String value) {
        if (!isInitialed) {
            return false;
        }
        if (key == null || "".equals(key)) {
            return false;
        }
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean putInt(String key, int value) {
        if (!isInitialed) {
            return false;
        }
        if (key == null || "".equals(key)) {
            return false;
        }
        editor.putInt(key, value);
        return editor.commit();
    }

    public boolean putLong(String key, long value) {
        if (!isInitialed) {
            return false;
        }
        if (key == null || "".equals(key)) {
            return false;
        }
        editor.putLong(key, value);
        return editor.commit();
    }

    public long getLong(String key, long defValue) {
        if (!isInitialed) {
            return defValue;
        }
        if (key == null || "".equals(key)) {
            return defValue;
        }
        return sp.getLong(key, defValue);
    }

    public boolean putBoolean(String key, boolean value) {
        if (!isInitialed) {
            return false;
        }
        if (key == null || "".equals(key)) {
            return false;
        }
        editor.putBoolean(key, value);

        return editor.commit();
    }

    public boolean remove(String key) {

        if (!isInitialed) {
            return false;
        }

        if (key == null || "".equals(key) || !sp.contains(key)) {
            return false;
        }

        editor.remove(key);
        return editor.commit();
    }
}

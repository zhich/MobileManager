/*
  * SPUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.zch.baselib.base.BaseApp;

/**
 * 信息存储
 *
 * @author zch
 * @since 2017-11-23
 */
public class SPUtils {

    private static Context mContext = BaseApp.getInstance();
    private static final String APP_NAME = mContext.getPackageName();

    private SPUtils() {
    }

    /**
     * 保存数据
     *
     * @param key：保存数据的键
     * @param objectValue：保存的数据
     */
    public static void setParam(String key, Object objectValue) {
        String type = objectValue.getClass().getSimpleName();
        SharedPreferences preferences = mContext.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if ("String".equals(type)) {
            editor.putString(key, (String) objectValue);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) objectValue);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) objectValue);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) objectValue);
        }
        editor.commit();
    }

    /**
     * 根据键获取值
     *
     * @param key：获取数据的键
     * @param defaultValue：默认返回值
     * @return
     */
    public static Object getParam(String key, Object defaultValue) {
        String type = defaultValue.getClass().getSimpleName();
        SharedPreferences preferences = mContext.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        if ("String".equals(type)) {
            return preferences.getString(key, (String) defaultValue);
        } else if ("Integer".equals(type)) {
            return preferences.getInt(key, (Integer) defaultValue);
        } else if ("Boolean".equals(type)) {
            return preferences.getBoolean(key, (Boolean) defaultValue);
        } else if ("Long".equals(type)) {
            return preferences.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    /**
     * 清除所有保存的数据
     */
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void clean() {
        SharedPreferences sp = mContext.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
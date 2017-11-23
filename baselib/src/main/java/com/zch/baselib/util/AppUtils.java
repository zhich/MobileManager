/*
  * AppUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * 应用工具类
 *
 * @author zch
 * @since 2017-11-23
 */
public class AppUtils {

    /**
     * Get AndroidManifest's meta element by metaKey
     *
     * @param context
     * @param metaKey
     * @return
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return apiKey;
    }

    /**
     * Get AndroidManifest's meta element by metaKey
     *
     * @param context
     * @param metaKey
     * @return
     */
    public static int getIntMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        int apiKey = 0;
        if (context == null || metaKey == null) {
            return 80;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getInt(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 80;
        }
        return apiKey;
    }

    /**
     * 获取当前主程序的版本信息
     *
     * @return
     */
    private static PackageInfo getLocalVersion(Context context) {
        //获取packagemanager实例
        PackageManager manager = context.getPackageManager();
        //getPackageName()是当前类的包名,0代表是获取版本信息
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 获取软件版本号(对应AndroidManifest.xml下android:VersionCode)
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            versionCode = getLocalVersion(context).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取软件版本名称(对应AndroidManifest.xml下android:VersionName)
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "1.0.0";
        try {
            versionName = getLocalVersion(context).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
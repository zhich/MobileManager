/*
  * NetUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络相关工具类
 *
 * @author zch
 * @since 2017-11-23
 */
public class NetUtils {

    /**
     * 判断网络是否可用的方法
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo mNetworkinfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkinfo != null) {
                return mNetworkinfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否连接上的方法
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null && mWiFiNetworkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
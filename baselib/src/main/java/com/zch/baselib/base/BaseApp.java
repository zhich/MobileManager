/*
 * BaseApp      2017-11-22
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.base;

import android.app.Application;

/**
 * App 应用环境对象
 *
 * @author zch
 * @since 2017-11-22
 */
public class BaseApp extends Application {

    protected static BaseApp sInstance;

    public static BaseApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }
}
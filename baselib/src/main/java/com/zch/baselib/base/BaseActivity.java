/*
 * BaseActivity      2017-11-22
 * Copyright © gnt All Rights Reserved.
 *
 */
package com.zch.baselib.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * AppCompatActivity 基类，与业务逻辑无关
 *
 * @author zch
 * @since 2017-11-22
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initBeforeCreate();
        super.onCreate(savedInstanceState);
        mContext = this;

        int layoutResourceId = getLayoutResource();
        if (layoutResourceId != 0) {
            setContentView(layoutResourceId);
        }
        bindView();
        init();
        setListener();
    }

    /**
     * 在设置 ContentView 或者 Activity 的 OnCreate 之前的初始化
     */
    protected void initBeforeCreate() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置手机屏幕的旋转不触发
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题栏
    }

    protected abstract int getLayoutResource();

    protected abstract void bindView();

    protected abstract void init();

    protected abstract void setListener();
}
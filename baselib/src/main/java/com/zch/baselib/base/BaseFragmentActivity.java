/*
  * BaseFragmentActivity      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * FragmentActivity 基类，与业务逻辑无关
 *
 * @author zch
 * @since 2017-11-22
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    protected Context mContext;
    protected Unbinder mUnbinder;
    protected ArrayList<BasePresenter> mPresenters = new ArrayList<>();

    protected abstract int getLayoutResource();

    protected abstract void init();

    protected abstract void setListener();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initBeforeCreate();
        super.onCreate(savedInstanceState);
        mContext = this;

        int layoutResourceId = getLayoutResource();
        if (layoutResourceId != 0) {
            setContentView(layoutResourceId);
        }
        mUnbinder = ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明状态栏
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

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

//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        for (BasePresenter p : mPresenters) {
            p.detach();
        }
        super.onDestroy();
    }

    protected void addPresenter(BasePresenter presenter) {
        mPresenters.add(presenter);
    }
}
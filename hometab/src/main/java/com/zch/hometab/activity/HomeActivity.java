/*
 * HomeActivity      2017-11-22
 * Copyright © gnt All Rights Reserved.
 *
 */
package com.zch.hometab.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zch.baselib.widget.Titlebar;
import com.zch.bizzlib.base.BaseAppActivity;
import com.zch.bizzlib.constant.RouterPathConstant;
import com.zch.hometab.R;
import com.zch.hometab.R2;

import butterknife.BindView;

/**
 * 主界面
 *
 * @author zch
 * @since 2017-11-22
 */
@Route(path = RouterPathConstant.HomeTab.HOME_ACTIVITY)
public class HomeActivity extends BaseAppActivity {

    @BindView(R2.id.home_titlebar)
    Titlebar titlebar;
    @BindView(R2.id.home_tv_content)
    TextView tvContent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        titlebar.initData("首页");

        tvContent.setText("Welcome to mobile manager");
    }
}
/*
 * StartActivity      2017-11-22
 * Copyright © gnt All Rights Reserved.
 *
 */
package com.zch.hometab.activity;

import android.content.Intent;

import com.zch.bizzlib.base.BaseAppActivity;
import com.zch.hometab.R;

/**
 * 启动页
 *
 * @author zch
 * @since 2017-11-22
 */
public class StartActivity extends BaseAppActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_start;
    }

    @Override
    protected void init() {
        startActivity(new Intent(this, HomeActivity.class));

//        ARouter.getInstance().build(RouterPathConstant.HomeTab.HOME_ACTIVITY).navigation();
        finish();
    }
}
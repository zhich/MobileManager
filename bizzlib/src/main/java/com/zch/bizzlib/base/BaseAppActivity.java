/*
 * BaseAppActivity      2017-11-22
 * Copyright © gnt All Rights Reserved.
 *
 */
package com.zch.bizzlib.base;

import com.zch.baselib.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * AppCompatActivity 基类
 *
 * @author zch
 * @since 2017-11-22
 */
public abstract class BaseAppActivity extends BaseActivity {

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }


    @Override
    protected void setListener() {

    }

    @Override
    public void finish() {
        super.finish();
    }
}
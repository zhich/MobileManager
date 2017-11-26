/*
  * BaseAppFragmentActivity      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.bizzlib.base;

import com.zch.baselib.base.BaseFragmentActivity;

import butterknife.ButterKnife;

/**
 * FragmentActivity 基类
 *
 * @author zch
 * @since 2017-11-22
 */
public abstract class BaseAppFragmentActivity extends BaseFragmentActivity {

    @Override
    protected void setListener() {

    }

    @Override
    public void finish() {
        super.finish();
    }
}
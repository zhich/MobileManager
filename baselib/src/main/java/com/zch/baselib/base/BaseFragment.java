/*
  * BaseFragment      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment 的基类，与业务逻辑无关
 *
 * @author zch
 * @since 2017-11-22
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mFragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = this.getActivity();
        int layoutResourceId = getLayoutResource();
        if (layoutResourceId != 0) {
            mFragmentView = inflater.inflate(getLayoutResource(), container, false);
        }
        bindView();
        return mFragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    protected abstract int getLayoutResource();

    protected abstract void bindView();

    protected abstract void init();
}
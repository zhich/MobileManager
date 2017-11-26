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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 的基类，与业务逻辑无关
 *
 * @author zch
 * @since 2017-11-22
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mFragmentView;
    protected Unbinder mUnbinder;
    protected ArrayList<BasePresenter> mPresenters = new ArrayList<>();

    protected abstract int getLayoutResource();

    protected abstract void init();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResourceId = getLayoutResource();
        if (layoutResourceId != 0) {
            mFragmentView = inflater.inflate(getLayoutResource(), container, false);
        }
        mUnbinder = ButterKnife.bind(this, mFragmentView);
        return mFragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        for (BasePresenter p : mPresenters) {
            p.detach();
        }
        super.onDestroy();
    }

    protected void addPresenter(BasePresenter presenter) {
        mPresenters.add(presenter);
    }
}
/*
  * BasePresenter      2017-11-24
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter 层基类
 *
 * @author zch
 * @since 2017-11-24
 */
public class BasePresenter<V extends IView> {

    public V mView;
    protected CompositeDisposable mCompositeDisposable;

    public BasePresenter(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
    }
}
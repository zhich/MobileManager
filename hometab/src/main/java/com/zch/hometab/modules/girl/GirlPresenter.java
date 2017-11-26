/*
  * GirlPresenter      2017-11-26
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import com.zch.baselib.base.BasePresenter;
import com.zch.baselib.retrofit.RetrofitClient;
import com.zch.baselib.util.LogUtils;
import com.zch.baselib.util.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * description
 *
 * @author zch
 * @since 2017-11-26
 */
public class GirlPresenter extends BasePresenter<IGirlView> {

    public GirlPresenter(IGirlView view) {
        super(view);
    }

    public void getGirlItemData(String cid, int page) {
        RetrofitClient.getInstance()
                .create(IGirlService.class)
                .getGirlItemData(cid, page)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
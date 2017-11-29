/*
  * GirlPresenter      2017-11-26
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import com.zch.baselib.base.BasePresenter;
import com.zch.baselib.retrofit.RetrofitClient;
import com.zch.baselib.util.JsonUtils;
import com.zch.baselib.util.LogUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
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

    public void getTuiGirls(int page, int len) {
        RetrofitClient.getInstance()
                .create(IGirlService.class)
                .getTuiGirls(page, len)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<TuiGirl>>() {
                    @Override
                    public void accept(Result<TuiGirl> tuiGirlResult) throws Exception {
                        LogUtils.e("info-->", JsonUtils.toJson(tuiGirlResult.data));
                        mView.onSuccess(tuiGirlResult.data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.e(throwable.getMessage());
                        mView.onError();
                    }
                });
    }
}
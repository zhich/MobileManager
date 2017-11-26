/*
  * GirlDataSource      2017-11-26
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import com.zch.baselib.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * description
 *
 * @author zch
 * @since 2017-11-26
 */
public class GirlDataSource {

    public Observable<String> getGirlItemData(String cid, int page) {
        return RetrofitClient.getInstance()
                .create(IGirlService.class)
                .getGirlItemData(cid, page)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });
    }
}
/*
  * RetrofitClient      2017-11-26
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description
 *
 * @author zch
 * @since 2017-11-26
 */
public class RetrofitClient {

    private RetrofitClient() {
    }

    private static class ClientHolder {
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.dbmeinv.com/dbgroup/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return ClientHolder.retrofit;
    }

}
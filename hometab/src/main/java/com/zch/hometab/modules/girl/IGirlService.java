/*
  * IGirlService      2017-11-26
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 妹子接口定义
 *
 * @author zch
 * @since 2017-11-26
 */
public interface IGirlService {

    @GET("https://api.tuimeizi.cn/girls")
    Observable<Result<TuiGirl>> getTuiGirls(@Query("page") int page, @Query("len") int len);
}
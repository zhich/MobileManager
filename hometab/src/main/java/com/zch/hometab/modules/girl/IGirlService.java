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
 * description
 *
 * @author zch
 * @since 2017-11-26
 */
public interface IGirlService {

    @GET("show.htm")
    Observable<String> getGirlItemData(@Query("cid") String cid, @Query("pager_offset") int pager_offset);
}
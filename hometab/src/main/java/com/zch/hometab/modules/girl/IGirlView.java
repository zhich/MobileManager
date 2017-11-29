/*
  * IGirlView      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import com.zch.baselib.base.IView;

import java.util.List;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public interface IGirlView extends IView {

    void onSuccess(List<TuiGirl> data);
}
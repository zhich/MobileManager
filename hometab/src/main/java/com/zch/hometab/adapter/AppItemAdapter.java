/*
  * AppItemAdapter      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zch.hometab.R;
import com.zch.hometab.entity.AppItem;

import java.util.List;

/**
 * 应用项适配器
 *
 * @author zch
 * @since 2017-11-22
 */
public class AppItemAdapter extends BaseQuickAdapter<AppItem, BaseViewHolder> {

    public AppItemAdapter(int layoutResId, @Nullable List<AppItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppItem item) {
        helper.setImageResource(R.id.itemApp_iv_icon, item.iconResId);
        helper.setText(R.id.itemApp_tv_name, item.name);
    }
}
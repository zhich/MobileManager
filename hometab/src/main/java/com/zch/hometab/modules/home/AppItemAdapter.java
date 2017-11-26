/*
  * AppItemAdapter      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.home;

import android.content.Context;

import com.zch.baselib.adapter.BaseAdapter;
import com.zch.baselib.adapter.BaseViewHolder;
import com.zch.hometab.R;

import java.util.List;

/**
 * 应用项适配器
 *
 * @author zch
 * @since 2017-11-22
 */
public class AppItemAdapter extends BaseAdapter<AppItem> {

    public AppItemAdapter(Context context, int layoutResId, List<AppItem> dataList) {
        super(context, layoutResId, dataList, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppItem item) {
        helper.setImageResource(R.id.itemApp_iv_icon, item.iconResId);
        helper.setText(R.id.itemApp_tv_name, item.name);
    }
}
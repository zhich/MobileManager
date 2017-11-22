/*
  * HomeFragment      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zch.bizzlib.base.BaseAppFragment;
import com.zch.hometab.R;
import com.zch.hometab.R2;
import com.zch.hometab.adapter.AppItemAdapter;
import com.zch.hometab.entity.AppItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 【首页】
 *
 * @author zch
 * @version 1.0.0
 * @since 2017-11-22
 */
public class HomeFragment extends BaseAppFragment {

    @BindView(R2.id.home_rv_app)
    RecyclerView rvApp;

    private AppItemAdapter mAppItemAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        mAppItemAdapter = new AppItemAdapter(R.layout.item_app, getAppItemList());
        rvApp.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvApp.setAdapter(mAppItemAdapter);
    }

    private List<AppItem> getAppItemList() {
        List<AppItem> appItemList = new ArrayList<>();
        appItemList.add(new AppItem(mContext.getString(R.string.note), R.drawable.ic_note));
        appItemList.add(new AppItem(mContext.getString(R.string.card), R.drawable.ic_card));
        appItemList.add(new AppItem(mContext.getString(R.string.calendar), R.drawable.ic_calendar));
        appItemList.add(new AppItem(mContext.getString(R.string.weather), R.drawable.ic_weather));
        appItemList.add(new AppItem(mContext.getString(R.string.video), R.drawable.ic_video));
        appItemList.add(new AppItem(mContext.getString(R.string.music), R.drawable.ic_music));
        return appItemList;
    }

}
/*
  * HomeFragment      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zch.baselib.util.ToastUtils;
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
        mAppItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToastShort(mContext, position + "");
            }
        });
    }

    private List<AppItem> getAppItemList() {
        String[] appNameArr = mContext.getResources().getStringArray(R.array.home_app_name_arr);

        List<AppItem> appItemList = new ArrayList<>();
        appItemList.add(new AppItem(appNameArr[0], R.drawable.ic_note));
        appItemList.add(new AppItem(appNameArr[1], R.drawable.ic_card));
        appItemList.add(new AppItem(appNameArr[2], R.drawable.ic_calendar));
        appItemList.add(new AppItem(appNameArr[3], R.drawable.ic_weather));
        appItemList.add(new AppItem(appNameArr[4], R.drawable.ic_video));
        appItemList.add(new AppItem(appNameArr[5], R.drawable.ic_music));
        return appItemList;
    }

}
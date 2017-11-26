/*
  * TypeFragment      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.zch.baselib.util.ResourceUtils;
import com.zch.bizzlib.adapter.TypePageAdapter;
import com.zch.bizzlib.base.BaseAppFragment;
import com.zch.hometab.modules.girl.GirlFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public class TypeFragment extends BaseAppFragment {

    private static final String TYPE = "type";

    @BindView(R2.id.type_tablayout)
    TabLayout mTabLayout;

    @BindView(R2.id.type_viewpager)
    ViewPager mViewPager;

    private String mType;
    private ArrayList<BaseAppFragment> mFragmentList;
    private List<String> mTitleList;

    private TypePageAdapter mTypePageAdapter;

    public static TypeFragment newInstance(String type) {
        TypeFragment fragment = new TypeFragment();
        Bundle arguments = new Bundle();
        arguments.putString(TYPE, type);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_type;
    }

    @Override
    protected void init() {
        mFragmentList = new ArrayList<>();
        mType = getArguments().getString(TYPE);
        if (ResourceUtils.resToStr(mContext, R.string.girl).equals(mType)) {
            mTitleList = ResourceUtils.stringArrayToList(mContext, R.array.girl);
            List<String> subtypes = ResourceUtils.stringArrayToList(mContext, R.array.girl_cid);
            for (String subtype : subtypes) {
                mFragmentList.add(GirlFragment.newInstance(subtype));
            }
        }

        mTypePageAdapter = new TypePageAdapter(getChildFragmentManager());
        mTypePageAdapter.setData(mFragmentList, mTitleList);
        mViewPager.setAdapter(mTypePageAdapter);
        mViewPager.setOffscreenPageLimit(mTitleList.size() - 1);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
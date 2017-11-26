/*
  * TypePageAdapter      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.bizzlib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zch.bizzlib.base.BaseAppFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public class TypePageAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseAppFragment> mFragmentList;
    private List<String> mTitleList;

    public TypePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return (null == mFragmentList || mFragmentList.isEmpty()) ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return null == mFragmentList ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    public void setData(ArrayList<BaseAppFragment> fragmentList, List<String> titleList) {
        mFragmentList = fragmentList;
        mTitleList = titleList;
    }
}
/*
 * HomeActivity      2017-11-22
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.hometab.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zch.baselib.widget.Titlebar;
import com.zch.bizzlib.base.BaseAppFragmentActivity;
import com.zch.bizzlib.constant.RouterPathConstant;
import com.zch.hometab.R;
import com.zch.hometab.R2;
import com.zch.hometab.fragment.GirlFragment;
import com.zch.hometab.fragment.HomeFragment;
import com.zch.hometab.fragment.MineFragment;
import com.zch.hometab.fragment.NewsFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * 主界面
 *
 * @author zch
 * @since 2017-11-22
 */
@Route(path = RouterPathConstant.HomeTab.HOME_ACTIVITY)
public class HomeActivity extends BaseAppFragmentActivity {

    @BindView(R2.id.home_titlebar)
    Titlebar titlebar;
    @BindViews({R2.id.bottomTab_iv_home, R2.id.bottomTab_iv_news, R2.id.bottomTab_iv_girl, R2.id.bottomTab_iv_mine})
    ImageView[] tabImgs;
    @BindViews({R2.id.bottomTab_tv_home, R2.id.bottomTab_tv_news, R2.id.bottomTab_tv_girl, R2.id.bottomTab_tv_mine})
    TextView[] tabTexts;

    /* package */ Fragment[] mFragments;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private GirlFragment mGirlFragment;
    private MineFragment mMineFragment;

    /* package */ int mCurrentTabIndex; // 当前 fragment 的 index

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        titlebar.initData(mContext.getString(R.string.home)).setBackVisibility(false);

        mHomeFragment = new HomeFragment();
        mNewsFragment = new NewsFragment();
        mGirlFragment = new GirlFragment();
        mMineFragment = new MineFragment();
        mFragments = new Fragment[]{mHomeFragment, mNewsFragment, mGirlFragment, mMineFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fl_realTabContent, mHomeFragment)
                .show(mHomeFragment).commit();

        mCurrentTabIndex = 0;
        tabImgs[mCurrentTabIndex].setSelected(true);
        tabTexts[mCurrentTabIndex].setTextColor(ContextCompat.getColor(mContext, R.color.color_theme));
    }

    @OnClick({R2.id.bottomTab_rl_home, R2.id.bottomTab_rl_news, R2.id.bottomTab_rl_girl, R2.id.bottomTab_rl_mine})
    public void onClickEvent(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bottomTab_rl_home) {
            titlebar.setTitle(mContext.getString(R.string.home));
            processTabClickEvent(0);
        } else if (viewId == R.id.bottomTab_rl_news) {
            titlebar.setTitle(mContext.getString(R.string.news));
            processTabClickEvent(1);
        } else if (viewId == R.id.bottomTab_rl_girl) {
            titlebar.setTitle(mContext.getString(R.string.girl));
            processTabClickEvent(2);
        } else if (viewId == R.id.bottomTab_rl_mine) {
            titlebar.setTitle(mContext.getString(R.string.mine));
            processTabClickEvent(3);
        }
    }

    /**
     * 处理 tab 点击事件
     *
     * @param index
     */
    /* package */ void processTabClickEvent(int index) {
        if (mCurrentTabIndex == index) {
            return;
        }
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(mFragments[mCurrentTabIndex]);
        if (!mFragments[index].isAdded()) {
            trx.add(R.id.home_fl_realTabContent, mFragments[index]);
        }
        trx.show(mFragments[index]).commit();

        tabImgs[mCurrentTabIndex].setSelected(false);
        tabImgs[index].setSelected(true);
        tabTexts[mCurrentTabIndex].setTextColor(ContextCompat.getColor(mContext, R.color.color_444444));
        tabTexts[index].setTextColor(ContextCompat.getColor(mContext, R.color.color_theme));
        mCurrentTabIndex = index;
    }
}
/*
 * HomeActivity      2017-11-22
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.hometab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zch.baselib.util.ResourceUtils;
import com.zch.baselib.widget.Titlebar;
import com.zch.bizzlib.base.BaseAppFragmentActivity;
import com.zch.bizzlib.constant.RouterPathConstant;
import com.zch.hometab.modules.home.HomeFragment;
import com.zch.hometab.modules.mine.MineFragment;
import com.zch.hometab.modules.news.NewsFragment;

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
    Titlebar mTitlebar;
    @BindViews({R2.id.bottomTab_iv_home, R2.id.bottomTab_iv_news, R2.id.bottomTab_iv_girl, R2.id.bottomTab_iv_mine})
    ImageView[] mTabImgs;
    @BindViews({R2.id.bottomTab_tv_home, R2.id.bottomTab_tv_news, R2.id.bottomTab_tv_girl, R2.id.bottomTab_tv_mine})
    TextView[] mTabTexts;

    /* package */ Fragment[] mFragments;
    private HomeFragment mHomeFragment;
    private NewsFragment mNewsFragment;
    private TypeFragment mTypeGirlFragment;
    private MineFragment mMineFragment;

    /* package */ int mCurrentTabIndex; // 当前 fragment 的 index

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        mTitlebar.initData(mContext.getString(R.string.home)).setBackVisibility(false);

        mHomeFragment = new HomeFragment();
        mNewsFragment = new NewsFragment();
        mTypeGirlFragment = TypeFragment.newInstance(ResourceUtils.resToStr(mContext, R.string.girl));
        mMineFragment = new MineFragment();
        mFragments = new Fragment[]{mHomeFragment, mNewsFragment, mTypeGirlFragment, mMineFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fl_realTabContent, mHomeFragment)
                .show(mHomeFragment).commit();

        mCurrentTabIndex = 0;
        mTabImgs[mCurrentTabIndex].setSelected(true);
        mTabTexts[mCurrentTabIndex].setTextColor(ContextCompat.getColor(mContext, R.color.color_theme));
    }

    @OnClick({R2.id.bottomTab_rl_home, R2.id.bottomTab_rl_news, R2.id.bottomTab_rl_girl, R2.id.bottomTab_rl_mine})
    public void onClickEvent(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bottomTab_rl_home) {
            mTitlebar.setTitle(mContext.getString(R.string.home));
            processTabClickEvent(0);
        } else if (viewId == R.id.bottomTab_rl_news) {
            mTitlebar.setTitle(mContext.getString(R.string.news));
            processTabClickEvent(1);
        } else if (viewId == R.id.bottomTab_rl_girl) {
            mTitlebar.setTitle(mContext.getString(R.string.girl));
            processTabClickEvent(2);
        } else if (viewId == R.id.bottomTab_rl_mine) {
            mTitlebar.setTitle(mContext.getString(R.string.mine));
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

        mTabImgs[mCurrentTabIndex].setSelected(false);
        mTabImgs[index].setSelected(true);
        mTabTexts[mCurrentTabIndex].setTextColor(ContextCompat.getColor(mContext, R.color.color_444444));
        mTabTexts[index].setTextColor(ContextCompat.getColor(mContext, R.color.color_theme));
        mCurrentTabIndex = index;
    }
}
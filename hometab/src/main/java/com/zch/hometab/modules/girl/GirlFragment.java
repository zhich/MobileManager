/*
  * GirlFragment      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zch.baselib.adapter.BaseViewHolder;
import com.zch.baselib.adapter.OnItemClickListener;
import com.zch.baselib.adapter.OnLoadMoreListener;
import com.zch.baselib.base.IView;
import com.zch.baselib.util.ToastUtils;
import com.zch.bizzlib.base.BaseAppFragment;
import com.zch.hometab.R;
import com.zch.hometab.R2;
import com.zch.hometab.constant.IntentConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 【妹子】界面
 *
 * @author zch
 * @since 2017-11-22
 */
public class GirlFragment extends BaseAppFragment implements SwipeRefreshLayout.OnRefreshListener,
        IGirlView {

    @BindView(R2.id.girl_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.girl_rv_grils)
    RecyclerView mGirlsRv;

    private GirlItemAdapter mGirlItemAdapter;

    /* package */ int mPageCount = 1;
    /* package */ int mTempPageCount = 2;
    /* package */ String mSubtype;
    /* package */ boolean mIsLoadMore; // 是否是底部加载更多

    public static GirlFragment newInstance(String subtype) {
        GirlFragment fragment = new GirlFragment();
        Bundle arguments = new Bundle();
        arguments.putString(IntentConstant.SUB_TYPE, subtype);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_girl;
    }

    @Override
    protected void init() {
        mSubtype = getArguments().getString(IntentConstant.SUB_TYPE);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // 实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        mGirlItemAdapter = new GirlItemAdapter(mContext, R.layout.item_girl, new ArrayList<GirlItemData>(), true);
        mGirlItemAdapter.setLoadingView(R.layout.layout_loading_more);
        mGirlItemAdapter.setOnItemClickListener(new OnItemClickListener<GirlItemData>() {
            @Override
            public void onItemClick(BaseViewHolder helper, GirlItemData item, int position) {
                ToastUtils.showToastShort(mContext, position + "");
            }
        });
        mGirlItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (mPageCount == mTempPageCount && !isReload) {
                    return;
                }
                mIsLoadMore = true;
                mPageCount = mTempPageCount;
                fetchData();
            }
        });

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE); // 可防止 item 切换
        mGirlsRv.setLayoutManager(layoutManager);
        mGirlsRv.setAdapter(mGirlItemAdapter);

        fetchData();
    }

    /* package */ void fetchData() {
        GirlPresenter girlPresenter = new GirlPresenter(this);
        girlPresenter.getGirlItemData(mSubtype, mPageCount);
        addPresenter(girlPresenter);
    }

    @Override
    public void onRefresh() {
        mIsLoadMore = false;
        mPageCount = 1;
        mTempPageCount = 2;
        fetchData();
    }

    @Override
    public void onSuccess(List<GirlItemData> data) {

    }

    @Override
    public void onError() {
        if (mIsLoadMore) {
            mGirlItemAdapter.setLoadFailedView(R.layout.layout_load_failed);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

}
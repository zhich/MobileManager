/*
  * BaseAdapter      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zch.baselib.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter 基类
 *
 * @author zch
 * @since 2017-11-25
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_COMMON_VIEW = 0x01;
    private static final int TYPE_FOOTER_VIEW = 0x02;
    private static final int TYPE_EMPTY_VIEW = 0x03;
    private static final int TYPE_DEFAULT_VIEW = 0x04;

    protected Context mContext;
    private int mItemLayoutResId;

    private View mLoadingView;
    private View mLoadFailedView;
    private View mLoadEndView;
    private View mEmptyView;
    private RelativeLayout mFooterLayout;

    protected List<T> mDataList;

    private boolean mOpenLoadMore;
    private boolean isAutoLoadMore = true;

    protected abstract void convert(BaseViewHolder helper, T item);

    public BaseAdapter(Context context,int itemLayoutResId, List<T> dataList) {
        mContext = context;
        mItemLayoutResId = itemLayoutResId;
        mDataList = null == dataList ? new ArrayList<T>() : dataList;
    }

    public BaseAdapter(Context context,int itemLayoutResId, List<T> dataList, boolean isOpenLoadMore) {
        mContext = context;
        mItemLayoutResId = itemLayoutResId;
        mDataList = null == dataList ? new ArrayList<T>() : dataList;
        mOpenLoadMore = isOpenLoadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case TYPE_COMMON_VIEW:
                baseViewHolder = BaseViewHolder.create(mContext, mItemLayoutResId, parent);
                break;
            case TYPE_FOOTER_VIEW:
                if (null == mFooterLayout) {
                    mFooterLayout = new RelativeLayout(mContext);
                }
                baseViewHolder = BaseViewHolder.create(mFooterLayout);
                break;
            case TYPE_EMPTY_VIEW:
                baseViewHolder = BaseViewHolder.create(mEmptyView);
                break;
            case TYPE_DEFAULT_VIEW:
                baseViewHolder = BaseViewHolder.create(new View(mContext));
                break;
        }
        return baseViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.isEmpty() && null != mEmptyView) {
            return TYPE_EMPTY_VIEW;
        }
        if (isFooterView(position)) {
            return TYPE_FOOTER_VIEW;
        }
        if (mDataList.isEmpty()) {
            return TYPE_DEFAULT_VIEW;
        }
        return TYPE_COMMON_VIEW;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_COMMON_VIEW:
                bindCommonItem(holder, position);
                break;
        }
    }

    private void bindCommonItem(RecyclerView.ViewHolder holder, final int position) {
        final BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        convert(baseViewHolder, mDataList.get(position));

        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(baseViewHolder, mDataList.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + getFooterViewCount();
    }

    private int getFooterViewCount() {
        return mOpenLoadMore ? 1 : 0;
    }

    /**
     * StaggeredGridLayoutManager 模式时，FooterView 可占据一行
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (null != lp && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * GridLayoutManager 模式时，FooterView 可占据一行，判断 RecyclerView 是否到达底部
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
        startLoadMore(recyclerView, layoutManager);
    }

    /**
     * 是否 FooterView
     *
     * @param position
     * @return
     */
    /* package */ boolean isFooterView(int position) {
        return mOpenLoadMore && getItemCount() > 1 && position >= getItemCount() - 1;
    }

    /**
     * 判断列表是否滑动到底部
     *
     * @param recyclerView
     * @param layoutManager
     */
    private void startLoadMore(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        if (!mOpenLoadMore || null == onLoadMoreListener) {
            return;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isAutoLoadMore && findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                        scrollLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isAutoLoadMore && findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                    scrollLoadMore();
                } else if (isAutoLoadMore) {
                    isAutoLoadMore = false;
                }
            }
        });
    }

    /* package */ int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            return findMax(lastVisibleItemPositions);
        }
        return -1;
    }

    /* package */ int findMax(int[] lastVisibleItemPositions) {
        int max = lastVisibleItemPositions[0];
        for (int value : lastVisibleItemPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * 到达底部开始刷新
     */
    /* package */ void scrollLoadMore() {
        if (mFooterLayout.getChildAt(0) == mLoadingView) {
            onLoadMoreListener.onLoadMore(false);
        }
    }

    /* package */  void addFooterView(View footerView) {
        if (null == footerView) {
            return;
        }
        if (null == mFooterLayout) {
            mFooterLayout = new RelativeLayout(mContext);
        }
        mFooterLayout.removeAllViews();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooterLayout.addView(footerView, params);
    }

    private View inflate(int layoutId) {
        if (layoutId <= 0) {
            return null;
        }
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    /* package */ OnItemClickListener<T> onItemClickListener;
    /* package */ OnLoadMoreListener onLoadMoreListener;

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    /**
     * 加载中布局
     *
     * @param loadingView
     */
    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
        addFooterView(mLoadingView);
    }

    /**
     * 加载中布局
     *
     * @param loadingId
     */
    public void setLoadingView(int loadingId) {
        setLoadingView(inflate(loadingId));
    }

    /**
     * 加载失败布局
     *
     * @param loadFailedView
     */
    public void setLoadFailedView(View loadFailedView) {
        if (null == loadFailedView) {
            return;
        }
        mLoadFailedView = loadFailedView;
        addFooterView(mLoadFailedView);
        mLoadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFooterView(mLoadingView);
                if (null != onLoadMoreListener) {
                    onLoadMoreListener.onLoadMore(true);
                }
            }
        });
    }

    /**
     * 加载失败布局
     *
     * @param loadFailedId
     */
    public void setLoadFailedView(int loadFailedId) {
        setLoadFailedView(inflate(loadFailedId));
    }

    /**
     * 全部加载完成布局
     *
     * @param loadEndView
     */
    public void setLoadEndView(View loadEndView) {
        mLoadEndView = loadEndView;
        addFooterView(mLoadEndView);
    }

    /**
     * 全部加载完成布局
     *
     * @param loadEndId
     */
    public void setLoadEndView(int loadEndId) {
        setLoadEndView(inflate(loadEndId));
    }

    /**
     * 加载空数据布局
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }
}
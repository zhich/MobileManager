/*
 * Titlebar      2017-11-03
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zch.baselib.R;
import com.zch.baselib.widget.popup.GPopupWindow;
import com.zch.baselib.widget.popup.GpopupItem;

import java.util.ArrayList;

/**
 * 标题栏
 *
 * @author zch
 * @since 2017-11-03
 */
public class Titlebar extends RelativeLayout implements View.OnClickListener {

    /* package */ Context mContext;

    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvRight;

    /* package */ GPopupWindow gPopupWindow;

    /* package */ ArrayList<GpopupItem> mDataList; // 【更多】图标下的 item 数据项

    public Titlebar(Context context) {
        this(context, null);
    }

    public Titlebar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Titlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initView();
        setListener();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_titlebar, this);

        tvBack = (TextView) findViewById(R.id.titlebar_tv_back);
        tvTitle = (TextView) findViewById(R.id.titlebar_tv_title);
        tvRight = (TextView) findViewById(R.id.titlebar_tv_right);
    }

    private void setListener() {
        tvBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.titlebar_tv_back) {
            ((Activity) mContext).finish();
        } else if (viewId == R.id.titlebar_tv_right) {
            if (null != onRightTextClickListener) {
                onRightTextClickListener.onRightTextClick();
            } else if (null != onRightImgItemClickListener) {
                if (null == gPopupWindow) {
                    gPopupWindow = new GPopupWindow(mContext, mDataList);
                    gPopupWindow.setOnItemClickListener(new GPopupWindow.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            onRightImgItemClickListener.onRightImgItemClick(position);
                        }
                    });
                }
                gPopupWindow.showPopupWindow(tvRight);
            }
        }
    }

    public interface OnRightTextClickListener {
        void onRightTextClick();
    }

    /* package */ OnRightTextClickListener onRightTextClickListener;

    public void setOnRightTextClickListener(OnRightTextClickListener onRightTextClickListener) {
        this.onRightTextClickListener = onRightTextClickListener;
    }

    public interface OnRightImgItemClickListener {
        void onRightImgItemClick(int itemPosition);
    }

    /* package */ OnRightImgItemClickListener onRightImgItemClickListener;

    public void setOnRightImgItemClickListener(OnRightImgItemClickListener onRightImgItemClickListener) {
        this.onRightImgItemClickListener = onRightImgItemClickListener;
    }

    public Titlebar initData(String title) {
        tvTitle.setText(title);
        return this;
    }

    public Titlebar initData(String title, ArrayList<GpopupItem> dataList) {
        tvTitle.setText(title);

        this.mDataList = dataList;
        Drawable drawableLeft = tvRight.getCompoundDrawables()[0];
        drawableLeft.setBounds(0, 0, drawableLeft.getIntrinsicWidth(), drawableLeft.getIntrinsicHeight());
        tvRight.setCompoundDrawables(drawableLeft, null, null, null);
        tvRight.setVisibility(View.VISIBLE);
        return this;
    }

    public Titlebar initData(String title, String rightText) {
        tvTitle.setText(title);
        tvRight.setCompoundDrawables(null, null, null, null);
        tvRight.setText(rightText);
        tvRight.setVisibility(View.VISIBLE);
        return this;
    }

    public void hidePopupWindow() {
        if (null != gPopupWindow) {
            gPopupWindow.hidePopupWindow();
        }
    }

    public Titlebar setBackVisibility(boolean visibility) {
        if (null != tvBack) {
            tvBack.setVisibility(visibility ? VISIBLE : INVISIBLE);
        }
        return this;
    }

    public Titlebar setRightTvVisibility(boolean visibility) {
        if (null != tvRight) {
            tvRight.setVisibility(visibility ? VISIBLE : INVISIBLE);
        }
        return this;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setDataList(ArrayList<GpopupItem> dataList) {
        this.mDataList = dataList;
        if (null != gPopupWindow) {
            gPopupWindow.updateDataList(dataList);
        }
    }

    public ArrayList<GpopupItem> getDataList() {
        return mDataList;
    }
}
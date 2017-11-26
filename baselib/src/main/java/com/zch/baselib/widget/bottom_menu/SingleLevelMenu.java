/*
 * SingleLevelMenu      2017-09-11
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.bottom_menu;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zch.baselib.R;
import com.zch.baselib.util.ListUtils;
import com.zch.baselib.widget.wheel.ArrayWheelAdapter;
import com.zch.baselib.widget.wheel.MyWheelView;
import com.zch.baselib.widget.wheel.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级选项菜单
 *
 * @author zch
 * @since 2017-09-11
 */
public class SingleLevelMenu extends BaseMenu {

    private TextView mTitleTv;
    private MyWheelView mMyWheelView;

    /* package */ ChildItem mSelItem; //选中的项
    /* package */ List<ChildItem> mDataList;

    public SingleLevelMenu(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mDialog = new Dialog(mContext, R.style.base_menu);
        mDialog.setContentView(R.layout.layout_single_level_menu);

        mTitleTv = (TextView) mDialog.findViewById(R.id.singleLevelMenu_tv_title);
        mMyWheelView = (MyWheelView) mDialog.findViewById(R.id.singleLevelMenu_wheel);

        mDialog.findViewById(R.id.singleLevelMenu_tv_cancel).setOnClickListener(this);
        mDialog.findViewById(R.id.singleLevelMenu_tv_sure).setOnClickListener(this);

        mMyWheelView.setCyclic(false);
        mMyWheelView.setLineSpacingMultiplier(2.6f);
        mMyWheelView.setTextSize(18);
        mMyWheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (!ListUtils.isEmpty(mDataList)) {
                    mSelItem = mDataList.get(index);
                }
            }
        });

        setMenuLocation();
    }

    /**
     * 初始化数据
     *
     * @param title
     * @param dataList
     * @return
     */
    public SingleLevelMenu initData(String title, List<ChildItem> dataList) {
        return initData(title, dataList, null);
    }

    /**
     * 初始化数据
     *
     * @param title
     * @param dataList
     * @param defaultItemValue 默认项的值，（默认都是唯一）
     * @return
     */
    public SingleLevelMenu initData(String title, List<ChildItem> dataList, String defaultItemValue) {
        if (ListUtils.isEmpty(dataList)) {
            return this;
        }
        this.mDataList = dataList;
        mTitleTv.setText(title);

        //int currentItem = dataList.size() / 2 == 0 ? dataList.size() / 2 - 1 : dataList.size() / 2;
        int currentItem = 0;
        if (!TextUtils.isEmpty(defaultItemValue)) {
            for (int i = 0, size = dataList.size(); i < size; i++) {
                if (defaultItemValue.equals(dataList.get(i).value)) {
                    currentItem = i;
                    break;
                }
            }
        }
        List<String> stringList = new ArrayList<>();
        for (ChildItem Item : dataList) {
            stringList.add(Item.value);
        }
        mMyWheelView.setAdapter(new ArrayWheelAdapter(stringList));
        mMyWheelView.setCurrentItem(currentItem);
        mSelItem = dataList.get(currentItem);
        return this;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (null == onClickCallback) {
            return;
        }
        int viewId = v.getId();
        if (viewId == R.id.singleLevelMenu_tv_sure) {
            onClickCallback.onSure(mSelItem);
        } else if (viewId == R.id.singleLevelMenu_tv_cancel) {
            onClickCallback.onCancel();
        }
    }

    /* package */ OnClickCallback onClickCallback;

    public void setOnClickCallback(OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public interface OnClickCallback {

        void onCancel();

        void onSure(ChildItem selItem);
    }
}
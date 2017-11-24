/*
 * SecondLevelMenu      2017-09-15
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.bottom_menu;

import android.app.Dialog;
import android.content.Context;
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
 * 二级选项菜单
 *
 * @author zch
 * @since 2017-09-15
 */
public class SecondLevelMenu extends BaseMenu {

    private TextView tvTitle;
    private MyWheelView parentMyWheelView;
    private MyWheelView childMyWheelView;
    /* package */ ChildItem mFirstChildSelItem;
    /* package */ ChildItem mSecondChildSelItem;

    /* package */ List<ParentItem> mParentDataList;

    /* package */ int mFirstChildIndex;
    /* package */ int mSecondChildIndex;

    public SecondLevelMenu(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        dialog = new Dialog(mContext, R.style.base_menu);
        dialog.setContentView(R.layout.layout_second_level_menu);

        tvTitle = (TextView) dialog.findViewById(R.id.secondLevelMenu_tv_title);
        parentMyWheelView = (MyWheelView) dialog.findViewById(R.id.secondLevelMenu_parentWheel);
        childMyWheelView = (MyWheelView) dialog.findViewById(R.id.secondLevelMenu_childWheel);

        dialog.findViewById(R.id.secondLevelMenu_tv_cancel).setOnClickListener(this);
        dialog.findViewById(R.id.secondLevelMenu_tv_sure).setOnClickListener(this);

        parentMyWheelView.setCyclic(false);
        parentMyWheelView.setLineSpacingMultiplier(2.6f);
        parentMyWheelView.setTextSize(18);
        parentMyWheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (null != mParentDataList && index < mParentDataList.size()) {
                    mFirstChildIndex = index;
                    mFirstChildSelItem = mParentDataList.get(index).childItem;

                    List<String> secondChildValue = getSecondChildValueList();
                    if (!ListUtils.isEmpty(secondChildValue)) {
                        childMyWheelView.setAdapter(new ArrayWheelAdapter(secondChildValue));
                        childMyWheelView.setCurrentItem(0);
                        mSecondChildSelItem = mParentDataList.get(index).childList.get(0);
                    }
                }
            }
        });

        childMyWheelView.setCyclic(false);
        childMyWheelView.setLineSpacingMultiplier(2.6f);
        childMyWheelView.setTextSize(18);
        childMyWheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (!ListUtils.isEmpty(mParentDataList)) {
                    ArrayList<ChildItem> childList = mParentDataList.get(mFirstChildIndex).childList;
                    if (null != childList && index < childList.size()) {
                        mSecondChildIndex = index;
                        mSecondChildSelItem = mParentDataList.get(mFirstChildIndex).childList.get(index);
                    }
                }
            }
        });

        setMenuLocation();
    }

    /* package */ List<String> getSecondChildValueList() {
        if (null == mParentDataList || mFirstChildIndex >= mParentDataList.size()) {
            return null;
        }
        List<String> secondChildValue = new ArrayList<>();

        List<ChildItem> dataList = mParentDataList.get(mFirstChildIndex).childList;
        for (ChildItem Item : dataList) {
            secondChildValue.add(Item.value);
        }
        return secondChildValue;
    }

    public SecondLevelMenu initData(String title, List<ParentItem> parentDataList) {
        if (ListUtils.isEmpty(parentDataList)) {
            return this;
        }
        this.mParentDataList = parentDataList;
        tvTitle.setText(title);
        ParentItem parentItem = parentDataList.get(0);

        List<String> stringList = new ArrayList<>();
        for (ParentItem Item : parentDataList) {
            stringList.add(Item.childItem.value);
        }
        parentMyWheelView.setAdapter(new ArrayWheelAdapter(stringList));
        parentMyWheelView.setCurrentItem(0);
        mFirstChildSelItem = parentItem.childItem;

        stringList = new ArrayList<>();
        if (!ListUtils.isEmpty(parentItem.childList)) {
            for (ChildItem Item : parentItem.childList) {
                stringList.add(Item.value);
            }
            childMyWheelView.setAdapter(new ArrayWheelAdapter(stringList));
            childMyWheelView.setCurrentItem(0);
            mSecondChildSelItem = parentItem.childList.get(0);
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (null == onClickCallback) {
            return;
        }
        int viewId = v.getId();
        if (viewId == R.id.secondLevelMenu_tv_sure) {
            onClickCallback.onSure(mFirstChildSelItem, mSecondChildSelItem);
        } else if (viewId == R.id.secondLevelMenu_tv_cancel) {
            onClickCallback.onCancel();
        }
    }

    /* package */ OnClickCallback onClickCallback;

    public void setOnClickCallback(OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public interface OnClickCallback {

        void onCancel();

        void onSure(ChildItem firstSelItem, ChildItem secondSelItem);
    }
}
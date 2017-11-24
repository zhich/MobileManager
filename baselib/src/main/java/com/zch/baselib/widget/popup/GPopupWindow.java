/*
 * GPopupWindow      2017-11-03
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zch.baselib.R;
import com.zch.baselib.util.img.ImgLoaderUtils;

import java.util.ArrayList;

/**
 * gnt PopupWindow
 *
 * @author zch
 * @since 2017-11-03
 */
public class GPopupWindow extends PopupWindow {

    private GPopupAdapter mGPopupAdapter;
    /* package */ ArrayList<GpopupItem> mDataList;

    public GPopupWindow(final Context context, ArrayList<GpopupItem> dataList) {
        this.mDataList = dataList;

        Activity activity = (Activity) context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.popup_window, null);
        RecyclerView recyclerView = (RecyclerView) conentView.findViewById(R.id.rv_recyclerview);
        recyclerView.setNestedScrollingEnabled(false);
        mGPopupAdapter = new GPopupAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(mGPopupAdapter);

        this.setContentView(conentView);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        //ColorDrawable dw = new ColorDrawable(Color.parseColor("#8C8C8C"));
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.animationPreview);
    }

    public void updateDataList(ArrayList<GpopupItem> dataList) {
        this.mDataList = dataList;
        mGPopupAdapter.notifyDataSetChanged();
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public void hidePopupWindow() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }

    class GPopupAdapter extends RecyclerView.Adapter<GPopupAdapter.ViewHolder> {

        @Override
        public GPopupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popop, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GPopupAdapter.ViewHolder holder, final int position) {
            GpopupItem entity = mDataList.get(position);
            if (null == entity) {
                return;
            }

            ImgLoaderUtils.loadLocalImg(holder.ivIcon, entity.iconResId);
            holder.tvText.setText(entity.name);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return null == mDataList ? 0 : mDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView ivIcon;
            TextView tvText;

            public ViewHolder(View itemView) {
                super(itemView);

                ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
                tvText = (TextView) itemView.findViewById(R.id.tv_text);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /* package */ OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
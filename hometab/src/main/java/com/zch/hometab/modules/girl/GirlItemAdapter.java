/*
  * GirlItemAdapter      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import android.content.Context;

import com.zch.baselib.adapter.BaseAdapter;
import com.zch.baselib.adapter.BaseViewHolder;
import com.zch.baselib.util.img.ImgLoaderUtils;
import com.zch.bizzlib.widget.ScaleImageView;
import com.zch.hometab.R;

import java.util.List;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public class GirlItemAdapter extends BaseAdapter<TuiGirl> {

    public GirlItemAdapter(Context context, int itemLayoutResId, List<TuiGirl> dataList, boolean isOpenLoadMore) {
        super(context, itemLayoutResId, dataList, isOpenLoadMore);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuiGirl item) {
        ScaleImageView scaleImageView = helper.getView(R.id.itemGirl_iv_girl);
//        scaleImageView.setInitSize(item.width, item.height);
        ImgLoaderUtils.loadImg(item.src, scaleImageView);
    }
}
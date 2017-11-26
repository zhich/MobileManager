/*
 * ImgLoaderUtils      2017-11-22
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.util.img;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zch.baselib.base.BaseApp;

/**
 * 图片加载工具类
 *
 * @author zch
 * @since 2017-11-22
 */
public class ImgLoaderUtils {

    /**
     * 加载本地图片
     *
     * @param imageView
     * @param imgResId
     */
    public static void loadLocalImg(final ImageView imageView, int imgResId) {
        imageView.setImageResource(imgResId);
    }

    public static void loadImg(String url, ImageView iv) {
        Glide.with(BaseApp.getInstance())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(iv);
    }
}
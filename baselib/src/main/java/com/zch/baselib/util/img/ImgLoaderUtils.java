/*
 * ImgLoaderUtils      2017-11-22
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.util.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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

    public static void loadImg(ImageView iv, String url) {
        Glide.with(BaseApp.getInstance())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(iv);
    }

    /**
     * 根据控件宽度等比例缩放高度
     *
     * @param imageView
     * @param url
     * @param showWidth 要显示的宽度
     */
    public static void loadScaleHeightByWidth(final ImageView imageView, String url, final int showWidth) {
        Glide.with(BaseApp.getInstance())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        int imageWidth = bitmap.getWidth();
                        int imageHeight = bitmap.getHeight();
                        int height = showWidth * imageHeight / imageWidth;

                        ViewGroup.LayoutParams lp = imageView.getLayoutParams();

                        lp.width = showWidth;
                        lp.height = height;

                        imageView.setImageBitmap(bitmap);
                    }
                });
    }
}
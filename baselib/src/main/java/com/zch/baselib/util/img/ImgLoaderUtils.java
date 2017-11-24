/*
 * ImgLoaderUtils      2017-11-22
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.util.img;

import android.widget.ImageView;

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
}
/*
  * ScaleImageView      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.bizzlib.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 根据给定的宽高比例缩放图片
 *
 * @author zch
 * @since 2017-11-25
 */
public class ScaleImageView extends AppCompatImageView {

    private int mInitWidth;
    private int mInitHeight;

    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInitSize(int initWidth, int initHeight) {
        mInitWidth = initWidth;
        mInitHeight = initHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mInitWidth > 0 && mInitHeight > 0) {
            int w = MeasureSpec.getSize(widthMeasureSpec);
            int h = MeasureSpec.getSize(heightMeasureSpec);
            float scale = (float) mInitHeight / (float) mInitWidth;
            if (w > 0) {
                h = (int) ((float) w * scale);
            }
            setMeasuredDimension(w, h);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
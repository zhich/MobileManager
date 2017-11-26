/*
 * GEditorView      2017-09-08
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.edt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zch.baselib.R;

/**
 * 扩展 EditText 类型控件
 *
 * @author zch
 * @since 2017-09-08
 */
public class XEditorView extends BaseEditorView {

    private ImageView mRightIv;
    private TextView mRighTv;

    public XEditorView(Context context) {
        this(context, null);
    }

    public XEditorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XEditorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_clear_edittext, this);

        mTagTv = (TextView) findViewById(R.id.clearEditText_tv_tag);
        mValueEdt = (EditText) findViewById(R.id.clearEditText_edt_value);
        mClearContentBtn = (Button) findViewById(R.id.clearEditText_btn_clearContent);

        mRightIv = (ImageView) findViewById(R.id.clearEditText_iv_rightImg);
        mRighTv = (TextView) findViewById(R.id.clearEditText_tv_rightText);
    }

    @Override
    protected void setListenner() {
        super.setBaseListenner();
        mRightIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onRightImgClickLitener) {
                    onRightImgClickLitener.onRightImgClick();
                }
                if (null != onRightViewClickLitener) {
                    onRightViewClickLitener.onRightViewClick(XEditorView.this);
                }
            }
        });
    }

    public XEditorView initData(String tag) {
        mTagTv.setText(tag);
        return this;
    }

    public XEditorView initData(String tag, int rightImgResId) {
        mTagTv.setText(tag);
        mRightIv.setImageResource(rightImgResId);
        mRightIv.setVisibility(VISIBLE);

        return this;
    }

    public XEditorView initData(String tag, String rightText) {
        mTagTv.setText(tag);
        mRighTv.setText(rightText);
        mRighTv.setVisibility(VISIBLE);

        return this;
    }

    public interface OnRightImgClickLitener {
        void onRightImgClick();
    }

    public interface OnRightViewClickLitener {
        void onRightViewClick(View view);
    }

    /* package */ OnRightImgClickLitener onRightImgClickLitener;
    /* package */ OnRightViewClickLitener onRightViewClickLitener;

    public XEditorView setOnRightImgClickLitener(OnRightImgClickLitener onRightImgClickLitener) {
        this.onRightImgClickLitener = onRightImgClickLitener;
        return this;
    }

    public XEditorView setOnRightViewClickLitener(OnRightViewClickLitener onRightViewClickLitener) {
        this.onRightViewClickLitener = onRightViewClickLitener;
        return this;
    }

    /**
     * 设置编辑框提示信息
     *
     * @param hintMsg
     * @return
     */
    public XEditorView setHint(String hintMsg) {
        mValueEdt.setHint(hintMsg);
        return this;
    }
}
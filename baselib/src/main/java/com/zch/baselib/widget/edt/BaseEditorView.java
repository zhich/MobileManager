/*
 * BaseEditorView      2017-09-08
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.edt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * EditText 输入框类型控件基类
 *
 * @author zch
 * @since 2017-09-08
 */
public abstract class BaseEditorView extends LinearLayout {

    protected TextView tvTag;
    protected EditText edtValue;
    protected Button btnClearContent;

    public BaseEditorView(Context context) {
        this(context, null);
    }

    public BaseEditorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseEditorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initView();
        setListenner();
    }

    /**
     * 设置监听
     */
    protected void setBaseListenner() {
        edtValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && edtValue.hasFocus()) {
                    btnClearContent.setVisibility(VISIBLE);
                } else {
                    btnClearContent.setVisibility(GONE);
                }
                if (null != onTextChangedLitener) {
                    onTextChangedLitener.onTextChanged(edtValue.getText().toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtValue.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (getValue().length() > 0) {
                        btnClearContent.setVisibility(VISIBLE);
                    } else {
                        btnClearContent.setVisibility(GONE);
                    }
                } else {
                    btnClearContent.setVisibility(GONE);
                }
            }
        });

        btnClearContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValue.setText("");
                btnClearContent.setVisibility(GONE);
            }
        });
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置事件监听
     */
    protected abstract void setListenner();

    public interface OnTextChangedLitener {
        void onTextChanged(String s);
    }

    /* package */ OnTextChangedLitener onTextChangedLitener;

    public void setOnTextChangedLitener(OnTextChangedLitener onTextChangedLitener) {
        this.onTextChangedLitener = onTextChangedLitener;
    }

    public BaseEditorView setTagMinWidth(int width) {
        tvTag.setMinWidth(width);
        return this;
    }

    /**
     * 获取编辑框文本值
     *
     * @return
     */
    public String getValue() {
        return edtValue.getText().toString().trim();
    }

    /**
     * 设置编辑框文本值
     *
     * @param value
     * @return
     */
    public BaseEditorView setValue(String value) {
        if (null == value) {
            value = "";
        }
        edtValue.setText(value);
        return this;
    }

    /**
     * 设置编辑框输入类型
     *
     * @param type
     */
    public BaseEditorView setInputType(int type) {
        edtValue.setInputType(type);
        return this;
    }

    /**
     * 设置编辑框最大长度
     *
     * @param maxLen
     */
    public BaseEditorView setMaxLen(int maxLen) {
        edtValue.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(maxLen)});
        return this;
    }

    /**
     * 设置编辑框是否可编辑
     *
     * @param editable
     * @return
     */
    public BaseEditorView setEditable(boolean editable) {
        edtValue.setFocusable(editable);
        edtValue.setFocusableInTouchMode(editable);
        if (editable) {
            edtValue.requestFocus();
        }
        return this;
    }

    public EditText getEditText() {
        return edtValue;
    }

    public TextView getLeftTextView() {
        return tvTag;
    }
}
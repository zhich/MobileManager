/*
 * BaseMenu      2017-09-11
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.bottom_menu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zch.baselib.R;

/**
 * 菜单基类
 *
 * @author zch
 * @since 2017-09-11
 */
public class BaseMenu implements View.OnClickListener {

    protected Context mContext;
    protected Dialog dialog;

    protected BaseMenu(Context context) {
        this.mContext = context;
        this.dialog = new Dialog(mContext, R.style.base_menu);
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }

    protected void setMenuLocation() {
        if (null == mContext || null == dialog) {
            return;
        }
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.base_menu_anim_style);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.x = 0;

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (android.os.Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);
            lp.y = size.y;
        } else {
            lp.y = display.getHeight();
        }

        dialog.onWindowAttributesChanged(lp);
        dialog.setCanceledOnTouchOutside(true);
    }

    public void show() {
        if (null != dialog) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }
}
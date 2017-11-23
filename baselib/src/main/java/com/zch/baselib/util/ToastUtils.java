/*
  * ToastUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 展示 toast 的工具类
 *
 * @author zch
 * @since 2017-11-23
 */
public class ToastUtils {

    /**
     * 长时间的toast提醒用户
     *
     * @param context 上下文对象。
     * @param msg     提醒的信息。
     */
    public static void showToastLong(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 短时间的toast提醒用户
     *
     * @param context 上下文对象。
     * @param msg     提醒的信息。
     */
    public static void showToastShort(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
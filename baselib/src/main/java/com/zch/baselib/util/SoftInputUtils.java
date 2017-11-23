/*
  * SoftInputUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘输入法工具类
 *
 * @author zch
 * @since 2017-11-23
 */
public class SoftInputUtils {

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static void hideSoftForWindow(Context context) {
        if (((Activity) context).getCurrentFocus() != null) {
            InputMethodManager inputMethod = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethod.hideSoftInputFromWindow(((Activity) context)
                    .getCurrentFocus().getWindowToken(), 0);
        }
    }
}
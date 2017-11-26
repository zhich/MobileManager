/*
  * ResourceUtils      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public class ResourceUtils {

    /**
     * 解析 String 类型的 arrays.xml 元素
     *
     * @param context
     * @param arrayId
     * @return
     */
    public static List<String> stringArrayToList(Context context, int arrayId) {
        return Arrays.asList(context.getResources().getStringArray(arrayId));
    }

    public static View inflate(Context context, int viewId, ViewGroup root) {
        return LayoutInflater.from(context).inflate(viewId, root, false);
    }

    public static String resToStr(Context context, int strId) {
        return context.getString(strId);
    }
}
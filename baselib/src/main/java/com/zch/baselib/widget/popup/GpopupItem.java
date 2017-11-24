/*
 * GpopupItem      2017-11-03
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.popup;

/**
 * PopupWindow 子项实体类
 *
 * @author zch
 * @since 2017-11-03
 */
public class GpopupItem {

    public int iconResId;
    public String name;

    public GpopupItem(int iconResId, String name) {
        this.iconResId = iconResId;
        this.name = name;
    }
}
/*
  * AppItem      2017-11-22
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.entity;

/**
 * 应用项
 *
 * @author zch
 * @version 1.0.0
 * @since 2017-11-22
 */
public class AppItem {

    public String name;
    public int iconResId;

    public AppItem(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }
}
/*
 * ChildItem      2017-09-12
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.baselib.widget.bottom_menu;

/**
 * WheelView 字典实体数据
 *
 * @author zch
 * @since 2017-09-12
 */
public class ChildItem {

    public String code; //编码
    public String pcode; //父编码
    public String value; //代表的值
    public String colunmDesc; //列描述

    public ChildItem() {
    }

    public ChildItem(String value) {
        this.value = value;
    }

    public ChildItem(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
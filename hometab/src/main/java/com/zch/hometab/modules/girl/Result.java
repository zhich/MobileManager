/*
 * Result      2017-11-29
 * Copyright © zch All Rights Reserved.
 *
 */
package com.zch.hometab.modules.girl;

import java.util.List;

/**
 * description
 *
 * @author zch
 * @since 2017-11-29
 */
/*  package */ class Result<T> {

    public String code; // 200-成功
    public String msg;
    public String title;
    public int page;
    public List<T> data;
}
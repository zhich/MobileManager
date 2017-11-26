/*
  * OnItemClickListener      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.adapter;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public interface OnItemClickListener<T> {

    void onItemClick(BaseViewHolder helper, T item, int position);
}
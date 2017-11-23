/*
  * ListUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * List 工具类
 *
 * @author zch
 * @since 2017-11-23
 */
public class ListUtils {

    /**
     * 判断List是否为空
     *
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (null == sourceList || sourceList.size() == 0);
    }

    /**
     * 集合值赋值
     *
     * @param src 源集合
     * @return 目标集合
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException,
            ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(
                byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
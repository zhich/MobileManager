/*
  * NumberUtils      2017-11-23
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.baselib.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 数字相关处理工具类
 *
 * @author zch
 * @since 2017-11-23
 */
public class NumberUtils {

    /**
     * GUID是一个128位长的数字，一般用16进制表示。算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID,
     * 可以产生一个号称全球唯一的ID
     *
     * @return
     */
    public static final String generateGUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取MD5加密后的密文
     *
     * @param s
     * @return
     */
    public static String getMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 去掉小数点多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
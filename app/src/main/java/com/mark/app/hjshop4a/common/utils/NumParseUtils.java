package com.mark.app.hjshop4a.common.utils;

/**
 * 字符串转换数值工具类
 * Created by lenovo on 2017/10/20.
 */

public class NumParseUtils {
    public static long parseLong(String data) {
        long l = 0;
        try {
            l = Long.parseLong(data);
        } catch (Exception e) {
        }
        return l;
    }

    public static float parseFloat(String data) {
        float d = 0;
        try {
            d = Float.parseFloat(data);
        } catch (Exception e) {
        }
        return d;
    }

    public static double parseDouble(String data) {
        double d = 0;
        try {
            d = Double.parseDouble(data);
        } catch (Exception e) {
        }
        return d;
    }

    public static int parseInt(String data) {
        int i = 0;
        try {
            i = Integer.parseInt(data);
        } catch (Exception e) {
        }
        return i;
    }
}
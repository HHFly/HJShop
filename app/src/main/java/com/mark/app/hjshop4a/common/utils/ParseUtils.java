package com.mark.app.hjshop4a.common.utils;

/**
 * 转换工具类
 * Created by lenovo on 2017/11/2.
 */

public class ParseUtils {
    public static long parseLong(String data) {
        long l = 0;
        try {
            l = Long.valueOf(data);
        } catch (Exception e) {
        }
        return l;
    }

    public static int parseInt(String data) {
        return (int) parseDouble(data);
    }

    public static double parseDouble(String data) {
        double d = 0;
        try {
            d = Double.parseDouble(data);
        } catch (Exception e) {
        }
        return d;
    }
}

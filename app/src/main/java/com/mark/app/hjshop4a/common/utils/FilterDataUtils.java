package com.mark.app.hjshop4a.common.utils;

import android.text.TextUtils;

/**
 * 过滤数据工具类
 * Created by lenovo on 2017/10/30.
 */

public class FilterDataUtils {
    /**
     * 过滤符号,币制的符号
     *
     * @return
     */
    public static String filterMoneyLabel(String data) {
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        return data.replaceAll("[0-9.]", "");
    }

    /**
     * 过滤空数据
     *
     * @param data
     * @param def
     * @return
     */
    public static String filterEmpty(String data, String def) {
        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(data.replaceAll("\\s", ""))) {
            return def;
        }
        return data;
    }

    /**
     * 过滤金额
     * 将服务器给的符号100,00.00---->10000.00
     *
     * @param data
     * @return
     */
    public static String filterMoney(String data) {
        if (TextUtils.isEmpty(data)) {
            return "0";
        }
        try {
            return data.replaceAll("[^0-9.]", "");
        }catch (Exception e){
            return "0";
        }

    }

    /**
     * 过滤金额
     * 将服务器给的符号100,00.00---->符号10000.00
     *
     * @param data
     * @return
     */
    public static String filterMoneyHasLabel(String data) {
//        return filterMoneyHasLabel(data, "0.00");
        return filterMoneyHasLabel(data, data);
    }

    /**
     * 过滤金额
     * 将服务器给的符号100,00.00---->符号10000.00
     *
     * @param data
     * @param def  金额为0或空的默认字符串
     * @return
     */
    public static String filterMoneyHasLabel(String data, String def) {
        String after = filterMoney(data);
        if (TextUtils.isEmpty(after) || parseDouble(after) == 0) {
            return def;
        } else {
            return data;
        }
    }

    /**
     * 将字符串转为double
     *
     * @param data
     * @return
     */
    public static double parseDouble(String data) {
        double d = 0;
        try {
            d = Double.parseDouble(data);
        } catch (Exception e) {
            LogUtils.logFormat("FilterDataUtils", "parseDouble", "Double.parseDouble(data);");
        }
        return d;
    }
}

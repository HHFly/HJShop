package com.mark.app.hjshop4a.common.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.mark.app.hjshop4a.app.AppContext;


/**
 * 字符串拼接
 * Created by lenovo on 2017/9/4.
 */

public class StringUtils {
    private static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 截取的字符串是否等于equals
     *
     * @param data
     * @param begin
     * @param end
     * @param equals
     * @return
     */
    public static boolean isSubstringEquals(String data, int begin, int end, String equals) {
        String subString = substring(data, begin, end);
        return subString.equals(equals);
    }

    /**
     * 截取字符串
     *
     * @param data
     * @param begin
     * @param end
     * @return
     */
    public static String substring(String data, int begin, int end) {
        try {
            return data.substring(begin, end);
        } catch (Exception e) {
            LogUtils.logFormat("StringUtils", "substring", "字符串截取异常");
            if (AppContext.isDebudEnv()) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
     * 截取字符串
     *
     * @param data
     * @param begin
     * @return
     */
    public static String substring(String data, int begin) {
        try {
            return data.substring(begin);
        } catch (Exception e) {
            LogUtils.logFormat("StringUtils", "substring", "字符串截取异常");
        }
        return data;
    }

    /**
     * 字符串拼接
     *
     * @param format
     * @param data
     * @return
     */
    public static String stringFormat(String format, Object... data) {
        return String.format(format, data);
    }

    /**
     * 字符串拼接
     *
     * @param resId
     * @param data
     * @return
     */
    public static String stringFormat(@StringRes int resId, Object... data) {
        String model = getString(resId);
        return String.format(model, data);
    }

    /**
     * 获取字符串
     *
     * @param id
     * @return
     */
    private static String getString(@StringRes int id) {
        if (mContext == null) {
            return "";
        }
        return mContext.getString(id);
    }

    /**
     * 手机号用****号隐藏中间数字
     *
     * @param phone
     * @return
     */
    public static String hidePhone(String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() < 11) {
            return phone;
        }
        String newPhone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return newPhone;
    }
    public static String DoubletoString(double num) {
        try {
          return   String.valueOf(num);
        }catch (Exception e){
            return "";
        }
    }
    public static String getStarString2(String content, int frontNum, int endNum) {

        if (frontNum >= content.length() || frontNum < 0) {
            return content;
        }
        if (endNum >= content.length() || endNum < 0) {
            return content;
        }
        if (frontNum + endNum >= content.length()) {
            return content;
        }
        String starStr = "";
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, frontNum) + starStr
                + content.substring(content.length() - endNum, content.length());

    }


}

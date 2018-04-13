package com.mark.app.hjshop4a.common.valid;

import android.text.TextUtils;

import com.mark.app.hjshop4a.common.utils.NumParseUtils;


/**
 * 数据检测工具列
 * true：通过；false：不通过
 * Created by lenovo on 2017/10/30.
 */

public class CheckUtils {
    /**
     * 检测字符串非空
     *
     * @param data
     * @return
     */
    public static boolean checkString(String data) {
        return !TextUtils.isEmpty(data);
    }

    /**
     * 检查费用
     * 不位空且不为0才通过
     *
     * @param data
     * @return
     */
    public static boolean checkMoneyZero(String data) {
        if (!TextUtils.isEmpty(data)) {
            String after = data.replaceAll("[^0-9.]", "");
            double d = NumParseUtils.parseDouble(after);
            if (d != 0) {
                return true;
            }
        }
        return false;
    }
}

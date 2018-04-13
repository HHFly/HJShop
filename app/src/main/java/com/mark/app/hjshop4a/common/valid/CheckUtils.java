package com.mark.app.hjshop4a.common.valid;

import android.text.TextUtils;


import com.mark.app.hjshop4a.common.utils.NumberUtils;

import java.util.List;

/**
 * 检查数据工具类
 * true：通过；false：不通过
 * Created by lenovo on 2017/10/25.
 */

public class CheckUtils {
    /**
     * 检测列表是否有数据
     *
     * @return
     */
    public static boolean checkListNull(List data) {
        return data != null && data.size() != 0;
    }

    /**
     * 检查费用
     * 不位空且不为0才通过
     *
     * @param data
     * @return
     */
    public static boolean checkMoney(String data) {
        if (!TextUtils.isEmpty(data)) {
            String after = data.replaceAll("[^0-9.]", "");
            double d = NumberUtils.parseDouble(after);
            if (d != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查数值
     * 不位空且不为0才通过
     *
     * @param data
     * @return
     */
    public static boolean checkNumber(String data) {
        return !TextUtils.isEmpty(data)
                && !"0".equals(data);
    }
}

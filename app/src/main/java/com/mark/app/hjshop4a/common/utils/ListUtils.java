package com.mark.app.hjshop4a.common.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * 列表工具类
 * Created by lenovo on 2017/9/4.
 */

public class ListUtils {
    /**
     * 字符串列表转字符串隔开
     *
     * @param data
     * @param delimiter 分隔符
     * @return
     */
    public static String toString(List<String> data, String delimiter) {
        String result = "";
        if (data != null) {
            int count = data.size();
            for (int i = 0; i < count; i++) {
                result += data.get(i);
                if (i != count - 1) {
                    result += delimiter;
                }
            }
        }
        return result;
    }

    /**
     * 字符串列表转字符串隔开
     *
     * @param data
     * @param delimiter 分隔符
     * @return
     */
    public static String toStringUnEmpty(List<String> data, String delimiter) {
        String result = "";
        if (data != null) {
            int count = data.size();
            for (int i = 0; i < count; i++) {
                String item = data.get(i);
                if (!TextUtils.isEmpty(item)) {
                    result += data.get(i);
                    if (i != count - 1) {
                        result += delimiter;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 获取列表数量
     *
     * @param data
     * @return
     */
    public static int size(List data) {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    /**
     * 获取行数
     *
     * @param data      数据源
     * @param lineCount 一行多少个
     * @return
     */
    public static int getLine(List data, int lineCount) {
        if (data == null || lineCount < 0) {
            return 0;
        }
        if (lineCount == 0) {
            lineCount = 1;
        }
        int size = data.size();
        int line = size / lineCount;
        if (size % lineCount != 0) {
            line++;
        }
        return line;
    }

    /**
     * 获取指定项
     *
     * @param data
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T get(List<T> data, int index) {
        if (data == null) {
            return null;
        }
        int size = data.size();
        if (index >= 0 && index < size) {
            return data.get(index);
        }
        return null;
    }

    /**
     * 分割字符串
     *
     * @param data
     * @return
     */
    public static String[] split(String data) {
        if (data == null) {
            return new String[0];
        }
        return data.split(",");
    }
}

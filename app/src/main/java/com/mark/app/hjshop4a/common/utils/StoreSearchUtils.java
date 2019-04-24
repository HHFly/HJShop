package com.mark.app.hjshop4a.common.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能：搜索历史记录帮助类
 * 作者：林敬聚
 */

public class StoreSearchUtils {

    //搜索key
    public static final String KEY_SEARCH_HISTORY = "search_history";
    //分隔符
    public static final String STRING_SEPARATOR = ",";
    //没有条数限制
    public static final int VALUE_NO_MAX = -1;

    private SPUtil utils;

    public StoreSearchUtils(Context context) {
        utils = SPUtil.getInstance(context);
    }

    /**
     * 获取实例
     *
     * @param context
     * @return
     */
    public static StoreSearchUtils getInstance(Context context) {
        StoreSearchUtils utils = new StoreSearchUtils(context);
        return utils;
    }

    /**
     * 获取历史搜索记录
     *
     * @return
     */
    public List<String> getList() {
        return getList(VALUE_NO_MAX);
    }

    /**
     * 获取历史搜索记录
     *
     * @return
     */
    public List<String> getList(int showCount) {
        List<String> result = null;
        String history = utils.getString(KEY_SEARCH_HISTORY);
        if (!TextUtils.isEmpty(history)) {
            //如果记录不为空
            result = new ArrayList<>();
            String[] strs = history.split(STRING_SEPARATOR);
            if (strs != null) {
                int count = strs.length;
                if (showCount != VALUE_NO_MAX) {
                    count = Math.min(count, showCount);
                }
                for (int i = 0; i < count; i++) {
                    result.add(strs[i]);
                }
            }
        }
        return result;
    }

    /**
     * 往最前方添加搜索记录
     *
     * @param keyword
     */
    public void add(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            String history = "";
            List<String> historyList = getList(VALUE_NO_MAX);
            if (historyList != null) {
                if (historyList.contains(keyword)) {
                    historyList.remove(keyword);
                }
                historyList.add(0, keyword);
                history = toString(historyList);
            } else {
                history = keyword;
            }
            save(history);
        }
    }

    /**
     * 将List<String>转换为"1,2,3,4,5"
     *
     * @return
     */
    private String toString(List<String> data) {
        return toString(data, VALUE_NO_MAX);
    }

    /**
     * 将List<String>转换为"1,2,3,4,5"
     *
     * @return
     */
    private String toString(List<String> data, int maxCount) {
        if (data == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int count = data.size();
        if (maxCount != VALUE_NO_MAX) {
            count = Math.min(count, maxCount);
        }
        for (int i = 0; i < count; i++) {
            builder.append(data.get(i));
            if (i != count - 1) {
                builder.append(STRING_SEPARATOR);
            }
        }
        return builder.toString();
    }

    /**
     * 清空搜索记录
     */
    public void clear() {
        save("");
    }

    /**
     * 保存SharedPreferences
     *
     * @param msg
     */
    public void save(String msg) {
        utils.putString(KEY_SEARCH_HISTORY, msg);
    }
}

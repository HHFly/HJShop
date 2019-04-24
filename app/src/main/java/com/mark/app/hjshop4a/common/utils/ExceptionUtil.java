package com.mark.app.hjshop4a.common.utils;


import com.mark.app.hjshop4a.common.log.LogUtil;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static void throwable(Throwable t) {
        LogUtil.logE("ExceptionUtil", "throwable", t.toString());
    }
}

package com.mark.app.hjshop4a.common.utils;


import com.mark.app.hjshop4a.app.AppContext;

/**
 * Created by lenovo on 2017/10/30.
 */

public class TestUtils {
    public static void main() {
        if (AppContext.isDebudEnv()) {
//            test();
        }
    }

    public static void test() {
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws Exception {
        Double d = Double.parseDouble("1000,000.00");
        LogUtils.logFormat("TestUtils", "main", "");
    }

    public static void test2()throws Exception  {
        Double d = Double.parseDouble("1000000.00");
        LogUtils.logFormat("TestUtils", "main", "");
    }
}

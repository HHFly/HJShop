package com.mark.app.hjshop4a.app;


import com.mark.app.hjshop4a.BuildConfig;

/**
 * 配置文件变量
 * Created by lenovo on 2017/8/27.
 */

public class AppContext {


    public static int versionCode() {
        return BuildConfig.VERSION_CODE;
    }



    /**
     * 获取服务dizhi
     *
     * @return
     */
    /**
     * 获取服务dizhi
     *
     * @return
     */

    public static String getBaseUrl() {

        return BuildConfig.TEST_URL;
    }
    /**
     * 获取平台
     *
     * @return
     */
    public static String getPlatForm() {
        return BuildConfig.PLATFORM;
    }




    /**
     * 获取版本号
     *
     * @return
     */
    public static final String versionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 是否为测试环境
     *
     * @return
     */
    public static boolean isDebudEnv() {
        return BuildConfig.DEBUG_ENV;
    }

    /**
     * 是否开启日志打印
     *
     * @return
     */
    public static boolean isOpenLog() {
        return BuildConfig.IS_OPEN_LOG;
    }

    /**
     * 获取webview的UserAgentString
     *
     * @return
     */
    public static String getUserAgentString() {
        return BuildConfig.WEB_USER_AGENT;
    }

    /**
     * 获取与WEb交互的接口名
     *
     * @return
     */
    public static String getWebInterfaceName() {
        return BuildConfig.WEB_INTERFACE;
    }


}

package com.mark.app.hjshop4a.app;


import android.content.Context;
import android.text.TextUtils;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.model.login.model.LoginRepo;
import com.white.lib.utils.SPUtil;

/**
 * 配置文件变量
 * Created by lenovo on 2017/8/27.
 */

public class AppContext {
    final String KEY_SETTING = "setting";//设置
    final String KEY_TOKEN = "token";//

    Context mContext;//getApplicationContext

    LoginRepo mLoginRepo;//登录数据

    public AppContext(Context context) {
        mContext = context.getApplicationContext();
        init();
    }

    /**
     * 初始化
     */
    public void init() {


        //初始化登录数据
        LoginRepo loginRepo = getLoginRepo();
        LogUtils.logFormat(this, "init", "初始化登录数据" + JsonUtils.toJson(loginRepo));
    }


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
    /**
     * 获取token信息
     *
     * @return
     */
    public LoginRepo getLoginRepo() {
        if (mLoginRepo == null) {
            String json = SPUtil.getInstance(mContext).getString(KEY_TOKEN, "");
            if (TextUtils.isEmpty(json)) {
                mLoginRepo = new LoginRepo();
            } else {
                mLoginRepo = JsonUtils.fromJson(json, LoginRepo.class);
            }
        }
        return mLoginRepo;
    }
    /**
     * 更新token信息
     *
     * @param data
     */
    public void setLoginRepo(LoginRepo data) {
        mLoginRepo = data;
        if (data != null) {
            //当前时间
            long curTime = System.currentTimeMillis();
            //超时时间秒
            int expiresIn = data.getExpiresIn();
            long endTime = curTime + expiresIn * 1000l;
            data.setEndTime(endTime);
            SPUtil.getInstance(mContext).putString(KEY_TOKEN, data.toJson());
        } else {
            SPUtil.getInstance(mContext).putString(KEY_TOKEN, new LoginRepo().toJson());
        }
        LogUtils.logFormat(this, "setLoginRepo", "更新token信息" + JsonUtils.toJson(data));
    }

}

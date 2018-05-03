package com.mark.app.hjshop4a.app;


import android.content.Context;
import android.text.TextUtils;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.model.login.model.LoginRepo;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;
import com.white.lib.utils.SPUtil;

/**
 * 配置文件变量
 * Created by lenovo on 2017/8/27.
 */

public class AppContext {
    final String KEY_SETTING = "setting";//设置
    final String KEY_TOKEN = "token";//
    final String KEY_AUTOLOGIN ="autologin";//自动登录
    final  String KEY_ROLETYPE ="roletype";//角色
    final  String KEY_USERINFO ="userinfo";//个人信息
    Context mContext;//getApplicationContext

    LoginRepo mLoginRepo;//登录数据
    Boolean isAutoLogin ;//是否自动登录
    UserInfo mUserInfo;//角色数据

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
        UserInfo userInfo = getUserInfo();

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
     * 获取个人信息
     *
     * @return
     */
    public UserInfo getUserInfo() {
        if (mUserInfo == null) {
            String json = SPUtil.getInstance(mContext).getString(KEY_USERINFO, "");
            if (TextUtils.isEmpty(json)) {
                mUserInfo = new UserInfo();
            } else {
                mUserInfo = JsonUtils.fromJson(json, UserInfo.class);
            }
        }
        return mUserInfo;
    }

    /**
     * 更新个人信息
     *
     * @param data
     */
    public void setUserInfo(UserInfo data) {
        mUserInfo = data;
        if (data != null) {

            SPUtil.getInstance(mContext).putString(KEY_USERINFO, data.toJson());
        } else {
            SPUtil.getInstance(mContext).putString(KEY_USERINFO, new UserInfo().toJson());
        }
        LogUtils.logFormat(this, "setUserInfo", "更新UserInfo信息" + JsonUtils.toJson(data));
    }
    /**
     * 更新token信息
     *
     * @param data
     */
    public void setLoginRepo(LoginRepo data) {
        mLoginRepo = data;
        if (data != null&&getIsAutoLogin()) {
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

    /*
    * 是否自动登录
    * */
        public Boolean getIsAutoLogin(){
//          isAutoLogin= SPUtil.getInstance(mContext).getBoolean(KEY_AUTOLOGIN,false);
        return isAutoLogin;
        }


    public void setIsAutoLogin(Boolean autoLogin){
       isAutoLogin =autoLogin;
//        SPUtil.getInstance(mContext).putBoolean(KEY_AUTOLOGIN,isAutoLogin);
    }
    public int getRoleType(){
        UserInfo userInfo =getUserInfo();

        return Integer.parseInt(userInfo.getUserTypeId());
    }

//

}

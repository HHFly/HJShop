package com.mark.app.hjshop4a.app;


import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;

import com.mark.app.hjshop4a.common.PDLifecycleHandle;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.model.login.model.LoginRepo;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by zhuwh on 2018/4/10.
 */

public class
App extends Application {

    private static App s_app;

    public static App get() {
        return s_app;
    }

    private static AppService s_service;

    public static AppService getServiceManager(){
        return s_service;
    }
    private static AppContext mAppContext;
    public static AppContext getAppContext(){return mAppContext;}
    private Activity mCurActivity;



    @Override
    public void onCreate() {
        super.onCreate();
        s_app = this;
        //初始化服务
        s_service = new AppService(this);
        mAppContext =new AppContext(this);
        ToastUtils.init(this);
        //初始化Fresco
        FrescoUtils.initialize(this);
//        初始化二维码
        ZXingLibrary.initDisplayOpinion(this);
    }



    private void registerActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                mCurActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /**
     * 获取当前Activity
     * @param <T>
     * @return
     */
    public <T extends Activity> T getCurrentActivity() {
        return (T) mCurActivity;
    }

    /**
     * 获取当前Activity的fragmentManager
     * @return
     */
    public FragmentManager getFragmentManager() {
        if (mCurActivity == null) {
            throw new NullPointerException("mCurActivity is null");
        }
        return mCurActivity.getFragmentManager();
    }
    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        return App.get().getAccToken();
    }
    /**
     * 是否有token
     *
     * @return
     */
    public static boolean hasToken() {
//        boolean autologin =getAppContext().getIsAutoLogin();
//        if(autologin){
//            return !TextUtils.isEmpty(getToken());
//        }else {
//           return  false;
//        }
        return !TextUtils.isEmpty(getToken());
    }

    public String getAccToken() {
        LoginRepo data = getAppContext().getLoginRepo();
        return data == null ? "" : data.getAccessToken();
    }
    public void setLogin(LoginRepo login) {
        setLogin(login, true);
    }
    public void setLogin(LoginRepo login, boolean isStartService) {
        if (login != null) {
            getAppContext().setLoginRepo(login);
            //JPushUtils.setAlias(login.getUserPushToken());
        } else {
            //退出登录
            //JPushUtils.clearAlias();

            getAppContext().setLoginRepo(null);
        }
        if (isStartService) {
           // PullService.startService(this, login);
        }
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    public Activity getCurActivity() {
        return PDLifecycleHandle.currentActivity();
    }
}

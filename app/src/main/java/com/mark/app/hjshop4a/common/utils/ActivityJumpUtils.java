package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mark.app.hjshop4a.common.androidenum.login.LoginBackType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.androidenum.web.WebType;
import com.mark.app.hjshop4a.login.activity.ForgetActivity;
import com.mark.app.hjshop4a.login.activity.LoginActivity;
import com.mark.app.hjshop4a.login.activity.RegisterActivity;
import com.mark.app.hjshop4a.ui.web.WebActivity;


/**
 * Activity跳转工具类
 * Created by lenovo on 2017/8/27.
 */
public class ActivityJumpUtils {

    /**
     * 跳转到登陆
     *
     * @param act
     */
    public static void actLogin(Activity act) {
        actLogin(act, LoginBackType.NONE);
    }
    /**
     * 跳转到登陆
     *
     * @param act
     */
    public static void actLogin(Activity act, @LoginBackType int type) {
        Intent intent = new Intent(act, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        BundleUtils.getInstance().putInt(BundleKey.TYPE, type).addIntent(intent);
        act.startActivity(intent);
    }
    /**
     * 跳转到注册
     *
     * @param act
     */
    public static void actRegister(Activity act, String phone) {
        Intent intent = new Intent(act, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        BundleUtils.getInstance().putString(BundleKey.PHONE, phone).addIntent(intent);
        act.startActivity(intent);
    }
    /**
     * 跳转到忘记密码
     *
     * @param activity
     * @param phone
     */
    public static void actForgetPwd(Activity activity, String phone) {
        Intent intent = new Intent(activity, ForgetActivity.class);
        BundleUtils.getInstance().putString(BundleKey.PHONE, phone).addIntent(intent);
        activity.startActivity(intent);
    }
    /**
     * 跳转到网页
     *
     * @param act
     * @param url
     */
    public static void actWebActivity(Activity act, String url) {
        actWebActivity(act, url, "");
    }

    /**
     * 跳转到网页
     *
     * @param act
     * @param url
     * @param title
     */
    public static void actWebActivity(Activity act, String url, String title) {
        actWebActivity(act, url, title, WebType.NORMAL);
    }

    /**
     * 跳转到网页
     *
     * @param act
     * @param url
     * @param title
     */
    public static void actWebActivity(Activity act, String url, String title, @WebType int type) {
        LogUtils.logFormat("ActivityJumpUtils", "actWebActivity", "url:" + url);

        if (url.contains("://")) {
            if (PdUrlUtil.isPdAppUrl(url)) {
                //是panda内部链接
                if (PdUrlUtil.isSpecialList(url)) {
                    //是专题链接
                    long specialId = PdUrlUtil.getLongQueryParameter(url, PdUrlUtil.PARAMETER_KEY_SPECIAL_ID, 0);
                    if (specialId != 0) {
                    }
                }
                if (PdUrlUtil.isRedNewUserAct(url)) {

                }
            } else {

                Intent intent = new Intent(act, WebActivity.class);
                Bundle bundle = BundleUtils.getInstance()
                        .putString(BundleKey.URL, url)
                        .putString(BundleKey.TITLE, title)
                        .putInt(BundleKey.TYPE, type)
                        .getBundle();
                intent.putExtras(bundle);
                act.startActivity(intent);
            }
        }
    }
}

package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mark.app.hjshop4a.common.androidenum.login.LoginBackType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.androidenum.web.WebType;
import com.mark.app.hjshop4a.ui.assedetail.AssetDetailActivity;
import com.mark.app.hjshop4a.ui.bankcard.BankCardActivity;
import com.mark.app.hjshop4a.ui.businessapply.BusinessApplicationActivity;
import com.mark.app.hjshop4a.ui.consumptionbill.ConsumptionBillActivty;

import com.mark.app.hjshop4a.ui.login.activity.ForgetActivity;
import com.mark.app.hjshop4a.ui.login.activity.LoginActivity;
import com.mark.app.hjshop4a.ui.login.activity.LoginSwitchActivity;
import com.mark.app.hjshop4a.ui.login.activity.RegisterActivity;
import com.mark.app.hjshop4a.ui.about.AboutActivity;
import com.mark.app.hjshop4a.ui.onlinerecharge.OnlineRechargeActivity;
import com.mark.app.hjshop4a.ui.recommend.RecommendActivity;
import com.mark.app.hjshop4a.ui.userinfo.BasicInfoActivity;
import com.mark.app.hjshop4a.ui.userinfo.CertificationInfoActivity;
import com.mark.app.hjshop4a.ui.userinfo.ModifyPWActivity;
import com.mark.app.hjshop4a.ui.userinfo.UserInfoActivity;
import com.mark.app.hjshop4a.ui.web.WebActivity;


/**
 * Activity跳转工具类
 * Created by lenovo on 2017/8/27.
 */
public class ActivityJumpUtils {
    public  static void actActivity(Activity act,Class ca){
        Intent intent =new Intent(act,ca);
        act.startActivity(intent);
//        act.overridePendingTransition(0,0);
    }

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
     * 跳转到账号管理
     *
     * @param activity
     */
    public static void actLoginSwicth(Activity activity) {
        Intent intent = new Intent(activity, LoginSwitchActivity.class);

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
    /**
     * 跳转到关于
     *
     * @param activity

     */
    public static void actAbout(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到我的推荐
     *
     * @param activity

     */
    public static void actRecommend(Activity activity) {
        Intent intent = new Intent(activity, RecommendActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到个人信息
     *
     * @param activity

     */
    public static void actUserInfo(Activity activity) {
        Intent intent = new Intent(activity, UserInfoActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到基本信息
     *
     * @param activity

     */
    public static void actBasicInfo(Activity activity) {
        Intent intent = new Intent(activity, BasicInfoActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到认证资料
     *
     * @param activity

     */
    public static void actCertificationInfo(Activity activity) {
        Intent intent = new Intent(activity, CertificationInfoActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到设置密码
     *
     * @param activity

     */
    public static void actModifyPW(Activity activity) {
        Intent intent = new Intent(activity, ModifyPWActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到设置密码
     *
     * @param activity

     */
    public static void actBankCard(Activity activity) {
        Intent intent = new Intent(activity, BankCardActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到我的资产
     *
     * @param activity

     */
    public static void actAssetDetail(Activity activity,int role) {
        Intent intent = new Intent(activity, AssetDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.ROLE,role);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /*
    * 跳转在线充值
    * */
    public static void actOnlineRecharge(Activity activity,int role) {
        Intent intent = new Intent(activity, OnlineRechargeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.ROLE,role);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /*
    * 跳转消费账单
    * */
    public static void actConsumption(Activity activity) {
        Intent intent = new Intent(activity, ConsumptionBillActivty.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /*
  * 跳转消费账单
  * */
    public static void actBusinesApply(Activity activity) {
        Intent intent = new Intent(activity, BusinessApplicationActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
}

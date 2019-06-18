package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mark.app.hjshop4a.common.androidenum.homepager.HPTabType;
import com.mark.app.hjshop4a.common.androidenum.login.LoginBackType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.androidenum.web.WebType;
import com.mark.app.hjshop4a.ui.assedetail.AssetDetailActivity;
import com.mark.app.hjshop4a.ui.bankcard.activity.BankCardActivity;
import com.mark.app.hjshop4a.ui.businessapply.BusinessApplicationActivity;
import com.mark.app.hjshop4a.ui.calendarview.CalendarViewActivity;
import com.mark.app.hjshop4a.ui.consumptionbill.ConsumptionBillActivty;

import com.mark.app.hjshop4a.uinew.bindinfo.AddTaobaoActivity;
import com.mark.app.hjshop4a.uinew.bindinfo.BankCardAddActivity;
import com.mark.app.hjshop4a.uinew.bindinfo.BindIDActivity;
import com.mark.app.hjshop4a.uinew.bindinfo.BindInfoActivity;
import com.mark.app.hjshop4a.uinew.bindinfo.BindTaobaoActivity;
import com.mark.app.hjshop4a.uinew.homepager.activity.HomePagerActivity;
import com.mark.app.hjshop4a.ui.start.GuideActivity;
import com.mark.app.hjshop4a.uinew.login.activity.ForgetActivity;
import com.mark.app.hjshop4a.uinew.login.activity.LoginActivity;
import com.mark.app.hjshop4a.uinew.login.activity.PhoneActivity;
import com.mark.app.hjshop4a.uinew.login.activity.RegisterActivity;
import com.mark.app.hjshop4a.ui.about.AboutActivity;
import com.mark.app.hjshop4a.ui.onlinerecharge.OnlineRechargeActivity;
import com.mark.app.hjshop4a.ui.recommend.RecommendActivity;
import com.mark.app.hjshop4a.ui.userinfo.BasicInfoActivity;
import com.mark.app.hjshop4a.ui.userinfo.CertificationInfoActivity;
import com.mark.app.hjshop4a.uinew.performorder.PerformOrderActivity;
import com.mark.app.hjshop4a.uinew.userinfo.ModifyPWActivity;
import com.mark.app.hjshop4a.ui.userinfo.UserInfoActivity;
import com.mark.app.hjshop4a.ui.web.WebActivity;
import com.mark.app.hjshop4a.uinew.userinfo.SettingActivity;
import com.mark.app.hjshop4a.uinew.userinfo.VersionActivity;


/**
 * Activity跳转工具类
 * Created by lenovo on 2017/8/27.
 */
public class ActivityJumpUtils {
    public  static void actActivity(Activity act,Class ca){
        Intent intent =new Intent(act,ca);
        act.startActivity(intent);
        act.overridePendingTransition(0,0);
    }
    /**
     * 跳转到引导页
     *
     * @param act
     */
    public static void actGuideNORMAL(final Activity act) {
        Intent intent = new Intent(act, GuideActivity.class);
        act.startActivity(intent);
        ActAnimationUtils.actAlphaIn(act);
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
        act.overridePendingTransition(0,0);
    }
    /**
     * 跳转到手机登录
     *
     * @param act
     */
    public static void actPhoneLogin(Activity act) {
        Intent intent = new Intent(act, PhoneActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        act.startActivity(intent);
        act.overridePendingTransition(0,0);
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
        act.overridePendingTransition(0,0);
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
        activity.overridePendingTransition(0,0);

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
     * 跳转到关于
     *
     * @param activity

     */
    public static void actVersion(Activity activity) {
        Intent intent = new Intent(activity, VersionActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到我的推荐
     *
     * @param activity

     */
    public static void actRecommend(Activity activity,int Role) {
        Intent intent = new Intent(activity, RecommendActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.ROLE,Role);
        intent.putExtras(bundle);
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
    public static void actSetting(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /**
     * 跳转到设置密码
     *
     * @param activity

     */
    public static void actBankCard(Activity activity) {
        Intent intent = new Intent(activity, BankCardAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
    public static void actConsumption(Activity activity,int role) {
        Intent intent = new Intent(activity, ConsumptionBillActivty.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.ROLE,role);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }


    /*
  * 跳转商户申请
  * */

    public static void actBusinesApply(Activity activity) {
        Intent intent = new Intent(activity, BusinessApplicationActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    public static void actBusinesApply(Activity activity,long shopId) {
        Intent intent = new Intent(activity, BusinessApplicationActivity.class);
        BundleUtils.getInstance().putLong("shopID",shopId).addIntent(intent);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /*
  * 跳转时间筛选
  * */
    public static void actCalendarView(Activity activity,String title) {
        Intent intent = new Intent(activity, CalendarViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,1);
        activity.overridePendingTransition(0,0);
    }

    /**
     * 跳转到首页
     *
     * @param act
     */
    public static void actHomePager(Activity act) {
        actHomePager(act, HPTabType.HOME);
    }

    /**
     * 跳转到首页
     *
     * @param act
     * @param type
     */
    public static void actHomePager(Activity act, @HPTabType int type) {
        Intent intent = new Intent(act, HomePagerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.TYPE, type);
        intent.putExtras(bundle);
        act.startActivity(intent);
        act.overridePendingTransition(0,0);
    }
    //跳转绑定
    public static void actBind(Activity activity) {
        Intent intent = new Intent(activity, BindInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    //跳转绑定
    public static void actID(Activity activity) {
        Intent intent = new Intent(activity, BindIDActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    //跳转淘宝绑定

    public static void actTaoBao(Activity activity) {
        Intent intent = new Intent(activity, BindTaobaoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    /*
     * 跳转添加淘宝绑定
     * */
    public static void actAddTaobao(Activity activity,int role,long id) {
        Intent intent = new Intent(activity, AddTaobaoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.SHOWTYPE,role);
        bundle.putLong(BundleKey.ID,id);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }

    /*
     * 跳转添加淘宝绑定
     * */
    public static void actPerform(Activity activity,String subOrderSn,int  step) {
        Intent intent = new Intent(activity, PerformOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.ORDER_SN,subOrderSn);
        bundle.putInt(BundleKey.ID,step);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
}

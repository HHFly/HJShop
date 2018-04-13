package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;

import com.mark.app.hjshop4a.R;


/**
 * 界面切换动画
 * Created by lenovo on 2017/9/20.
 */

public class ActAnimationUtils {
    public static void actNone(Activity activity) {
        activity.overridePendingTransition(R.anim.activity_none, R.anim.activity_none);
    }
    /**
     * 跳转动画
     * 透明度动画:淡入
     *
     * @param activity
     */
    public static void actAlphaIn(Activity activity) {
        activity.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_none);
    }

    /**
     * 跳转动画
     * 透明度动画:淡出
     *
     * @param activity
     */
    public static void actAlphaOut(Activity activity) {
        activity.overridePendingTransition(R.anim.activity_none, R.anim.activity_alpha_out);
    }

}

package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.google.gson.JsonElement;
import com.mark.app.hjshop4a.common.androidenum.login.LoginBackType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.ui.LoginActivity;


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
}

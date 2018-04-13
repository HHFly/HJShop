package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mark.app.hjshop4a.base.model.BaseModel;


/**
 * bundle工具类
 * Created by lenovo on 2017/9/5.
 */

public class BundleUtils {

    //bundle
    private Bundle mBundle;

    public BundleUtils() {
        mBundle = new Bundle();
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static BundleUtils getInstance() {
        return new BundleUtils();
    }

    public static String getString(Intent intent, String key, String def) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                return bundle.getString(key, def);
            }
        }
        return def;
    }

    public static int getInt(Intent intent, String key, int def) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                return bundle.getInt(key, def);
            }
        }
        return def;
    }
    public static int getInt(Activity activity, String key, int def) {
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    return bundle.getInt(key, def);
                }
            }
        }
        return def;
    }
    public static <T> T getSerializable(Intent intent, String key) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                return (T) bundle.getSerializable(key);
            }
        }
        return null;
    }

    public BundleUtils putString(String key, String value) {
        mBundle.putString(key, value);
        return this;
    }

    public BundleUtils putInt(String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }

    public BundleUtils putLong(String key, long value) {
        mBundle.putLong(key, value);
        return this;
    }

    public BundleUtils putSerializable(String key, BaseModel value) {
        mBundle.putSerializable(key, value);
        return this;
    }

    public Bundle getBundle() {
        return mBundle;
    }

    public void addIntent(Intent intent) {
        intent.putExtras(getBundle());
    }
}

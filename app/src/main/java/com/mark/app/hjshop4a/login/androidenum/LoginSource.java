package com.mark.app.hjshop4a.login.androidenum;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 登陆界面来源
 * Created by lenovo on 2017/10/24.
 */

@IntDef({
        LoginSource.NORMAL,
        LoginSource.SPLASH
})
@Retention(RetentionPolicy.SOURCE)
public @interface LoginSource {
    /**
     * 启动页
     */
    int SPLASH = 1;
    /**
     * 接口2015
     */
    int NORMAL = 0;
}

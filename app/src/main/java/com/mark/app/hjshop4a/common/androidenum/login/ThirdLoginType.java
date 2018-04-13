package com.mark.app.hjshop4a.common.androidenum.login;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 第三方登陆类型
 * Created by lenovo on 2017/9/9.
 */

@StringDef({
        ThirdLoginType.WX
})
@Retention(RetentionPolicy.SOURCE)
public @interface ThirdLoginType {
    /**
     * 微信登陆
     */
    String WX="wx";
}

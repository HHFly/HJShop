package com.mark.app.hjshop4a.common.androidenum.login;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 登陆返回类型
 * Created by lenovo on 2017/10/9.
 */
@IntDef({
        LoginBackType.INDEX,
        LoginBackType.NONE
})
@Retention(RetentionPolicy.SOURCE)
public @interface LoginBackType {
    /**
     * 从不需要离开当前界面的地方来的
     */
    int NONE = 0;
    /**
     * 从需要回到首页的地方来的
     */
    int INDEX = 1;
}

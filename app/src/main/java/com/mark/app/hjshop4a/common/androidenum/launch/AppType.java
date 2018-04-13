package com.mark.app.hjshop4a.common.androidenum.launch;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 客户端类型
 * Created by lenovo on 2017/9/5.
 */
@IntDef({
        AppType.DELIVERY,
        AppType.STORE,
        AppType.USER
})
@Retention(RetentionPolicy.SOURCE)
public @interface AppType {
    /**
     * 用户端
     */
    int USER = 1;
    /**
     * 商家端
     */
    int STORE = 2;
    /**
     * 配送端
     */
    int DELIVERY = 3;
}

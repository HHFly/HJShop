package com.mark.app.hjshop4a.common.androidenum.web;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * web界面类型
 * Created by lenovo on 2017/9/1.
 */
@IntDef({
        WebType.NORMAL,
        WebType.ALPHA
})
@Retention(RetentionPolicy.SOURCE)
public @interface WebType {
    /**
     * 默认
     */
    int NORMAL = 0;
    /**
     * 标题栏透明的webfragment
     */
    int ALPHA = 1;
}

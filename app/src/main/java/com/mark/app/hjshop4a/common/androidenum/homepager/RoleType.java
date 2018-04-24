package com.mark.app.hjshop4a.common.androidenum.homepager;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 首页tab类型
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        RoleType.MEMBER,
        RoleType.BUSINESS,
        RoleType.AREAAGENT,
        RoleType.PROVINCIALAGENT
})
@Retention(RetentionPolicy.SOURCE)
public @interface RoleType {
    /**
     *会员
     */
    int MEMBER = 1;
    /**
     * 分类
     */
    int BUSINESS = 2;
    /**
     * 区代理
     */
    int AREAAGENT=3;

    /**
     * 省代理
     */
    int PROVINCIALAGENT = 4;
}

package com.mark.app.hjshop4a.common.androidenum.userinfo;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by pc on 2018/5/2.
 */
@IntDef({
        UserInfoType.BASIC,
        UserInfoType.CERTIFI,
        UserInfoType.CHANGEPSW,
        UserInfoType.HEADIMG,
        UserInfoType.PAYPSW
})
@Retention(RetentionPolicy.SOURCE)
public @interface UserInfoType {
    /**
     * 用户基本信息
     */
    int BASIC = 1;
    /**
     * 用户认证信息
     */
    int CERTIFI = 2;
    /**
     * 修改密码
     */
    int CHANGEPSW = 3;
    /**
     * 更换头像
     */
    int HEADIMG = 4;
    /**
     * z支付密码
     */
    int PAYPSW = 5;


}
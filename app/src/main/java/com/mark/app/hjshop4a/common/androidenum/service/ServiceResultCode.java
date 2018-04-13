package com.mark.app.hjshop4a.common.androidenum.service;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 接口返回code
 * Created by lenovo on 2017/8/29.
 */

@IntDef({
        ServiceResultCode.FAIL,
        ServiceResultCode.SUCCESS,
        ServiceResultCode.UN_LOGIN,
        ServiceResultCode.DATA_EMPTY,
        ServiceResultCode.OLDUSER,
        ServiceResultCode.UN_REDPACKET,
})
@Retention(RetentionPolicy.SOURCE)
public @interface ServiceResultCode {
    /**
     * 空数据
     */
    int DATA_EMPTY = 1012;
    /**
     * 成功
     */
    int SUCCESS = 1000;
    /**
     * 未登陆
     */
    int UN_LOGIN = 2015;
    /**
     * 失败
     */
    int FAIL = 9999;
    /*已是老用户
    * */
    int OLDUSER =300010;
    /*
    红包不存在
    * */
    int UN_REDPACKET =30005;
}

package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * startforresult的请求code
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        ActRequestCode.ADDRESS_MAP,
        ActRequestCode.NEW_ADDRESS,
        ActRequestCode.SELECT_ADDRESS,
        ActRequestCode.PAY,
        ActRequestCode.PERMISSION,
        ActRequestCode.USER_BIND_PHONE,
        ActRequestCode.ORDER_REMARK,
        ActRequestCode.ADDRESS_LIST,
        ActRequestCode.SAVE_CARD,
        ActRequestCode.PAY_CARD_LIST,
        ActRequestCode.SEARCH_ADDRESS,
        ActRequestCode.BIND_THIRD,
        ActRequestCode.RED_LIST,
        ActRequestCode.EXCHANGE_RED,
        ActRequestCode.STRIP_ACTIVITY
})
@Retention(RetentionPolicy.SOURCE)
public @interface ActRequestCode {
    /**
     * Strip支付
     */
    int STRIP_ACTIVITY = 116;
    /**
     * 兑换红包
     */
    int EXCHANGE_RED = 115;
    /**
     * 红包列表
     */
    int RED_LIST = 114;
    /**
     * 国别码选择
     */
    int COUNTRY_SELECT = 113;
    /**
     * 绑定第三方
     */
    int BIND_THIRD = 112;
    /**
     * 搜索地址界面
     */
    int SEARCH_ADDRESS = 111;
    /**
     * 信用卡列表
     */
    int PAY_CARD_LIST = 110;
    /**
     * 添加信用卡界面
     */
    int SAVE_CARD = 109;
    /**
     * 收货地址列表
     */
    int ADDRESS_LIST = 108;
    /**
     * 订单备注界面
     */
    int ORDER_REMARK = 107;
    /**
     * 绑定手机界面
     */
    int USER_BIND_PHONE = 106;
    /**
     * 权限申请界面
     */
    int PERMISSION = 105;
    /**
     * 跳转到支付界面
     */
    int PAY = 104;
    /**
     * 跳转到选择地址界面
     */
    int SELECT_ADDRESS = 103;
    /**
     * 跳转到新增地址
     */
    int NEW_ADDRESS = 102;
    /**
     * 跳转到地图
     */
    int ADDRESS_MAP = 101;
}

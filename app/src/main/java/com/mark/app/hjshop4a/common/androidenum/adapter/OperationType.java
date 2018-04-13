package com.mark.app.hjshop4a.common.androidenum.adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 适配器操作类型
 * Created by lenovo on 2017/8/31.
 */

@IntDef({
        OperationType.MODIFY,
        OperationType.DETAILS,
        OperationType.APPLY_RETURN,
        OperationType.CLASS_TAB,
        OperationType.BANNER_ITEM,
        OperationType.CANCEL_ORDER,
        OperationType.SELECT_ADDRESS,
        OperationType.LOCATION_AGAIN,
        OperationType.STORE,
        OperationType.PRODUCT,
        OperationType.PRODUCT_EXCHANGE,
        OperationType.MY_INTEGRAL,
        OperationType.EXCHANGE_RECORD
})
@Retention(RetentionPolicy.SOURCE)
public @interface OperationType {

    /**
     * 修改操作
     */
    int MODIFY = 0;
    /**
     * 跳转到详情
     */
    int DETAILS = 1;
    /**
     * 订单详情：申请退单
     */
    int APPLY_RETURN = 2;
    /**
     * 点击首页分类tab
     */
    int CLASS_TAB = 3;
    /**
     * 点击首页banner
     */
    int BANNER_ITEM = 4;
    /**
     * 取消订单
     */
    int CANCEL_ORDER = 5;
    /**
     * 选择地址
     */
    int SELECT_ADDRESS = 6;
    /**
     * 重新定位
     */
    int LOCATION_AGAIN = 7;
    /**
     * 点击店铺
     */
    int STORE = 8;
    /**
     * 点击商品
     */
    int PRODUCT = 9;
    /**
     * 点击商品兑换
     */
    int PRODUCT_EXCHANGE = 10;
    /**
     * 点击我的积分
     */
    int MY_INTEGRAL = 11;
    /**
     * 点击兑换记录
     */
    int EXCHANGE_RECORD = 12;
}

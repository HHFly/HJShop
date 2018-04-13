package com.mark.app.hjshop4a.common.androidenum.order;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 订单状态
 * Created by lenovo on 2017/9/14.
 */
@IntDef({
        OrderStatus.CANCELLED,
        OrderStatus.COMPLETED,
        OrderStatus.DELIVERY,
        OrderStatus.FOR_DISTRIBUTION,
        OrderStatus.SERVED,
        OrderStatus.TO_BE_DELIVERED,
        OrderStatus.TO_PAY,
        OrderStatus.WAIT_LIST,
        OrderStatus.PAYED,
        OrderStatus.APPLY_BACK_AUDIT,
        OrderStatus.APPLY_BACK_AUDIT_FAIL,
        OrderStatus.APPLY_BACK_AUDIT_SUCCESS,
        OrderStatus.APPLY_BACK_FAIL,
        OrderStatus.APPLY_BACK_IN,
        OrderStatus.APPLY_BACK_SUCCESS
})
@Retention(RetentionPolicy.SOURCE)
public @interface OrderStatus {
    /**
     * 待支付
     */
    int TO_PAY = 0;
    /**
     * 已支付
     */
    int PAYED = 1;
    /**
     * 待接单
     */
    int WAIT_LIST = 2;
    /**
     * 待配货
     */
    int FOR_DISTRIBUTION = 3;
    /**
     * 待配送
     */
    int TO_BE_DELIVERED = 4;
    /**
     * 配送中
     */
    int DELIVERY = 5;
    /**
     * 已送达
     */
    int SERVED = 6;
    /**
     * 已完成
     */
    int COMPLETED = 7;
    /**
     * 已取消
     */
    int CANCELLED = 8;
    /**
     * 申请退款中
     */
    int APPLY_BACK_IN = 9;
    /**
     * 申请退款成功
     */
    int APPLY_BACK_SUCCESS = 10;
    /**
     * 申请退款失败
     */
    int APPLY_BACK_FAIL = 11;
    /**
     * 申请退款审核中
     */
    int APPLY_BACK_AUDIT = 12;
    /**
     * 申请退款审核通过
     */
    int APPLY_BACK_AUDIT_SUCCESS = 13;
    /**
     * 申请退款审核驳回
     */
    int APPLY_BACK_AUDIT_FAIL = 14;
}

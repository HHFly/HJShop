package com.mark.app.hjshop4a.common.androidenum.pay;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * //支付结果状态
 */
@IntDef({PayResultStatus.PAY_STATE_SUCCESS,
        PayResultStatus.PAY_STATE_FAIL,
        PayResultStatus.PAY_STATE_CANCEL,
        PayResultStatus.PAY_STATE_ERROR
})
@Retention(RetentionPolicy.SOURCE)
public @interface PayResultStatus {
    /**
     * 支付成功
     */
    int PAY_STATE_SUCCESS = 1;
    /**
     * 支付失败
     */
    int PAY_STATE_FAIL = 2;
    /**
     * 支付取消
     */
    int PAY_STATE_CANCEL = 3;
    /**
     * 支付异常
     */
    int PAY_STATE_ERROR = 4;
}
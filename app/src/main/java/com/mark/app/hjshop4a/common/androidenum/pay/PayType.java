package com.mark.app.hjshop4a.common.androidenum.pay;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 支付类型
 * Created by lenovo on 2017/9/9.
 */
@IntDef({
        PayType.ALIPAY,
        PayType.WECHAT,
        PayType.STRIPE
})
@Retention(RetentionPolicy.SOURCE)
public @interface PayType {
    /**
     * 支付宝支付
     */
    int ALIPAY = 1;
    /**
     * 微信支付
     */
    int WECHAT = 2;
    /**
     * 新的支付方式
     */
    int STRIPE = 3;
}

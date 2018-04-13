package com.mark.app.hjshop4a.common.androidenum.pay;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 支付类型
 * Created by lenovo on 2017/9/9.
 */
@StringDef({
        PayStringType.WXPAY
})
@Retention(RetentionPolicy.SOURCE)
public @interface PayStringType {
    /**
     * 微信支付
     */
    String WXPAY = "wxPay";
}

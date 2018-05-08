package com.mark.app.hjshop4a.common.androidenum.consumptionbill;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 首页tab类型
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        BillTabType.GOLDBEAN,
        BillTabType.BALANCE,
        BillTabType.RECHARGE,
        BillTabType.BALANCEWITHDRAW,
        BillTabType.BEANTRADEIN,
        BillTabType.MEMBERTRADEIN

})
@Retention(RetentionPolicy.SOURCE)
public @interface BillTabType {
    /**
     *金豆消费
     */
    int GOLDBEAN = 0;
    /**
     * 余额消费
     */
    int BALANCE = 1;
    /**
     * 充值
     */
    int RECHARGE = 2;
    /*
    *
    * */
    int BALANCEWITHDRAW=4;
    /*
    *
    * */
    int BEANTRADEIN=3;

    int MEMBERTRADEIN=5;

}

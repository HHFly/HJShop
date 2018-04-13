package com.mark.app.hjshop4a.common.androidenum.order;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 订单详情tab类型
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        OrderDetailsTabType.PROGRESS,
        OrderDetailsTabType.DETAILS
})
@Retention(RetentionPolicy.SOURCE)
public @interface OrderDetailsTabType {
    /**
     * 订单进度
     */
    int PROGRESS = 1;
    /**
     * 订单详情
     */
    int DETAILS = 0;
}

package com.mark.app.hjshop4a.common.androidenum.order;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 订单tab类型
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        OrderTabType.WAIT,
        OrderTabType.FINISH
})
@Retention(RetentionPolicy.SOURCE)
public @interface OrderTabType {
    /**
     * 待处理
     */
    int WAIT = 100;
    /**
     * 已完成
     */
    int FINISH = 101;
}

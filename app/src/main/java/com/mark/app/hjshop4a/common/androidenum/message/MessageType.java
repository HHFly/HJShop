package com.mark.app.hjshop4a.common.androidenum.message;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 消息类型
 * Created by lenovo on 2017/9/30.
 */
@IntDef({
        MessageType.DELIVERY,
        MessageType.EVAL,
        MessageType.ORDER,
        MessageType.SYSTEM
})
@Retention(RetentionPolicy.SOURCE)
public @interface MessageType {
    /**
     * 订单
     */
    int ORDER = 0;
    /**
     * 评价
     */
    int EVAL = 1;
    /**
     * 配送
     */
    int DELIVERY = 2;
    /**
     * 系统
     */
    int SYSTEM = 3;
}

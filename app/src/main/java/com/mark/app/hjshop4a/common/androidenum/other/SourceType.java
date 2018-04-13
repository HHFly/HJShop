package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 来源类型
 * Created by lenovo on 2017/11/25.
 */
@IntDef({
        SourceType.NEED_GO_HOME,
        SourceType.NORMAL,
        SourceType.NEED_GO_ORDER_DETAILS
})
@Retention(RetentionPolicy.SOURCE)
public @interface SourceType {
    /**
     * 默认
     */
    int NORMAL = 0;
    /**
     * 从需要返回首页的地方来的
     */
    int NEED_GO_HOME = 1;
    /**
     * 从需要返回订单详情的地方来的
     */
    int NEED_GO_ORDER_DETAILS = 2;
}

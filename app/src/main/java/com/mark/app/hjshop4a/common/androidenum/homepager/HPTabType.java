package com.mark.app.hjshop4a.common.androidenum.homepager;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 首页tab类型
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        HPTabType.FIND,
        HPTabType.FOOD,
        HPTabType.ME,
        HPTabType.ORDER
})
@Retention(RetentionPolicy.SOURCE)
public @interface HPTabType {
    /**
     * 外卖
     */
    int FOOD = 0;
    /**
     * 发现
     */
    int FIND = 1;
    /**
     * 订单
     */
    int ORDER = 2;
    /**
     * 我的
     */
    int ME = 3;
}

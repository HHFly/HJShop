package com.mark.app.hjshop4a.common.androidenum.address;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 选择地址界面处于哪个状态
 * Created by lenovo on 2017/9/20.
 */

@IntDef({
        SelectAddressStatus.CITY,
        SelectAddressStatus.SEARCH,
        SelectAddressStatus.DELIVERY
})
@Retention(RetentionPolicy.SOURCE)
public @interface SelectAddressStatus {
    /**
     * 收货列表
     */
    int DELIVERY = 0;
    /**
     * 城市列表
     */
    int CITY = 1;
    /**
     * 搜索
     */
    int SEARCH = 2;
}

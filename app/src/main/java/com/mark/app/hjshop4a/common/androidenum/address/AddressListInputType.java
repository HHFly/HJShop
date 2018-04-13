package com.mark.app.hjshop4a.common.androidenum.address;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 收货地址列表进入类型
 * Created by lenovo on 2017/9/12.
 */
@IntDef({
        AddressListInputType.SELECT,
        AddressListInputType.FROM_MINE
})
@Retention(RetentionPolicy.SOURCE)
public @interface AddressListInputType {
    /**
     * 选择地址
     */
    int SELECT = 1;
    /**
     * 来自我的界面
     */
    int FROM_MINE = 2;
}

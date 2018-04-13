package com.mark.app.hjshop4a.common.androidenum.address;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 地址编辑页面进入类型
 * Created by lenovo on 2017/9/12.
 */
@IntDef({
        NewAddressInputType.ADD,
        NewAddressInputType.MODIFY,
        NewAddressInputType.DELETE
})
@Retention(RetentionPolicy.SOURCE)
public @interface NewAddressInputType {
    /**
     * 新增地址
     */
    int ADD = 1;
    /**
     * 修改地址
     */
    int MODIFY = 2;
    /**
     * 删除
     */
    int DELETE = 3;
}

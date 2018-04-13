package com.mark.app.hjshop4a.common.androidenum.address;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 性别类型
 * Created by lenovo on 2017/9/8.
 */
@IntDef({
        SexType.NAN,
        SexType.NV,
        SexType.OTHER
})
@Retention(RetentionPolicy.SOURCE)
public @interface SexType {
    /**
     * 男
     */
    int NAN = 1;
    /**
     * 女
     */
    int NV = 2;
    /**
     * 其他
     */
    int OTHER = 0;
}

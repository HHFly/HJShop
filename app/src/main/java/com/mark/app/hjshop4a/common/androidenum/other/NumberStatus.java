package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * int状态
 * Created by lenovo on 2017/9/2.
 */

@IntDef({
        NumberStatus.NUMBER_NULL
})
@Retention(RetentionPolicy.SOURCE)
public @interface NumberStatus {
    /**
     * 空
     */
    int NUMBER_NULL = -1;
}

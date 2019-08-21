package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 更新类型
 * Created by lenovo on 2017/11/14.
 */

@IntDef({
        UpdateType.CAN_UPDATE,
        UpdateType.MUST_UPDATE,
        UpdateType.UN_UPDATE
})
@Retention(RetentionPolicy.SOURCE)
public @interface UpdateType {
    /**
     * 必须更新
     */
    int MUST_UPDATE = 2;
    /**
     * 非必需更新
     */
    int CAN_UPDATE = 1;
    /**
     * 不更新
     */
    int UN_UPDATE = 0;
}

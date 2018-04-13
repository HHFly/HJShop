package com.mark.app.hjshop4a.common.androidenum.homepager;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 功能：版本检测是否强制更新的更新类型
 * 是否强制更新1：是，2无新版本不更新，3：非强制更新
 */
@IntDef({
        LookupType.FORCE_UPDATE,
        LookupType.UN_UPDATE,
        LookupType.UN_FORCE_UPDATE})
@Retention(RetentionPolicy.SOURCE)

public @interface LookupType {
    /**
     * 强制更新
     */
    int FORCE_UPDATE = 2;
    /**
     * 不更新
     */
    int UN_UPDATE = 1;
    /**
     * 非强制更新
     */
    int UN_FORCE_UPDATE = 3;
}
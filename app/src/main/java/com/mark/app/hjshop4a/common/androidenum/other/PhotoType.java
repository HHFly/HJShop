package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 获取照片方式
 * Created by lenovo on 2017/9/6.
 */
@IntDef({
        PhotoType.ALBUM,
        PhotoType.CAMERA
})
@Retention(RetentionPolicy.SOURCE)
public @interface PhotoType {
    /**
     * 相册
     */
    int ALBUM = 0;
    /**
     * 相机
     */
    int CAMERA = 1;
}

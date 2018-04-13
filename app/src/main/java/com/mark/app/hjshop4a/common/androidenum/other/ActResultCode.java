package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * startforresult的返回的code
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        ActResultCode.RESULT_OK
})
@Retention(RetentionPolicy.SOURCE)
public @interface ActResultCode {
    /**
     * 成功
     */
    int RESULT_OK = 20001;
}

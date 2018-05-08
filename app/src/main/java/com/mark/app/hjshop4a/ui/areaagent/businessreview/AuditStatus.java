package com.mark.app.hjshop4a.ui.areaagent.businessreview;

import android.support.annotation.IntDef;

/**
 * Created by pc on 2018/5/8.
 */
@IntDef({
        AuditStatus.NOREVIEW,
        AuditStatus.INREVIEW,
        AuditStatus.PASS,
        AuditStatus.UNPASS
})
public @interface AuditStatus {
    int NOREVIEW =0;//待审核
    int INREVIEW =1;//审核中
    int PASS  =2;//审核通过
    int UNPASS=3;//审核不通过
}

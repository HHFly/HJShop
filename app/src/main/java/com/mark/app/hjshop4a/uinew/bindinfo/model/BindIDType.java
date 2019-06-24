package com.mark.app.hjshop4a.uinew.bindinfo.model;

import android.support.annotation.IntDef;

@IntDef({
        BindIDType.unupload,
        BindIDType.WAITCONFIRM,
        BindIDType.Verified,
        BindIDType.FailureAudit
})
public @interface BindIDType {
    /*身份绑定状态 -1待上传 0待审核 1审核通过 2审核失败*/
    int  unupload =-1;


    int WAITCONFIRM=0;
    int Verified=1;
 int  FailureAudit=2;
}

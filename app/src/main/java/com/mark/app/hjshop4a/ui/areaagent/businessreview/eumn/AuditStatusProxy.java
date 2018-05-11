package com.mark.app.hjshop4a.ui.areaagent.businessreview.eumn;

import android.support.annotation.IntDef;

/**
 * Created by pc on 2018/5/8.
 */

@IntDef({
        AuditStatusProxy.NOREVIEW,
        AuditStatusProxy.PASS,
        AuditStatusProxy.UNPASS
})
public @interface AuditStatusProxy {
    int NOREVIEW =0;
    int PASS =1;
    int UNPASS=2;
}

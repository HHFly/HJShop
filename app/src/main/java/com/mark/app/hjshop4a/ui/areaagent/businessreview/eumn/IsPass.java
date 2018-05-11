package com.mark.app.hjshop4a.ui.areaagent.businessreview.eumn;

import android.support.annotation.IntDef;

/**
 * Created by pc on 2018/5/8.
 */
@IntDef({
        IsPass.YES,
        IsPass.NO
})
public @interface IsPass {
    int YES =1;
    int NO =2;
}

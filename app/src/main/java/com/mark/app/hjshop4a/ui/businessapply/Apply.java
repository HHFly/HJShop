package com.mark.app.hjshop4a.ui.businessapply;

import android.support.annotation.IntDef;

/**
 * Created by hui on 2018/6/17.
 */
@IntDef({
        Apply.ToBeAudited,
        Apply.Audit,
        Apply.PASS,
        Apply.UNPASS
})
public @interface Apply {
    int ToBeAudited = 0;//待审核
    int Audit=1;//审核中
    int PASS =2;//审核通过
    int UNPASS =3;//审核不通过
}

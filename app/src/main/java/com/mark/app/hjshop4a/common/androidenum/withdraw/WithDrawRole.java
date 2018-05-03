package com.mark.app.hjshop4a.common.androidenum.withdraw;

import android.support.annotation.IntDef;

import com.mark.app.hjshop4a.common.androidenum.web.WebType;

/**
 * Created by pc on 2018/5/3.
 */
@IntDef({
        WithDrawRole.BUSNIESS,
        WithDrawRole.AREAGENT,
        WithDrawRole.PAREAGENT
})
public @interface WithDrawRole {
    /**
     * 商户
     */
    int BUSNIESS = 1;
    /*
    *代理商（市） */
    int AREAGENT =2;
    /*代理商（省）*/
    int PAREAGENT =3;
}

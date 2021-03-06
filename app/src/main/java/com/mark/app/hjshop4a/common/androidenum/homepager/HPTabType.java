package com.mark.app.hjshop4a.common.androidenum.homepager;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 首页tab类型
 * Created by lenovo on 2017/8/27.
 */

@IntDef({
        HPTabType.HOME,
        HPTabType.ORDERLIST,
        HPTabType.ME,
        HPTabType.ORDER,
        HPTabType.SERVICE

})
@Retention(RetentionPolicy.SOURCE)
public @interface HPTabType {
    /**
     *首页
     */
    int HOME = 0;
    /**
     * 分类
     */
    int ORDERLIST = 1;
    /**
     * 购物车
     */
    int ORDER = 2;
    /**
     * 我的
     */
    int ME = 3;
    /*
    * 客服
    * */
    int SERVICE=4;
}

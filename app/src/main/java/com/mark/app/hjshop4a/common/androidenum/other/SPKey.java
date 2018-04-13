package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SharedPreferences键值
 * Created by lenovo on 2017/9/14.
 */
@StringDef({
        SPKey.TOKEN,
        SPKey.JPUSH_ALIAS,
        SPKey.JPUSH_ALIAS_SETTING_STATUS
})
@Retention(RetentionPolicy.SOURCE)
public @interface SPKey {
    /**
     * token
     */
    String TOKEN = "token";
    /**
     * 当前绑定的极光推送别名
     */
    String JPUSH_ALIAS = "alias";
    /**
     * 服务器绑定别名状态
     */
    String JPUSH_ALIAS_SETTING_STATUS = "server_alias_status";
}

package com.mark.app.hjshop4a.app;

import android.support.multidex.MultiDexApplication;

/**
 * Created by zhuwh on 2018/4/10.
 */

public class App extends MultiDexApplication {
    private static App mApp;

    /**
     * 获取Application实例
     *
     * @return
     */
    public static App getInstance() {
        return mApp;
    }
}

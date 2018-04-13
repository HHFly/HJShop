package com.mark.app.hjshop4a.data.help;


import com.mark.app.hjshop4a.data.entity.BaseResultEntity;

import io.reactivex.annotations.NonNull;

/**
 * 服务监听
 * Created by lenovo on 2017/10/6.
 */

public interface ServiceListener<T> {
    /**
     * 成功：返回值为1000
     */
    void onSuccess(@NonNull BaseResultEntity<T> obj);

    /**
     * 其他：返回值除了1000
     *
     * @return 是否拦截该返回值对应的默认处理方式
     */
    boolean onOther(@NonNull BaseResultEntity<T> obj);

    /**
     * 返回值为1000,的finish
     */
    void onSuccessFinish();

    /**
     * 返回值不为1000,的finish
     */
    void onUnSuccessFinish();

    void onAllFinish();
}

package com.mark.app.hjshop4a.data.help;

import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;

import io.reactivex.annotations.NonNull;

public interface RainbowServiceListener<T> {
    /**
     * 成功：返回值为1000
     */
    void onSuccess(@NonNull RainbowResultEntity<T> obj);

    /**
     * 其他：返回值除了1000
     *
     * @return 是否拦截该返回值对应的默认处理方式
     */
    boolean onOther(@NonNull RainbowResultEntity<T> obj);

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

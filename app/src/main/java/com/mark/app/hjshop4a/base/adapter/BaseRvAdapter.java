package com.mark.app.hjshop4a.base.adapter;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mark.app.hjshop4a.app.App;

import java.util.List;

/**
 * RecyclerAdapter的基类
 */

public abstract class BaseRvAdapter extends RecyclerView.Adapter {

    /**
     * Application上下文
     */
    private Context mContext;

    public BaseRvAdapter() {
        mContext = App.get();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return customOnCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        customBindView((AutoViewHolder) holder, position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            customBindLocalRefresh((AutoViewHolder) holder, position, payloads);
        }
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract AutoViewHolder customOnCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定view
     *
     * @param holder
     * @param position
     */
    public abstract void customBindView(AutoViewHolder holder, int position);

    /**
     * 局部刷新
     *
     * @param holder
     * @param position
     */
    public abstract void customBindLocalRefresh(AutoViewHolder holder, int position, List payloads);

    /**
     * 获取字符串资源
     *
     * @param resId
     * @return
     */
    public String getString(@StringRes int resId) {
        return getContext().getString(resId);
    }

    /**
     * 获取颜色资源
     *
     * @param resId
     * @return
     */
    public int getColor(@ColorRes int resId) {
        return getContext().getResources().getColor(resId);
    }

    /**
     * 获取Application上下文
     *
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 创建实例
     *
     * @param parent
     * @param resId
     * @return
     */
    public View inflater(ViewGroup parent, @LayoutRes int resId) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

}

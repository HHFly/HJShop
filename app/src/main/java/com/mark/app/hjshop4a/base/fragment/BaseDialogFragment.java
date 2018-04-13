package com.mark.app.hjshop4a.base.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;

/**
 * 对话框基类
 * Created by lenovo on 2017/8/27.
 */

public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private boolean isCanceledOnTouchOutside = true;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //隐藏对话框默认标题
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //对话框背景透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置弹窗动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.BaseDialogAnim;
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        return dialog;
    }

    /**
     * 设置点击外面对话框会不会消失
     *
     * @param canceledOnTouchOutside
     */
    public BaseDialogFragment setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        isCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    /**
     * 显示弹窗
     *
     * @param manager
     */
    public void show(FragmentManager manager) {
        try {
            show(manager, TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取实例
     *
     * @param rootView
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(View rootView, @IdRes int id) {
        return (T) rootView.findViewById(id);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 资源为空
     */
    protected final int RES_ID_NULL = -1;

    /**
     * 设置文本
     *
     * @param view
     * @param resId
     */
    public void setText(View view, @StringRes int resId) {
        if (view != null && view instanceof TextView && resId != RES_ID_NULL) {
            ((TextView) view).setText(resId);
        }
    }

    /**
     * 设置文本
     *
     * @param view
     * @param data
     */
    public void setText(View view, String data) {
        if (view != null && view instanceof TextView) {
            ((TextView) view).setText(data);
        }
    }

    /**
     * 资源id是否为空
     *
     * @param resId
     * @return
     */
    public boolean isResIdNull(int resId) {
        return resId == RES_ID_NULL;
    }

    /**
     * 设置是否显示
     *
     * @param view
     * @param isVisibility
     */
    public void setVisibility(View view, boolean isVisibility) {
        if (view != null) {
            view.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置监听
     *
     * @param view
     * @param listener
     */
    public void setListener(View view, View.OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 获取字符串
     *
     * @param resId
     * @return
     */
    public String getStringApp(@StringRes int resId) {
        return App.get().getString(resId);
    }

    /**
     * 设置文案
     *
     * @param rootView
     * @param id
     * @param data
     */
    public void setTvText(View rootView, @IdRes int id, String data) {
        if (rootView != null) {
            View view = getView(rootView, id);
            if (view != null && view instanceof TextView) {
                TextView tv = (TextView) view;
                tv.setText(data);
            }
        }
    }
}

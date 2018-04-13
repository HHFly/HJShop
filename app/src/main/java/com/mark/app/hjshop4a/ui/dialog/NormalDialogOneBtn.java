package com.mark.app.hjshop4a.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;


/**
 * 提示弹窗
 * Created by lenovo on 2017/8/27.
 */

public class NormalDialogOneBtn extends NormalDialog {
    private String mContentStr;
    private String mRightBtnTextStr;

    //按钮监听
    private View.OnClickListener onRightBtnClickListener;

    //是否初始化完毕
    private boolean isInitFinish = false;
    //是否响应点击监听
    private boolean isUseClickListener = true;
    //点击按钮是是否自动调用dismiss
    private boolean isAutoUseDismiss = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_normal_one_btn, container, false);
        TextView tvContent = getView(rootView, R.id.normal_tv_content);
        Button btnRight = getView(rootView, R.id.normal_btn);

        //设置标题
//        setText(tvTitle, mTitleStr);

        //设置内容
        setText(tvContent, mContentStr);

        //设置按钮文本
        if (!TextUtils.isEmpty(mRightBtnTextStr)) {
            setText(btnRight, mRightBtnTextStr);
        }

        //设置按钮监听
        setListener(btnRight, this);
        isInitFinish = true;
        return rootView;
    }

    /**
     * 设置右侧按钮文本
     *
     * @param rightBtnResId
     * @return
     */
    public NormalDialogOneBtn setBtnText(@StringRes int rightBtnResId) {
        mRightBtnTextStr = getStringApp(rightBtnResId);
        if (isInitFinish) {
            setTvText(getView(), R.id.normal_btn, mRightBtnTextStr);
        }
        return this;
    }

    /**
     * 设置右侧按钮文本
     *
     * @return
     */
    public NormalDialogOneBtn setBtnText(String str) {
        mRightBtnTextStr = str;
        return this;
    }

    /**
     * 设置内容
     *
     * @param resId
     * @return
     */
    public NormalDialogOneBtn setContentText(@StringRes int resId) {
        mContentStr = getStringApp(resId);
        return this;
    }

    /**
     * 设置内容
     *
     * @param resId
     * @return
     */
    public NormalDialogOneBtn setContentText(String resId) {
        mContentStr = resId;
        if (isInitFinish) {
            setTvText(getView(), R.id.normal_tv_content, resId);
        }
        return this;
    }

    /**
     * 设置右边按钮点击监听
     *
     * @param listener
     * @return
     */
    public NormalDialogOneBtn setRightBtnClickListener(View.OnClickListener listener) {
        onRightBtnClickListener = listener;
        return this;
    }

    /**
     * 设置右边按钮点击监听
     *
     * @param flag
     * @return
     */
    public NormalDialogOneBtn setUseClickListener(boolean flag) {
        isUseClickListener = flag;
        return this;
    }

    public NormalDialogOneBtn setAutoUseDismiss(boolean autoUseDismiss) {
        isAutoUseDismiss = autoUseDismiss;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_btn: {
                if (isUseClickListener) {
                    if (onRightBtnClickListener != null) {
                        onRightBtnClickListener.onClick(v);
                    }
                    if (onDialogListener != null) {
                        onDialogListener.onRightBtnClick();
                    }
                    if (isAutoUseDismiss) {
                        dismiss();
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDialogListener != null) {
            onDialogListener.onDismiss();
        }
    }

    private OnDialogListener onDialogListener;

    public NormalDialogOneBtn setOnDialogListener(OnDialogListener listener) {
        onDialogListener = listener;
        return this;
    }

    public interface OnDialogListener {
        void onRightBtnClick();

        void onDismiss();
    }

    public static class DefOnDialogListener implements OnDialogListener {
        @Override
        public void onRightBtnClick() {

        }

        @Override
        public void onDismiss() {

        }
    }
}

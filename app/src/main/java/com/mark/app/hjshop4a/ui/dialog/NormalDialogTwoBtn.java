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

public class NormalDialogTwoBtn extends NormalDialog {
    private String mContentStr;
    private String mLeftBtnTextStr;
    private String mRightBtnTextStr;

    //按钮监听
    private View.OnClickListener onLeftBtnClickListener;
    private View.OnClickListener onRightBtnClickListener;

    //点击按钮是是否自动调用dismiss
    private boolean isAutoUseDismiss = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_normal_two_btn, container, false);
        TextView tvContent = getView(rootView, R.id.normal_tv_content);
        Button btnLeft = getView(rootView, R.id.normal_btn_left);
        Button btnRight = getView(rootView, R.id.normal_btn_right);

        //设置内容
        setText(tvContent, mContentStr);

        //设置按钮文本
        if (!TextUtils.isEmpty(mLeftBtnTextStr)) {
            setText(btnLeft, mLeftBtnTextStr);
        }
        if (!TextUtils.isEmpty(mRightBtnTextStr)) {
            setText(btnRight, mRightBtnTextStr);
        }

        //设置按钮监听
        setListener(btnLeft, this);
        setListener(btnRight, this);
        return rootView;
    }


    public void setAutoUseDismiss(boolean autoUseDismiss) {
        isAutoUseDismiss = autoUseDismiss;
    }

    /**
     * 设置按钮文本
     *
     * @param leftBtnResId
     * @param rightBtnResId
     * @return
     */
    public NormalDialogTwoBtn setBtnText(@StringRes int leftBtnResId, @StringRes int rightBtnResId) {
        setLeftBtnText(leftBtnResId);
        setRightBtnText(rightBtnResId);
        return this;
    }

    /**
     * 设置左侧按钮文本
     *
     * @param leftBtnResId
     * @return
     */
    public NormalDialogTwoBtn setLeftBtnText(@StringRes int leftBtnResId) {
        mLeftBtnTextStr = getStringApp(leftBtnResId);
        return this;
    }

    @Override
    public NormalDialogTwoBtn setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        return (NormalDialogTwoBtn) super.setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    /**
     * 设置右侧按钮文本
     *
     * @param rightBtnResId
     * @return
     */
    public NormalDialogTwoBtn setRightBtnText(@StringRes int rightBtnResId) {
        mRightBtnTextStr = getStringApp(rightBtnResId);
        return this;
    }

    /**
     * 设置内容
     *
     * @param resId
     * @return
     */
    public NormalDialogTwoBtn setContentText(@StringRes int resId) {
        mContentStr = getStringApp(resId);
        return this;
    }

    /**
     * 设置内容
     *
     * @return
     */
    public NormalDialogTwoBtn setContentText(String str) {
        mContentStr = str;
        return this;
    }

    /**
     * 设置左侧按钮点击监听
     *
     * @param listener
     * @return
     */
    public NormalDialogTwoBtn setLeftBtnClickListener(View.OnClickListener listener) {
        onLeftBtnClickListener = listener;
        return this;
    }

    /**
     * 设置右边按钮点击监听
     *
     * @param listener
     * @return
     */
    public NormalDialogTwoBtn setRightBtnClickListener(View.OnClickListener listener) {
        onRightBtnClickListener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_btn_left: {
                if (onLeftBtnClickListener != null) {
                    onLeftBtnClickListener.onClick(v);
                }
                if (onDialogListener != null) {
                    onDialogListener.onLeftBtnClick();
                }
                dismiss();
                break;
            }
            case R.id.normal_btn_right: {
                if (onRightBtnClickListener != null) {
                    onRightBtnClickListener.onClick(v);
                }
                if (onDialogListener != null) {
                    onDialogListener.onRightBtnClick();
                }
                if (isAutoUseDismiss) {
                    dismiss();
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

    public NormalDialogTwoBtn setOnDialogListener(OnDialogListener listener) {
        onDialogListener = listener;
        return this;
    }

    public interface OnDialogListener {
        void onLeftBtnClick();

        void onRightBtnClick();

        void onDismiss();
    }

    public static class DefOnDialogListener implements OnDialogListener {

        @Override
        public void onLeftBtnClick() {

        }

        @Override
        public void onRightBtnClick() {

        }

        @Override
        public void onDismiss() {

        }
    }
}

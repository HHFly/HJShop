package com.mark.app.hjshop4a.ui.dialog;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;


/**
 * 包含一个输入框的对话框
 * Created by lenovo on 2017/9/5.
 */

public class AddOneEtParamDialog extends BaseDialogFragment {

    //根布局
    View mRootView;

    //参数名
    @StringRes
    int mParamName;
    //空提示
    @StringRes
    int mEmptyParamHint;

    /**
     * 获取实例
     *
     * @return
     */
    public static AddOneEtParamDialog getInstance() {
        AddOneEtParamDialog dialog = new AddOneEtParamDialog();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_add_one_param, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTvText(mRootView, R.id.et_value, "");
    }

    /**
     * 初始化
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mRootView = rootView;

        //设置参数名
//        setTvText(rootView, R.id.tv_title, mParamName);

        //设置输入框
        EditText etValue = getView(rootView, R.id.et_value);

        EditTextUtils.openKeyBroad(etValue);
        etValue.setText("");
        if (mEmptyParamHint != 0) {
            etValue.setHint(mEmptyParamHint);
        }

        View viewCancel = getView(rootView, R.id.normal_btn_left);
        View viewCommit = getView(rootView, R.id.normal_btn_right);
        setListener(viewCancel, this);
        setListener(viewCommit, this);
    }
/*
*设置输入数字
* s*/
public  void InputNumber(boolean IsNumber){
    if (IsNumber==true){
        EditText etValue = getView(mRootView, R.id.et_value);
            etValue.setInputType(InputType.TYPE_CLASS_PHONE);
    }
}
    /**
     * 设置参数名称
     *
     * @param data
     * @return
     */
    public AddOneEtParamDialog setTvParamName(@StringRes int data) {
        mParamName = data;
        //设置参数名
//        setTvText(mRootView, R.id.tv_title, mParamName);
        return this;
    }

    /**
     * 设置空参数提示
     *
     * @param data
     * @return
     */
    public AddOneEtParamDialog setTvEmptyParamHint(@StringRes int data) {
        mEmptyParamHint = data;
        //设置hint
        setTvHint(mRootView, R.id.et_value, mEmptyParamHint);
        return this;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.normal_btn_left: {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickCancel(this);
                }
                break;
            }
            case R.id.normal_btn_right: {
                String data = getCurInput();
                if (data != null) {
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onClickCommit(this, data);
                    }
                }
                break;
            }
        }
    }

    private <T extends View> T getView(@IdRes int id) {
        if (mRootView != null) {
            return (T) mRootView.findViewById(id);
        }
        return null;
    }

    /**
     * 获取用户当前输入
     *
     * @return
     */
    private String getCurInput() {
        String result = null;

        EditText etValue = getView(R.id.et_value);

        String strValue = etValue.getText().toString();

        if (TextUtils.isEmpty(strValue)) {
            if (mEmptyParamHint != 0) {
                ToastUtils.show(mEmptyParamHint);
            }
        } else {
            result = strValue;
        }
        return result;
    }

    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        onDialogClickListener = listener;
    }

    public interface OnDialogClickListener {
        void onClickCancel(AddOneEtParamDialog dialog);

        void onClickCommit(AddOneEtParamDialog dialog, String data);
    }

    public static class DefOnDialogClickListener implements OnDialogClickListener {
        @Override
        public void onClickCancel(AddOneEtParamDialog dialog) {
            dialog.dismiss();
        }

        @Override
        public void onClickCommit(AddOneEtParamDialog dialog, String data) {
            dialog.dismiss();
        }
    }
}

package com.mark.app.hjshop4a.uinew.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;

public class PswDialog  extends BaseDialogFragment {
    //根布局
    View mRootView;
    //空提示
    @StringRes
    int mEmptyParamHint;
    //参数名
    @StringRes
    int mParamName;
    String mData ;

    public static PswDialog getInstance() {
        PswDialog dialog = new PswDialog();
        return dialog;
    }

    public static PswDialog getInstance(int hint, int title) {
        PswDialog dialog = new PswDialog();
        dialog.mParamName =title;
        dialog.mEmptyParamHint =hint;
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.dialog_psw, container, false);

        initView(rootView);
        return rootView;
    }

    public void setEtValue(String data){
        mData =data;
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
//        setTvText(mRootView, R.id.et_value, mData);
        //设置输入框
        EditText etValue = getView(rootView, R.id.et_value);

        EditTextUtils.openKeyBroad(etValue);
//        etValue.setText("");
        if (mEmptyParamHint != 0) {
            etValue.setHint(mEmptyParamHint);
        }
        int i =etValue.getText().length();
        etValue.setSelection(etValue.getText().length());
        View viewCancel = getView(rootView, R.id.normal_btn_left);
        View viewCommit = getView(rootView, R.id.normal_btn_right);
        setListener(viewCancel, this);
        setListener(viewCommit, this);
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.normal_btn_left: {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickCancel(this);
                }
                dismiss();
                break;
            }
            case R.id.normal_btn_right: {
                String data = getCurInput();
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onClickCommit(this, data);
                    }

                break;
            }
        }
    }
    /**
     * 获取用户当前输入
     *
     * @return
     */
    private String getCurInput() {
        String result = null;

        EditText etValue = getView(mRootView, R.id.et_value);
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
        void onClickCancel(PswDialog dialog);

        void onClickCommit(PswDialog dialog, String data);
    }


}

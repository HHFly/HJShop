package com.mark.app.hjshop4a.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;

/**
 * Created by pc on 2018/4/16.
 */

public class SexDialog extends BaseDialogFragment {
    //根布局
    View mRootView;
    Context content ;
    int Sex;
    public void setContent(Context content) {
        this.content = content;
    }

    @Override
    protected boolean getStartInBottom() {
        return true;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_select_sex, container, false);
        final AlertDialog dialog = new AlertDialog.Builder(content, R.style.dialog_lhp )
                .create();

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.mystyle);
        initView(rootView);
        return rootView;
    }
    /**
     * 初始化
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mRootView = rootView;
        View viewno  =getView(rootView,R.id.sex_no);
        View viewman = getView(rootView, R.id.sex_man);
        View viewwoman = getView(rootView, R.id.sex_woman);
        setListener(viewno, this);
        setListener(viewman, this);
        setListener(viewwoman,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sex_no:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickNo(this);
                    dismiss();
                }
                break;
            case R.id.sex_man:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickMan(this);
                    dismiss();

                }
                break;
            case R.id.sex_woman:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickWoman(this);
                    dismiss();

                }
                break;
        }
    }



    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        onDialogClickListener = listener;
    }

    public interface OnDialogClickListener {
        void onClickNo(SexDialog dialog);
        void onClickMan(SexDialog dialog);
        void onClickWoman(SexDialog dialog);
    }
}

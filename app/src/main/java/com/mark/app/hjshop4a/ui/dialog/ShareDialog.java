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
 * Created by pc on 2018/5/1.
 */

public class ShareDialog extends BaseDialogFragment {
    //根布局
    View mRootView;
    Context content ;

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
        View rootView = inflater.inflate(R.layout.dialog_share, container, false);
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
        View viewwx  =getView(rootView,R.id.tv_1);
        View viewwxf = getView(rootView, R.id.tv_2);
        View viewweibo = getView(rootView, R.id.tv_3);
        View viewqq = getView(rootView, R.id.tv_4);
        setListener(viewwx, this);
        setListener(viewwxf, this);
        setListener(viewweibo,this);
        setListener(viewqq,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_1:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickWX(this);
                    dismiss();
                }
                break;
            case R.id.tv_2:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickWXF(this);

                }
                break;
            case R.id.tv_3:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickWeiBo(this);
                    dismiss();
                }
                break;
            case R.id.tv_4:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickQQ(this);
                    dismiss();
                }
                break;
            case R.id.tv_cancel:
               dismiss();
                break;
        }
    }
    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        onDialogClickListener = listener;
    }

    public interface OnDialogClickListener {
        void onClickWX(ShareDialog dialog);
        void onClickWXF(ShareDialog dialog);
        void onClickWeiBo(ShareDialog dialog);
        void onClickQQ(ShareDialog dialog);
    }
}

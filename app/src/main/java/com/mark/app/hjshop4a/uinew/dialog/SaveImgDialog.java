package com.mark.app.hjshop4a.uinew.dialog;

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

public class SaveImgDialog extends BaseDialogFragment {
    //根布局
    View mRootView;
    Context content;
    int YesNo;
    private String mImageUrl;
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
        View rootView = inflater.inflate(R.layout.dialog_save_img, container, false);
        final AlertDialog dialog = new AlertDialog.Builder(content, R.style.dialog_lhp)
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

        View viewman = getView(rootView, R.id.yes);


        setListener(viewman, this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.yes:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickYes();
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


        void onClickYes();
    }
}



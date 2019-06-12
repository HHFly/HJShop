package com.mark.app.hjshop4a.uinew.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;


/**
 * 查看大图
 * Created by lenovo on 2017/12/1.
 */

public class BigSdvDialog extends BaseDialogFragment {

    private String mImageUrl;
    private  int url;
    int type ;
    public static BigSdvDialog getInstance(String imageUrl) {
        BigSdvDialog dialog = new BigSdvDialog();
        dialog.mImageUrl = imageUrl;
        return dialog;
    }
    public static BigSdvDialog getInstance(int imageUrl,int type) {
        BigSdvDialog dialog = new BigSdvDialog();
        dialog.url = imageUrl;
        dialog.type =type;
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_big_sdv, container, false);
        SimpleDraweeView sdv = (SimpleDraweeView) view.findViewById(R.id.dialog_sdv);
        switch (type){
            case 0:
                FrescoUtils.sdvBig(sdv, mImageUrl);
                break;
            case 1:
                sdv.setImageResource(url);
                break;
                default:FrescoUtils.sdvBig(sdv, mImageUrl);
                break;
        }
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(0x00ffffff));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        win.setAttributes(params);
    }
}

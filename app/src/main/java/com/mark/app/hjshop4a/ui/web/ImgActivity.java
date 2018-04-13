package com.mark.app.hjshop4a.ui.web;

import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;

/**
 * 纯图片的界面
 * Created by lenovo on 2017/9/18.
 */

public class ImgActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_img;
    }

    @Override
    public void getIntentParam(Bundle bundle) {

    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        SimpleDraweeView sdv = getView(R.id.img_sdv);
    }

    @Override
    public void onClick(View v) {

    }
}

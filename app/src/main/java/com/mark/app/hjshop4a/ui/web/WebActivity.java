package com.mark.app.hjshop4a.ui.web;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.fragment.BaseWebFragment;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.androidenum.web.WebType;


/**
 * 网页加载Activity
 * Created by lenovo on 2017/9/7.
 */

public class WebActivity extends BaseActivity {
    private String mUrl;    //链接地址
    private String mTitle;  //链接标题
    private int mType;      //类型

    @Override
    public int getContentViewResId() {
        return R.layout.activity_web;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        mTitle = bundle.getString(BundleKey.TITLE);
        mUrl = bundle.getString(BundleKey.URL);
        mType = bundle.getInt(BundleKey.TYPE, WebType.NORMAL);
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        BaseWebFragment fragment;
        switch (mType) {
            case WebType.ALPHA: {
                fragment = AlphaWebFragment.getInstance(mUrl, mTitle);
                break;
            }
            default:
            case WebType.NORMAL: {
                fragment = WebFragment.getInstance(mUrl, mTitle);
                break;
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rootView, fragment).show(fragment).commit();
    }

    @Override
    public void onClick(View v) {

    }
}

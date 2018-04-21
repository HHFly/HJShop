package com.mark.app.hjshop4a.ui.homepager.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActAnimationUtils;


/**
 * 启动页
 * Created by lenovo on 2017/11/2.
 */

public class SplashActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return 0;
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
        startActivity(new Intent(this, PermisstionActivity.class));
        finish();
        ActAnimationUtils.actNone(this);
    }

    @Override
    public void onClick(View v) {

    }
}

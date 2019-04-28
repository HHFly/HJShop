package com.mark.app.hjshop4a.uinew;

import android.view.View;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;

public class VersionActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_version;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.版本信息);
        setTvText(R.id.version_number,getString(R.string.当前版本, BuildConfig.VERSION_NAME));
    }
    @Override
    public void setListener() {
        super.setListener();
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.version_check);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.version_check:
                    validUpdate();
                break;

        }
    }
}

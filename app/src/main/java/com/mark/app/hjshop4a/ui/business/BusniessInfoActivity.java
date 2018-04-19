package com.mark.app.hjshop4a.ui.business;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.ui.businessapply.BusinessStoreImgActivity;

/**
 * Created by pc on 2018/4/19.
 */

public class BusniessInfoActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_busniesss_info;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商户信息");
        setTvText(R.id.titlebar_tv_right,"协议");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.layout_company_info);
        setClickListener(R.id.layout_certificates);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.layout_certificates:
                ActivityJumpUtils.actActivity(this, BusinessStoreImgActivity.class);
                break;
            case R.id.layout_company_info:
                ActivityJumpUtils.actActivity(this,CompanyInfoActivity.class);
                break;
        }
    }
}

package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;

/**
 * Created by pc on 2018/4/18.
 */

public class BusinessApplicationActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_businessapply;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商户申请");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.basic_information);
        setClickListener(R.id.company_information);
        setClickListener(R.id.state);
        setClickListener(R.id.button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.basic_information:
                ActivityJumpUtils.actActivity(this, BusniessUserInfoActivity.class);
                break;
            case R.id.company_information:
                ActivityJumpUtils.actActivity(this,BusniessCompanyActivity.class);
                break;
            case R.id.state:
                break;
            case R.id.button:
                break;

        }
    }
}

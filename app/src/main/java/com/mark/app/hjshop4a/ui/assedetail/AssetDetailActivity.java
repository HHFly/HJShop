package com.mark.app.hjshop4a.ui.assedetail;

import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;

/**
 * Created by pc on 2018/4/17.
 */

public class AssetDetailActivity extends BaseActivity {
    private int role;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_asset_detail;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
         role =bundle.getInt("Role");
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"资产明细");
        initRole();
    }

    private void initRole() {
        switch (role){
            case RoleType.MEMBER:
                setViewVisibility(R.id.declaration_income,false);
        }
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
        }
    }
}

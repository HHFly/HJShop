package com.mark.app.hjshop4a.ui.businessapply;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;

/**
 * Created by pc on 2018/4/18.
 */

public class BusniessCompanyActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_company;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"公司信息");

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.company_layout_name);
        setClickListener(R.id.company_layout_loacl);
        setClickListener(R.id.company_layout_address);
        setClickListener(R.id.store_layout_name);
        setClickListener(R.id.shop_layout_type);
        setClickListener(R.id.shop_layout_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.company_layout_name:
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.company_tv_name);
                break;
            case R.id.company_layout_loacl:
                FunctionDialogFactory.showWheeDialog(this);
                break;
            case R.id.company_layout_address:
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.company_tv_loacl);
                break;
            case R.id.store_layout_name:
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.store_tv_name);
                break;
            case R.id.shop_layout_type:
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.shop_tv_type);
                break;
            case R.id.shop_layout_img:
                ActivityJumpUtils.actActivity(this,BusinessStoreImgActivity.class);
                break;
        }
    }
}

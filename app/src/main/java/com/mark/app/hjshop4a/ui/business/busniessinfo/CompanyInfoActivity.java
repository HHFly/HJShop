package com.mark.app.hjshop4a.ui.business.busniessinfo;

import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.ui.businessapply.model.CompanyInfo;

/**
 * Created by pc on 2018/4/19.
 */

public class CompanyInfoActivity extends BaseActivity {
    private CompanyInfo mData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_company_info;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        mData = (CompanyInfo) bundle.getSerializable("CompanyInfo");
    }

    @Override
    public void initView() {
        if(mData!=null) {
            setTvText(R.id.titlebar_tv_title, "公司信息");
            setTvText(R.id.store_name, mData.getCompanyName());
            setTvText(R.id.store_type, mData.getShopCategoryName());
            setTvText(R.id.store_address, mData.getAddressInfo().getCompleteAddress());
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

package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;

/**
 * Created by pc on 2018/4/18.
 */

public class BusniessUserInfoActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_person_info;
    }

    @Override
    public void initView() {

        setTvText(R.id.titlebar_tv_title,"个人信息");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.user_layout_user_name);
        setClickListener(R.id.user_layout_real_name);
        setClickListener(R.id.user_layout_user_email);
        setClickListener(R.id.user_layout_user_idcard);
        setClickListener(R.id.user_layout_user_check);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.user_layout_user_name:
                FunctionDialogFactory.showAddOneParamDialog(this,"请输入会员名称",R.id.user_tv_user_name);
                break;
            case R.id.user_layout_real_name:
                FunctionDialogFactory.showAddOneParamDialog(this,"请输入真实姓名",R.id.user_tv_user_real_name);
                break;
            case R.id.user_layout_user_email:
                FunctionDialogFactory.showAddOneParamDialog(this,"请输入邮箱地址",R.id.user_tv_user_email);
                break;
            case R.id.user_layout_user_idcard:
                FunctionDialogFactory.showAddOneParamDialog(this,"请输入身份证号码",R.id.user_tv_user_idcard);
                break;
            case R.id.user_layout_user_check:
                ActivityJumpUtils.actActivity(this,IdCardCheckActivity.class);
                break;
        }
    }

}

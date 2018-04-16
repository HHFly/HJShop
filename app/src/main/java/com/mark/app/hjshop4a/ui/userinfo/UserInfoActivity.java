package com.mark.app.hjshop4a.ui.userinfo;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;

/**
 * Created by pc on 2018/4/16.
 */

public class UserInfoActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {
       setTvText(R.id.titlebar_tv_title,"个人信息认证");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.certification_information);
        setClickListener(R.id.basic_information);
        setClickListener(R.id.forget_psw);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.certification_information:
                ActivityJumpUtils.actCertificationInfo(this);
                break;
            case R.id.basic_information:
                //基本信息
                ActivityJumpUtils.actBasicInfo(this);
                break;
            case  R.id.forget_psw:
//                  设置密码

                ActivityJumpUtils.actModifyPW(this);
                break;
            case  R.id.titlebar_iv_return:
                finish();
                break;
        }
    }
}

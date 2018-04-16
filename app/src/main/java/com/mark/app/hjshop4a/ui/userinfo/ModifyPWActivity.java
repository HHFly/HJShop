package com.mark.app.hjshop4a.ui.userinfo;

import android.view.View;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;

/**
 * Created by pc on 2018/4/16.
 */

public class ModifyPWActivity extends BaseActivity {
    private  boolean newflag =false;
    private  boolean oldflag =false;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_user_modify_pwd;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"设置密码");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.iv_new_eye);
        setClickListener(R.id.iv_old_eye);
        setClickListener(R.id.btn);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.iv_old_eye:
                oldflag=!oldflag;
                EditText oet = getView(R.id.et_old_pwd);
                EditTextUtils.changedEditTextVisiable(oet, oldflag);
                setViewSelected(R.id.iv_old_eye, oldflag);
                break;
            case R.id.iv_new_eye:
                newflag =!newflag;
                EditText net = getView(R.id.et_new_pwd);
                EditTextUtils.changedEditTextVisiable(net, newflag);
                setViewSelected(R.id.iv_new_eye, newflag);
                break;
            case R.id.btn:
//                确定
                break;
        }
    }



}

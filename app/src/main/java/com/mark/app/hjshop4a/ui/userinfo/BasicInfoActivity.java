package com.mark.app.hjshop4a.ui.userinfo;

import android.content.res.AssetManager;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.WheelDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.dialog.factory.WheelDialogFactory;
import com.mark.app.hjshop4a.widget.PickerScrollView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pc on 2018/4/16.
 */

public class BasicInfoActivity extends BaseActivity {
    private AddOneEtParamDialog mAddOneEtParamDialog;
    //选择dialog
    private WheelDialog wheelDialog;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_user_basic_info;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"基本信息");
        setTvText(R.id.certification_tv_user_ispass,"未通过");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.certification_layout_user_name);
        setClickListener(R.id.certification_layout_user_city);
        setClickListener(R.id.certification_layout_user_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.certification_layout_user_name:
//                姓名
                FunctionDialogFactory.showAddOneParamDialog(this,R.string.info_请输入姓名,R.id.certification_tv_user_name);
                break;
            case R.id.certification_layout_user_city:
//                居住城市
                FunctionDialogFactory.showWheeDialog(this);
                break;
            case R.id.certification_layout_user_address:
//                居住地址
                FunctionDialogFactory.showAddOneParamDialog(this,R.string.basicinfo_请输入居住地址,R.id.certification_tv_user_address);
                break;
        }
    }


}

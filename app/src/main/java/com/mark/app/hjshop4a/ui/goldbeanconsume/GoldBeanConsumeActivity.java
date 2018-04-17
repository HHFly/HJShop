package com.mark.app.hjshop4a.ui.goldbeanconsume;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.InputType;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;

/**
 * Created by pc on 2018/4/17.
 */

public class GoldBeanConsumeActivity  extends BaseActivity{
    private int role ;
    private AddOneEtParamDialog mAddOneEtParamDialog;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_gold_bean_consume_member;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        role =bundle.getInt(BundleKey.ROLE);
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"金豆换购");
        initRole();
    }

    private void initRole() {
        switch (role){
            case RoleType.MEMBER:
                break;
            case RoleType.BUSINESS:
                break;
        }
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.button);
        setClickListener(R.id.shop_name);
        setClickListener(R.id.bean_change_count);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case  R.id.button:
                break;
            case R.id.bean_change_count:
                showAddOneParamDialog(R.string.gold_请输入金豆换购的个数,R.id.tv_bean_change_count);
                break;
        }
    }
    /**
     * 显示一个参数的对话框
     */
    private void showAddOneParamDialog(@StringRes int paramEmptyHint, @IdRes final int idres) {
        if (mAddOneEtParamDialog == null) {
            mAddOneEtParamDialog = AddOneEtParamDialog.getInstance();
        }

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {
//                requestUpdateParam(type, data);
                setTvText(idres,data);
                dialog.dismiss();
            }
        });
//        mAddOneEtParamDialog.setTvParamName(paramName);
        mAddOneEtParamDialog.setTvEmptyParamHint(paramEmptyHint);
        mAddOneEtParamDialog.InputType(InputType.TYPE_CLASS_NUMBER);
        mAddOneEtParamDialog.show(getFragmentManager());
    }

}

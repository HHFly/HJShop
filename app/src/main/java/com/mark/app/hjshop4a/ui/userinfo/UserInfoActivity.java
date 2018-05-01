package com.mark.app.hjshop4a.ui.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class UserInfoActivity extends BaseActivity {
    private UserInfo mdata ;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {
       setTvText(R.id.titlebar_tv_title,"个人信息设置");
        mdata= App.getAppContext().getUserInfo();
    }



    @Override
    public void setListener() {
        setClickListener(R.id.certification_information);
        setClickListener(R.id.basic_information);
        setClickListener(R.id.forget_psw);
        setClickListener(R.id.set_pay_psw);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.certification_information:
                Intent intent =new Intent(this,CertificationInfoActivity.class);
                BundleUtils.getInstance().putSerializable("UserInfo",mdata).addIntent(intent);
                this.startActivity(intent);
                break;
            case R.id.basic_information:
                //基本信息
                Intent intent_i =new Intent(this,BasicInfoActivity.class);
                BundleUtils.getInstance().putSerializable("UserInfo",mdata).addIntent(intent_i);
                this.startActivity(intent_i);

                break;
            case  R.id.forget_psw:
//                  修改密码

                ActivityJumpUtils.actModifyPW(this);
                break;

            case R.id.set_pay_psw:
//                支付密码
               ActivityJumpUtils.actActivity(this,PayPWDActivity.class);
                break;
            case  R.id.titlebar_iv_return:
                finish();
                break;
        }
    }

    /**
     * 请求数据
     */
    private void requestData(int type,CommitUserInfo userInfo) {
//        showLoadingDialog();

        App.getServiceManager().getPdmService()
                .setUserInfo(type,userInfo.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        ToastUtils.show("修改成功");

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }

    private  void  ShowDialog(){
        AddOneEtParamDialog mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(true);

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {


                dialog.dismiss();
            }
        });

        mAddOneEtParamDialog.show(this.getFragmentManager());
    }
}

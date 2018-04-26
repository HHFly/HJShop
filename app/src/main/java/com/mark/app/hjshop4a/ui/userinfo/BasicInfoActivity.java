package com.mark.app.hjshop4a.ui.userinfo;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.WheelDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.dialog.factory.WheelDialogFactory;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;
import com.mark.app.hjshop4a.widget.PickerScrollView;

import java.io.IOException;
import java.io.InputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class BasicInfoActivity extends BaseActivity {
    private AddOneEtParamDialog mAddOneEtParamDialog;
    //选择dialog
    private WheelDialog wheelDialog;
    private UserInfo mData;
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        if(bundle!=null){
            mData= (UserInfo) bundle.getSerializable("UserInfo");
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_user_basic_info;
    }

    @Override
    public void initView() {

        setTvText(R.id.titlebar_tv_title,"基本信息");
        if(mData!=null){
            setTvText(R.id.certification_tv_user_name,mData.getUserName());
            setTvText(R.id.certification_tv_user_city,mData.getProvinceName()+mData.getCityName()+mData.getCountyName());
            setTvText(R.id.certification_tv_user_address,mData.getCompleteAddress());
        }
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
                ShowDialog(R.id.certification_layout_user_name);

                break;
            case R.id.certification_layout_user_city:
//                居住城市

                break;
            case R.id.certification_layout_user_address:
//                居住地址
                ShowDialog(R.id.certification_layout_user_address);

                break;
        }
    }
    /**
     * 请求数据
     */
    private void requestData(int type,CommitUserInfo userInfo) {
        showLoadingDialog();

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

//    填写信息dialog
    private  void  ShowDialog(final int type){
        AddOneEtParamDialog mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(false);

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {

                CommitUserInfo userInfo =new CommitUserInfo();
                switch (type){
                    case R.id.certification_layout_user_name:
                            userInfo.setUserName(data);
                        setTvText(R.id.certification_tv_user_name,data);
                            requestData(0,userInfo);
//                            setTvText(R.id.certification_layout_user_name,data);
                            break;
                    case R.id.certification_layout_user_address:
                        userInfo.setCompleteAddress(data);
                        setTvText(R.id.certification_tv_user_address,data);
                        requestData(0,userInfo);

                        break;

                }
                dialog.dismiss();
            }
        });

        mAddOneEtParamDialog.show(this.getFragmentManager());
    }
}

package com.mark.app.hjshop4a.uinew.bindinfo;

import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfo;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoParam;
import com.mark.app.hjshop4a.uinew.bindinfo.model.BuyerAccount;
import com.mark.app.hjshop4a.widget.UpdateImgLayout;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddTaobaoActivity extends BaseActivity {
    UpdateImgLayout huabeiPic ,levelPic,realAuthenticatePic;

    long id ;
    int type;
    AccountInfoParam accountInfoParam =new AccountInfoParam();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_taobao;
    }

    @Override
    public void findView() {
        huabeiPic  =getView(R.id.up_huabei);
        levelPic  =getView(R.id.up_realAuthenticate);
        realAuthenticatePic  =getView(R.id.up_levelPic);
    }
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        id =bundle.getLong(BundleKey.ID);
        type =bundle.getInt(BundleKey.SHOWTYPE);
    }
    @Override
    public void initView() {

        setTvText(R.id.titlebar_tv_title,R.string.绑定淘宝账号);
        setViewVisibility(R.id.mask,type==1);

        requestData();

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
    private void  changeDataUI(BuyerAccount data){
        setTvText(R.id.et_wangwangname,data.getAccountName());
        setTvText(R.id.et_recivename,data.getReceiverName());
        setTvText(R.id.et_recivephone,data.getReceiverPhone());
        setTvText(R.id.et_reciveaddress,data.getAddresDetail());
        setTvText(R.id.user_tv_user_sex,data.getSex());
        setTvText(R.id.user_tv_user_level,data.getLevel());
        setTvText(R.id.user_tv_user_shoptype,data.getShoppingType());
        setTvText(R.id.user_tv_user_ishuabei,data.getIsHuabei()==0?getString(R.string.不是):getString(R.string.是));
        levelPic.setImg(data.getLevelPic());
        huabeiPic.setImg(data.getHuabeiPic());
        realAuthenticatePic.setImg(data.getRealAuthenticatePic());
    }
    //请求数据
    private void requestData() {
        accountInfoParam.setId(id);
        App.getServiceManager().getmService()
                .getAcccountInfo1(accountInfoParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<BuyerAccount>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<BuyerAccount> obj) {
                        BuyerAccount data = JsonUtils.fromJson(obj.getResult(),BuyerAccount.class);
                        changeDataUI(data);

                    }

                    @Override
                    public void onAllFinish() {
//                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }
                });
    }

}

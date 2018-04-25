package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;
import com.white.lib.utils.ToastUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/18.
 */

public class BusinessApplicationActivity extends BaseActivity {
    private BusinessApply mData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_merchant_apply;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商户申请");
        requestData();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.layout_certificates);
        setClickListener(R.id.layout_company_info);
        setClickListener(R.id.state);
        setClickListener(R.id.button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.layout_certificates:
                if(mData!=null) {
                    Intent intent = new Intent(this, IdCardCheckActivity.class);
                    BundleUtils.getInstance().putSerializable("userInfo", mData.getUserInfo()).addIntent(intent);
                    this.startActivity(intent);
                }else {
                    ToastUtil.show("服务器繁忙稍后重试");
                }
                break;
            case R.id.layout_company_info:
                ActivityJumpUtils.actActivity(this,BusniessCompanyActivity.class);
                break;
            case R.id.state:
                break;
            case R.id.button:
                break;

        }
    }
    private  void requestData(){
        showLoadingDialog();
        App.getServiceManager().getPdmService().merchantapply()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BusinessApply>() {
                    @Override
                    public void onSuccess(BaseResultEntity<BusinessApply> obj) {
                        BusinessApply data =obj.getResult();
                        mData =data;
                        setTvText(R.id.user_tv_1,data.getUserInfo().getUserNick());
                        setTvText(R.id.user_tv_2,data.getUserInfo().getUserRealName());
                        setTvText(R.id.user_tv_3,data.getUserInfo().getCellphone());
                        setTvText(R.id.user_tv_4,data.getUserInfo().getEmail());
                        SwitchAduit(data.getAuditStatus());
                    }


                    @Override
                    public void onUnSuccessFinish() {


                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });


    }
    // 0 审核中 1 审核通过 2 审核不通过

    private void  SwitchAduit(int auditStatus){
        switch (auditStatus){
            case 0: setIvImage(R.id.aduit_logo,R.mipmap.ic_review_pass);break;
            case 1:setIvImage(R.id.aduit_logo,R.mipmap.ic_review_pass);break;
            case 2:setIvImage(R.id.aduit_logo,R.mipmap.ic_review_pass);break;

        }
    }
}

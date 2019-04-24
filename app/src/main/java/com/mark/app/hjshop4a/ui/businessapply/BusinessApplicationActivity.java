package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/18.
 */

public class BusinessApplicationActivity extends BaseActivity {
    private BusinessApply mData;
    private int role ;
    private long shopId;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_merchant_apply;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        shopId=bundle.getLong("shopID");
    }

    @Override
    public void initView() {
        role =App.getAppContext().getRoleType();
        switch (role){
          case   RoleType.MEMBER: setTvText(R.id.titlebar_tv_title,"商户申请");

                                    break;
            case  RoleType.BUSINESS: setTvText(R.id.titlebar_tv_title,"商户信息");
                                        setViewVisibility(R.id.rl_4,true);
                                             break;
            case RoleType.AREAAGENT: setTvText(R.id.titlebar_tv_title,"商户申请");
                                        setViewVisibility(R.id.rl_4,true);
                                        break;
        }
        if (shopId==0) {
            requestData();
        }else {
            requestDataShopID();
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== ActResultCode.RESULT_OK){
            requestData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.layout_certificates:
                if(mData!=null&&!mData.getUserInfo().getUserIdCard().equals("")&&!mData.getUserInfo().getUserIdCardFront().equals("")&&!mData.getUserInfo().getUserIdCardSide().equals("")) {

                        Intent intent = new Intent(this, IdCardCheckActivity.class);
                        BundleUtils.getInstance().putSerializable("userInfo", mData.getBusniessApplyUserInfo()).addIntent(intent);
                        this.startActivityForResult(intent,1);


                }else {
                    ToastUtils.show("请先前往个人信息认证");
                }
                break;

            case R.id.layout_company_info:
                if(mData!=null&&!mData.getUserInfo().getUserIdCard().equals("")&&!mData.getUserInfo().getUserIdCardFront().equals("")&&!mData.getUserInfo().getUserIdCardSide().equals("")) {

                        if(Apply.PASS==mData.getUserAuditStatus()) {
                            Intent intent = new Intent(this, BusniessCompanyActivity.class);
                            BundleUtils.getInstance().putSerializable("BusinessApply", mData).addIntent(intent);
                            this.startActivityForResult(intent,1);
                        }else {
                            ToastUtils.show("个人信息认证审核未通过");
                        }

                }else {
                    ToastUtils.show("请先前往个人信息认证");
                }
                break;
            case R.id.state:
                break;
            case R.id.button:
                break;

        }
    }

    private void requestDataShopID(){
        showLoadingDialog();
        App.getServiceManager().getPdmService().getMerchantapplyInfo(shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BusinessApply>() {
                    @Override
                    public void onSuccess(BaseResultEntity<BusinessApply> obj) {
                        BusinessApply data =obj.getResult();
                        mData =data;
                        if(data!=null&& data.getBusniessApplyUserInfo()!=null) {
                            setTvText(R.id.user_tv_1, data.getBusniessApplyUserInfo().getUserNick());
                            setTvText(R.id.user_tv_2, data.getBusniessApplyUserInfo().getUserRealName());
                            setTvText(R.id.user_tv_3, data.getBusniessApplyUserInfo().getCellphone());
                            setSdvInside(R.id.hm_sdv_logo, data.getBusniessApplyUserInfo().getUserHeadImg());
                            SwitchAduit(data.getAuditStatus());
                            SwitchUserAduit(data.getUserAuditStatus());
                        }
                        if(data.getCompanyInfo()!=null){
                            setTvText(R.id.user_tv_4, String.valueOf(data.getCompanyInfo().getShopId()));
                        }
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
    private  void requestData(){
        showLoadingDialog();
        App.getServiceManager().getPdmService().getMerchantapply()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BusinessApply>() {
                    @Override
                    public void onSuccess(BaseResultEntity<BusinessApply> obj) {
                        BusinessApply data =obj.getResult();
                        mData =data;
                    if(data!=null&& data.getBusniessApplyUserInfo()!=null) {
                        setTvText(R.id.user_tv_1, data.getBusniessApplyUserInfo().getUserNick());
                        setTvText(R.id.user_tv_2, data.getBusniessApplyUserInfo().getUserRealName());
                        setTvText(R.id.user_tv_3, data.getBusniessApplyUserInfo().getCellphone());
                        setSdvInside(R.id.hm_sdv_logo, data.getBusniessApplyUserInfo().getUserHeadImg());
                        SwitchAduit(data.getAuditStatus());
                        SwitchUserAduit(data.getUserAuditStatus());
                    }
                    if(data.getCompanyInfo()!=null){
                        setTvText(R.id.user_tv_4, String.valueOf(data.getCompanyInfo().getShopId()));
                    }
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
    // 审核状态 0 待审核 1审核中 2审核通过 3 审核不通过
    private void SwitchUserAduit(int userAuditStatus) {
        switch (userAuditStatus){
            case Apply.ToBeAudited:
                setTvText(R.id.tv_certificates,"待审核");
                break;
            case Apply.Audit:
                setTvText(R.id.tv_certificates,"审核中");break;
            case Apply.PASS:
                setTvText(R.id.tv_certificates,"审核通过");break;
            case Apply.UNPASS:
                setTvText(R.id.tv_certificates,"审核不通过");break;
        }
    }
    //审核状态 0 待审核 1审核中 2审核通过 3 审核不通过

    private void  SwitchAduit(int auditStatus){
        switch (auditStatus){
            case Apply.Audit: setIvImage(R.id.aduit_logo_apply,R.mipmap.ic_in_review);
                    setViewVisibility(R.id.aduit_logo_apply,true);
                setTvText(R.id.tv_company_info,"审核中");
                     break;
            case Apply.PASS:setViewVisibility(R.id.aduit_logo_apply,true);setIvImage(R.id.aduit_logo_apply,R.mipmap.ic_review_pass);
                    setTvText(R.id.tv_company_info,"审核通过");break;
            case Apply.UNPASS:setViewVisibility(R.id.aduit_logo_apply,true);setIvImage(R.id.aduit_logo_apply,R.mipmap.ic_uppass); setTvText(R.id.tv_company_info,"审核不通过");break;
            case Apply.ToBeAudited:setViewVisibility(R.id.aduit_logo_apply,false);
                    if(mData.getCompanyInfo()==null||mData.getCompanyInfo().getCompanyName().equals("")) {
                        setTvText(R.id.tv_company_info, "");
                    }else {
                        setTvText(R.id.tv_company_info, "待审核");
                    }
                        break;

        }
    }
}

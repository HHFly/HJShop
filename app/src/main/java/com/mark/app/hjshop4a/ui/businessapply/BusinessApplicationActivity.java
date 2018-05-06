package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.StringUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.business.busniessinfo.BusinessStoreImgActivity;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;
import com.white.lib.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/18.
 */

public class BusinessApplicationActivity extends BaseActivity {
    private BusinessApply mData;
    private int role ;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_merchant_apply;
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
        }

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
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
                if(mData!=null&&mData.getBusniessApplyUserInfo()!=null) {

                    Intent intent = new Intent(this, IdCardCheckActivity.class);
                    BundleUtils.getInstance().putSerializable("userInfo", mData.getBusniessApplyUserInfo()).addIntent(intent);
                    this.startActivityForResult(intent,1);

                }else {
                    ToastUtils.show("个人信息认证未设置");
                }
                break;
            case R.id.layout_company_info:
                if(mData!=null) {
                        if(mData.getUserAuditStatus()==1) {
                            Intent intent = new Intent(this, BusniessCompanyActivity.class);
                            BundleUtils.getInstance().putSerializable("BusinessApply", mData).addIntent(intent);
                            this.startActivity(intent);
                        }else {
                            ToastUtils.show("个人信息认证审核未通过");
                        }

                }else {
                    ToastUtils.show("个人信息认证未设置");
                }
                break;
            case R.id.state:
                break;
            case R.id.button:
                break;

        }
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
                        setTvText(R.id.user_tv_4, String.valueOf(data.getCompanyInfo().getShopId()));
                        setSdvInside(R.id.hm_sdv_logo, data.getBusniessApplyUserInfo().getUserHeadImg());
                        SwitchAduit(data.getAuditStatus());
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
    // 0 审核中 1 审核通过 2 审核不通过 3未提交

    private void  SwitchAduit(int auditStatus){
        switch (auditStatus){
            case 0: setIvImage(R.id.aduit_logo_apply,R.mipmap.ic_in_review);
                    setViewVisibility(R.id.aduit_logo_apply,true);
                     break;
            case 1:setViewVisibility(R.id.aduit_logo_apply,true);setIvImage(R.id.aduit_logo_apply,R.mipmap.ic_review_pass);break;
            case 2:setViewVisibility(R.id.aduit_logo_apply,true);setIvImage(R.id.aduit_logo_apply,R.mipmap.ic_uppass);break;
            case 3:setViewVisibility(R.id.aduit_logo_apply,false);break;
        }
    }
}

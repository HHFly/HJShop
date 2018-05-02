package com.mark.app.hjshop4a.ui.business.busniessinfo;

import android.content.Intent;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/19.
 */

public class BusniessInfoActivity extends BaseActivity {
    private BusinessApply mData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_busniesss_info;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商户信息");
        setTvText(R.id.titlebar_tv_right,"协议");
        requestData();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.layout_company_info);
        setClickListener(R.id.layout_certificates);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.layout_certificates:
                Intent intent1 =new Intent(this,BusinessStoreImgActivity.class);
                BundleUtils.getInstance().putSerializable("CompanyInfo",mData.getCompanyInfo()).addIntent(intent1);
                startActivity(intent1);

                break;
            case R.id.layout_company_info:
                Intent intent =new Intent(this,CompanyInfoActivity.class);
                BundleUtils.getInstance().putSerializable("CompanyInfo",mData.getCompanyInfo()).addIntent(intent);
                startActivity(intent);

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
                    if(data!=null) {
                        setTvText(R.id.user_tv_user_name, data.getBusniessApplyUserInfo().getUserRealName());
                        setTvText(R.id.user_tv_user_phone, data.getBusniessApplyUserInfo().getCellphone());
                        setTvText(R.id.user_tv_user_email, data.getBusniessApplyUserInfo().getEmail());
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
    // 0 审核中 1 审核通过 2 审核不通过

    private void  SwitchAduit(int auditStatus){
        switch (auditStatus){
            case 0: setIvImage(R.id.aduit_logo,R.mipmap.ic_review_pass);break;//待审核
            case 1:setIvImage(R.id.aduit_logo,R.mipmap.ic_review_pass);break;//审核通过
            case 2:setIvImage(R.id.aduit_logo,R.mipmap.ic_uppass);break;  //审核不通过

        }
    }
}

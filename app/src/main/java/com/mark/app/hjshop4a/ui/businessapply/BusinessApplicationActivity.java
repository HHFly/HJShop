package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;
import com.white.lib.utils.ToastUtil;

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
                    BundleUtils.getInstance().putSerializable("userInfo", mData.getBusniessApplyUserInfo()).addIntent(intent);
                    this.startActivity(intent);
                }else {
                    ToastUtils.show("个人信息未设置");
                }
                break;
            case R.id.layout_company_info:
                if(mData!=null) {
                Intent intent = new Intent(this, BusniessCompanyActivity.class);
                BundleUtils.getInstance().putSerializable("BusinessApply", mData).addIntent(intent);
                this.startActivity(intent);
                }else {
                    ToastUtils.show("个人信息未设置");
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
                    if(data!=null) {
                        setTvText(R.id.user_tv_1, data.getBusniessApplyUserInfo().getUserNick());
                        setTvText(R.id.user_tv_2, data.getBusniessApplyUserInfo().getUserRealName());
                        setTvText(R.id.user_tv_3, data.getBusniessApplyUserInfo().getCellphone());
                        setTvText(R.id.user_tv_4, data.getBusniessApplyUserInfo().getEmail());
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
            case 0: setIvImage(R.id.aduit_logo,R.mipmap.ic_in_review);break;
            case 1:setIvImage(R.id.aduit_logo,R.mipmap.ic_review_pass);break;
            case 2:setIvImage(R.id.aduit_logo,R.mipmap.ic_uppass);break;

        }
    }
}

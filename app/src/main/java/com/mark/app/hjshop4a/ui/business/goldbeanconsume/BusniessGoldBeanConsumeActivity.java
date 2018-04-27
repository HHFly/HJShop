package com.mark.app.hjshop4a.ui.business.goldbeanconsume;

import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.business.goldbeanconsume.model.BusniessGoldBeanCS;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.BeanConsume;
import com.white.lib.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/19.
 */

public class BusniessGoldBeanConsumeActivity extends BaseActivity {
    private AddOneEtParamDialog mAddOneEtParamDialog;
    private  Double  Ratiox =1.0;
    private Double BeanNum =0.0 ;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddOneEtParamDialog=null;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_gold_bean_consume_business;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商家兑换金豆");
        requestData();
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
            case R.id.button:
                commit();
                break;
            case R.id.shop_name:
                showDialog();
                break;
        }
    }
    private void commit() {
        String cpatCha =getTvText(R.id.et_audit);
        String beanTradeInNum= getTvText(R.id.tv_bean_change_count);
        if(TextUtils.isEmpty(beanTradeInNum)){
            ToastUtils.show(getApplicationContext(),"请输入兑换的金豆数");
            return;
        }
        if(TextUtils.isEmpty(cpatCha)){
            ToastUtils.show(getApplicationContext(),"请输入验证码");
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .tradeIn(beanTradeInNum,cpatCha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                            requestData();
                        ToastUtil.show("成功");
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();
                        ToastUtil.show("失败");
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /**
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .tradeInget()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BusniessGoldBeanCS>() {


                    @Override
                    public void onSuccess(BaseResultEntity<BusniessGoldBeanCS> obj) {
                        BusniessGoldBeanCS data = obj.getResult();
                        bindData(data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    private void  bindData(BusniessGoldBeanCS data){
        setTvText(R.id.gold_bean_count,data.getBeanUsable());
        BeanNum =NumParseUtils.parseDouble(data.getBeanUsable());
        Ratiox = NumParseUtils.parseDouble(data.getDisCounts());
        setSdvInside(R.id.audit,data.getCaptCha());
    }
    private  void showDialog(){
        if(mAddOneEtParamDialog==null) {
            mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(true);
            mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
                @Override
                public void onClickCommit(AddOneEtParamDialog dialog, String data) {
                    Double count = NumParseUtils.parseDouble(data);
                    if (count < BeanNum) {
                        setTvText(R.id.tv_bean_change_count, data);
                        String cash = String.valueOf(count * Ratiox);
                        setTvText(R.id.tv_money_count, cash);

                    }else {
                        ToastUtils.show(getApplicationContext(),"超过可用金豆数");
                    }

                    dialog.dismiss();
                }
            });

        }
        mAddOneEtParamDialog.show(this.getFragmentManager());
    }
}

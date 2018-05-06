package com.mark.app.hjshop4a.ui.goldbeanconsume;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.assedetail.model.AssetDetail;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.BeanConsume;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.Shop;
import com.white.lib.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/17.
 */

public class MemberGoldBeanConsumeActivity extends BaseActivity{

    private AddOneEtParamDialog mAddOneEtParamDialogId;
    private AddOneEtParamDialog mAddOneEtParamDialogCount;
    private  Double  Ratiox =1.0;
    private Double BeanNum =0.0 ;
    long shopId ;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddOneEtParamDialogId=null;
        mAddOneEtParamDialogCount=null;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_gold_bean_consume_member;
    }

    @Override
    public void getIntentParam(Bundle bundle) {

    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"金豆换购");
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
            case  R.id.button:
                commit();
                break;
            case R.id.bean_change_count:
                showInputDialogCount( R.id.bean_change_count);
                break;
            case R.id.shop_name:
                showInputDialogID( R.id.shop_name);
        }
    }

    private void commit() {

        String beanTradeInNum= getTvText(R.id.tv_bean_change_count);
        if(shopId==0){
            ToastUtils.show(getApplicationContext(),"请输入店铺Id号");
            return;
        }
        if(TextUtils.isEmpty(beanTradeInNum)){
            ToastUtils.show(getApplicationContext(),"请输入兑换的金豆数");
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .beanconsume(shopId,beanTradeInNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        requestData();
                        setTvText(R.id.tv_bean_change_count,"");

                        ToastUtils.show("换购成功");
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
                .getConsume()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanConsume>() {


                    @Override
                    public void onSuccess(BaseResultEntity<BeanConsume> obj) {
                        BeanConsume data = obj.getResult();
                        bindData(data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    private void  bindData(BeanConsume data){
        if(data!=null) {
            setTvText(R.id.gold_bean_count, data.getBeanNum());
            BeanNum = NumParseUtils.parseDouble(data.getBeanNum());
            Ratiox = NumParseUtils.parseDouble(data.getBeanRatio());

        }
    }
    private  void showInputDialogID(final int id){
        if(mAddOneEtParamDialogId==null) {
            mAddOneEtParamDialogId = AddOneEtParamDialog.getInstance(true);
            mAddOneEtParamDialogId.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
                @Override
                public void onClickCommit(AddOneEtParamDialog dialog, String data) {
                   switch (id) {
                       case  R.id.bean_change_count:
                       Double count = NumParseUtils.parseDouble(data);
                       if (count < BeanNum) {
                           setTvText(R.id.tv_bean_change_count, data);
                           String cash = String.valueOf(count * Ratiox);
                           setTvText(R.id.tv_money_count, cash);

                       } else {
                           ToastUtils.show(getApplicationContext(), "超过可用金豆数");
                       }
                       break;
                       case    R.id.shop_name:
                           requestShopName(NumParseUtils.parseLong(data));

                   }
                    dialog.dismiss();
                }
            });

        }
        mAddOneEtParamDialogId.show(this.getFragmentManager());
    }
    private  void showInputDialogCount(final int id){
        if(mAddOneEtParamDialogCount==null) {
            mAddOneEtParamDialogCount = AddOneEtParamDialog.getInstance(true);
            mAddOneEtParamDialogCount.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
                @Override
                public void onClickCommit(AddOneEtParamDialog dialog, String data) {
                    switch (id) {
                        case  R.id.bean_change_count:
                            Double count = NumParseUtils.parseDouble(data);
                            if (count < BeanNum) {
                                setTvText(R.id.tv_bean_change_count, data);
                                String cash = String.valueOf(count * Ratiox);
                                setTvText(R.id.tv_money_count, cash);

                            } else {
                                ToastUtils.show(getApplicationContext(), "超过可用金豆数");
                            }
                            break;
                        case    R.id.shop_name:
                            requestShopName(NumParseUtils.parseLong(data));

                    }
                    dialog.dismiss();
                }
            });

        }
        mAddOneEtParamDialogCount.show(this.getFragmentManager());
    }

    /**
     * 请求数据
     */
    private void requestShopName(long id) {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .getShopName(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Shop>() {


                    @Override
                    public void onSuccess(BaseResultEntity<Shop> obj) {
                        Shop data = obj.getResult();
                        if(data!=null) {
                            setTvText(R.id.tv_user_shop_name, data.getShopName());
                            shopId =data.getShopId();
                        }
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();
//                        ToastUtils.show("不存在该店铺ID");
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }


}

package com.mark.app.hjshop4a.ui.goldbeanconsume;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.BusniessGoldBeanCS;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
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
    protected void onResume() {
        super.onResume();
        requestData();

    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商家兑换金豆");
//       EditText e=  getView(R.id.certification_tv_user_name);

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.button);
        setClickListener(R.id.shop_name);
        setClickListener(R.id.bean_change_count);
        setClickListener(R.id.audit);
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
            case R.id.audit:
                requestImgCode();
                break;
        }
    }
    private void commit() {
        String cpatCha =getTvText(R.id.et_audit);
        String beanTradeInStr= getTvText(R.id.certification_tv_user_name);
        int beanTradeInNum = NumParseUtils.parseInt(beanTradeInStr);
        if(TextUtils.isEmpty(beanTradeInStr)){
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
                        setTvText(R.id.certification_tv_user_name,"");
                        setTvText(R.id.et_audit,"");
                        requestImgCode();
                        ToastUtils.show("金豆兑换成功");
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();

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
        setSdvInside(R.id.audit,data.getCaptCha());
        if(data.getRule()!=null&&!data.getRule().equals("")){
            setTvText(R.id.tv_tip,data.getRule());
        }
        BeanNum =NumParseUtils.parseDouble(data.getBeanUsable());
        Ratiox = NumParseUtils.parseDouble(data.getDisCounts());


    }
    private  void showDialog(){
        if(mAddOneEtParamDialog==null) {
            mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(true);
            mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
                @Override
                public void onClickCommit(AddOneEtParamDialog dialog, String data) {
                    Double count = NumParseUtils.parseDouble(data);
                    if (count <= BeanNum) {
                        if(count%100==0) {
                            if(count>=200) {
                                setTvText(R.id.certification_tv_user_name, data);
                                String cash = String.valueOf(count * Ratiox);
                                setTvText(R.id.tv_bean_change_count, cash);
                            }else {
                                ToastUtils.show(getApplicationContext(),"金豆兑换数最小200");
                            }
                        }else {
                            ToastUtils.show(getApplicationContext(),"金豆兑换数必须是100的倍数");
                        }
                    }else {
                        ToastUtils.show(getApplicationContext(),"超过可用金豆数");
                    }

                    dialog.dismiss();
                }
            });

        }
        mAddOneEtParamDialog.show(this.getFragmentManager());
    }
    /**
     * 请求图片验证码
     */
    private void requestImgCode() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .randomVerification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        String data = (String) obj.getResult();
                        if(data!=null) {
                            setSdvInside(R.id.audit,data);
                        }
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}

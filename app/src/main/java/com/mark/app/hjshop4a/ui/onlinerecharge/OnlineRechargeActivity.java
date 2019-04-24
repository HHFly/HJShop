package com.mark.app.hjshop4a.ui.onlinerecharge;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.google.gson.JsonElement;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.androidenum.pay.PayType;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.onlinerecharge.model.PayWay;
import com.mark.app.hjshop4a.ui.onlinerecharge.model.PayWayList;


import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/17.
 */

public class OnlineRechargeActivity extends BaseActivity {
    private OnlineRechargeRvAdapter mAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_onlinerecharge;
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }
    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, "在线充值");
        requestData();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_iv_return: {
                finish();
                break;
            }
        }
    }

    /**
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .onLineGet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<PayWayList>() {


                    @Override
                    public void onSuccess(BaseResultEntity<PayWayList> obj) {
                        PayWayList data = obj.getResult();
                        if(data!=null&&data.getPayWayList()!=null) {
                            initRvAdapter(data, data.getPayWayList());
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /**
     * 获取支付信息
     */
//    private void requestPayInfo(String topUpMoney, final int payWayCode) {
//        showLoadingDialog();
//        App.getServiceManager().getPdmService().onLine(topUpMoney,payWayCode)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver() {
//
//                    @Override
//                    public void onSuccess(BaseResultEntity obj) {
//                        String payInfo = obj.getResult().toString();
//                        App.getServiceManager().getMkPay().pay(getActivity(), payInfo, SwitchPay(payWayCode), new MkPayCallback() {
//                            @Override
//                            public void onPayResult(MkPayResult result) {
//                                Log.e("onPayResult>>>", result.getResult());
//                                switch (result.getResultStatus()) {
//                                    case MkPayResult.PAY_STATE_SUCCESS: {
////                                        ActivityJumpUtils.actPaySuccess(getActivity(), mOrderSn);
//                                        setResult(ActResultCode.RESULT_OK);
//                                        finish();
//                                        break;
//                                    }
//                                    case MkPayResult.PAY_STATE_CANCEL: {
//                                        ToastUtils.show("支付取消");
//                                        break;
//                                    }
//                                    case MkPayResult.PAY_STATE_FAIL: {
//                                        ToastUtils.show("支付失败");
//                                        break;
//                                    }
//                                    case MkPayResult.PAY_STATE_ERROR: {
//                                        ToastUtils.show("支付错误");
//                                        break;
//                                    }
//                                }
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onAllFinish() {
//                        super.onAllFinish();
//                        hideLoadingDialog();
//                    }
//                });
//    }

//    private int SwitchPay(int payWayCode) {
//        switch (payWayCode){
//            case PayType.ALIPAY: {
//              return MkPay.PAY_TYPE_ALIPAY;
//
//            }
//            case PayType.WECHAT: {
//                return MkPay.PAY_TYPE_WXPAY;
//
//            }
//            default: return 0;
//        }
//
//    }

    /*
    *提交
    * */
    private void commit(PayWay data, String count ) {

        if(TextUtils.isEmpty(count)){
            ToastUtils.show(getApplicationContext(),"请输入充值金额");
            return;
        }

//        requestPayInfo(count,data.getPayWayCode());

    }
    /**
     * 初始化列表
     *
     * @param data
     */
    private void initRvAdapter(PayWayList payWayList, ArrayList<PayWay> data) {

        if (mAdapter == null) {
            mAdapter = new OnlineRechargeRvAdapter(payWayList,payWayList.getPayWayList());
            RecyclerView rv = getView(R.id.recyclerView);
            rv.setAdapter(mAdapter);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mAdapter.setOnItemClickListener(new OnlineRechargeRvAdapter.OnItemClickListener() {
                @Override
                public void onClickPay(PayWay data, String count) {
                            commit(data,count);
                }

            });
        } else {
            mAdapter.notifyData(data, true);
        }
    }
}

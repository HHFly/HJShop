package com.mark.app.hjshop4a.uinew.performorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.TakePhoneUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.performorder.adapter.FourAddShopAdapter;
import com.mark.app.hjshop4a.uinew.performorder.adapter.PayInfoAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.NextStepParam;
import com.mark.app.hjshop4a.uinew.performorder.model.OrderPayParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PayInfo;
import com.mark.app.hjshop4a.uinew.performorder.model.PayInfoParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepFour;
import com.mark.app.hjshop4a.uinew.performorder.model.StepFourParam;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PayInfoActivity extends BaseActivity {
    String subOrderSn;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    PayInfo data;
    PayInfoAdapter payInfoAdapter;
    UpdateStepOneLayout updateStepTip;//
    private int mapId;//1 23 4
    private Map<Integer,String> pic =new HashMap<>();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_perform;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"下单付款");
        requestData();
    }
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        subOrderSn =bundle.getString(BundleKey.ORDER_SN);
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
    @Override
    public void findView() {
        mRefreshLayout =getView(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData();
            }
        });
    }

    /**
     * 请求数据
     */
    private void requestData() {
//        showLoadingDialog();
        PayInfoParam payInfoParam =new PayInfoParam();
        payInfoParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .getPayInfo(payInfoParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        data = JsonUtils.fromJson(obj.getResult(),PayInfo.class);
                        //设置信息
                        initRvAdapter(data);
                        payInfoAdapter.startTime();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(final PayInfo data){

        if(payInfoAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            payInfoAdapter = new PayInfoAdapter(data,data.getData());
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(payInfoAdapter);
            payInfoAdapter.setOnItemClickListener(new PayInfoAdapter.OnItemClickListener() {


                @Override
                public void onClickNext(String tbOrderSn, String payPrice) {
                    nextStep(tbOrderSn,payPrice);
                }

                @Override
                public void onClickHuobisanjiaPic1(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=1;
                    updateStepTip= updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }



            });
        }
        else {
           payInfoAdapter.notifyData(data);
        }
//        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
//        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TakePhoneUtil.onActivityResult(getActivity(), requestCode, resultCode, data, new TakePhoneUtil.CallBack() {
            @Override
            public void back(Uri var1) {
                requestUpdateDataOfNewPic(var1);
            }


        });

    }
    /**
     * 请求上传图片
     *
     */
    private void requestUpdateDataOfNewPic(Uri uri) {
        showLoadingDialog();

        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);
                updateStepTip.setImg(imgUrl);
                pic.put(mapId,imgUrl);


                hideLoadingDialog();

            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
                ToastUtils.show("上传图片失败");
                hideLoadingDialog();

            }

        });

    }
    private void setpic(){
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560415409646&di=4b4d5a87786acb4902a92ae2f4d64d89&imgtype=0&src=http%3A%2F%2Fimg009.hc360.cn%2Fg8%2FM08%2FEE%2F89%2FwKhQt1N9cmGEHKQQAAAAAN7_jII892.jpg";
        pic.put(1,url);
        pic.put(2,url);
        pic.put(3,url);
        pic.put(4,url);
        pic.put(0,url);
    }
    /**
     * 下一步
     */
    private void nextStep(String tbOrderSn, String payPrice) {
        setpic();
        if(!check(tbOrderSn,payPrice)){return;}

        showLoadingDialog();
        OrderPayParam orderPayParam =new OrderPayParam();
        orderPayParam.setSubOrderSn(subOrderSn);
        orderPayParam.setTbOrderSn(tbOrderSn);
        orderPayParam.setPayPic(pic.get(1));
        orderPayParam.setPayPrice(payPrice);
        App.getServiceManager().getmService()
                .orderPay(orderPayParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        ActivityJumpUtils.actEvaluationInfo(getActivity(),subOrderSn);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }

    private boolean check(String tbOrderSn, String payPrice){
        if(!payInfoAdapter.getIsAllCheck()){
            ToastUtils.show("请勾选所有确认信息");
            return false;
        }
        if(TextUtils.isEmpty(tbOrderSn)){
            ToastUtils.show("请输入淘宝订单号");
            return false;
        }
        if(TextUtils.isEmpty(payPrice)){
            ToastUtils.show("请输入付款金额");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(1))){
            ToastUtils.show("请上传加入购物车");
            return false;
        }

        return true;
    }
}

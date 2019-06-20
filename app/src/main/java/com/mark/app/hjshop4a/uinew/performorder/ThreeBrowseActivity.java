package com.mark.app.hjshop4a.uinew.performorder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dd.CircularProgressButton;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.performorder.adapter.TwoSearchAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.AddProduct;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThree;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwo;
import com.mark.app.hjshop4a.widget.UpdateStepLayout;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThreeBrowseActivity extends BaseActivity {
    String subOrderSn;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    StepThree data;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_perform;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"浏览评价");
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
        PerformParam performParam =new PerformParam();
        performParam.setStep(3);
        performParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .getOrderInfo(performParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        data = JsonUtils.fromJson(obj.getResult(),StepThree.class);
                        //设置信息
                        initRvAdapter(data);
//                        twoSearchAdapter.startTime();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(final StepTwo data){

        if(twoSearchAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            twoSearchAdapter = new TwoSearchAdapter(data,data.getAddProductList());
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(twoSearchAdapter);

        }
        else {
            twoSearchAdapter.notifyData(data,data.getAddProductList(),true);
        }
//        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
//        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
}

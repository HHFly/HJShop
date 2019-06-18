package com.mark.app.hjshop4a.uinew.performorder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.uinew.bindinfo.adapter.TaobaoAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfo;
import com.mark.app.hjshop4a.uinew.performorder.adapter.OneDetailAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.NextStepParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepOne;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OneDetailActivity extends BaseActivity {
    String subOrderSn;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    OneDetailAdapter oneDetailAdapter;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_perform;
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

    @Override
    public void initView() {
    setTvText(R.id.titlebar_tv_title,"活动详情");
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
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        subOrderSn =bundle.getString(BundleKey.ORDER_SN);
    }
    /**
     * 请求数据
     */
    private void requestData() {
//        showLoadingDialog();
        PerformParam performParam =new PerformParam();
        performParam.setStep(1);
        performParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .getOrderInfo(performParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        StepOne data = JsonUtils.fromJson(obj.getResult(),StepOne.class);
                        //设置信息
                        initRvAdapter(data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                      mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(final StepOne data){

        if(oneDetailAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            oneDetailAdapter = new OneDetailAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(oneDetailAdapter);
            oneDetailAdapter.setOnItemClickListener(new OneDetailAdapter.OnItemClickListener() {
                @Override
                public void onClickNext() {
                    nextStep();
                }


            });
        }
        else {
            oneDetailAdapter.setData(data);
            oneDetailAdapter.notifyDataSetChanged();
        }
//        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
//        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
    /**
     * 请求数据
     */
    private void nextStep() {
        showLoadingDialog();
        NextStepParam nextStepParam =new NextStepParam();
        nextStepParam.setStep(1);
        nextStepParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .nextStep(nextStepParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        Boolean Sccess = JsonUtils.fromJson(obj.getResult(),Boolean.class);
                      if(Sccess){





                      }else {
                          ToastUtils.show("失败！："+obj.getReason());
                      }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                       hideLoadingDialog();
                    }
                });
    }
}

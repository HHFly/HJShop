package com.mark.app.hjshop4a.ui.areaagent.areabusniess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.model.AreaBusniess;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBusniessActivity extends BaseActivity  implements OnRefreshLoadMoreListener {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    AreaBusniessAdapter mAdapter;
    private long  startTime;
    private long endTime;
    private int role;
    private long cityId =0;
    private long userId =0;
    int mSource;//来源
    PagingBaseModel mPagingData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        cityId=bundle.getLong("cityId");
        userId =bundle.getLong("userId");
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"区域商户");
        setTvText(R.id.titlebar_tv_right,"筛选");
    }
    @Override
    public void findView() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        startTime=0;
        endTime=0;
        if(cityId==0) {
//            cityId = App.getAppContext().getUserInfo().getCityId();
        }
        role= App.getAppContext().getRoleType();
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mRefreshLayout.autoRefresh();
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_tv_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.titlebar_tv_right:
                ActivityJumpUtils.actCalendarView(this,"区域商户");
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            startTime=data.getLongExtra("sTime", 0);
            endTime=data.getLongExtra("eTime", 0);
            mRefreshLayout.autoRefresh();
        }

    }
    private  void requestData(final  int curPage,final  long timetamp){


        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timetamp);
        if(userId==0) {
            App.getServiceManager().getPdmService().areaMerchant(1, pagingParam.getMap(), startTime, endTime, cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<AreaBusniess>() {
                        @Override
                        public void onSuccess(BaseResultEntity<AreaBusniess> obj) {
                            AreaBusniess data = obj.getResult();
                            initRvAdapter(data, curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getMerchantDataList(), obj.getNowTime());
                            RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                        }


                        @Override
                        public void onUnSuccessFinish() {
                            initRvAdapter(new AreaBusniess(), curPage == 1);
                            RefreshLayoutUtils.finish(mRefreshLayout);
                        }


                    });
        }else {
            App.getServiceManager().getPdmService().areaMerchant(1, pagingParam.getMap(), startTime, endTime, cityId,userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<AreaBusniess>() {
                        @Override
                        public void onSuccess(BaseResultEntity<AreaBusniess> obj) {
                            AreaBusniess data = obj.getResult();
                            initRvAdapter(data, curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getMerchantDataList(), obj.getNowTime());
                            RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                        }


                        @Override
                        public void onUnSuccessFinish() {
                            initRvAdapter(new AreaBusniess(), curPage == 1);
                            RefreshLayoutUtils.finish(mRefreshLayout);
                        }


                    });
        }


    }

    public void initRvAdapter(AreaBusniess data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new AreaBusniessAdapter(data,data.getMerchantDataList());
            RecyclerView rv = getView(R.id.recyclerView);
            if (rv != null) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

        } else {
            mAdapter.notifyData(data,data.getMerchantDataList(), isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null );
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                requestData(nextPage,timestamp);
            }


        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData(1,mPagingData.getTimestamp());
    }
}

package com.mark.app.hjshop4a.ui.withdrawdetail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.JsonElement;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceWithDraw;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceWithDrawList;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/21.
 */

public class WithDrawDetailActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    WithDrawDetailAdapter mAdapter;
    PagingBaseModel mPagingData;
    //是否正在刷新数据
    boolean isRequestData;
    @Override
    public void findView() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mRefreshLayout.autoRefresh();
    }
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"提现记录");
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
    /**
     * 请求数据
     */
    private  void requestData(final  int curPage,final  long timetamp){
        if(!isRequestData) {
            isRequestData = true;
            PagingParam pagingParam = new PagingParam();
            pagingParam.setCurrentPage(curPage);
            pagingParam.setTimestamp(timetamp);

            App.getServiceManager().getPdmService().busniessbalanceWithDrawList(3,3,pagingParam.getMap())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<BalanceWithDrawList>() {
                        @Override
                        public void onSuccess(BaseResultEntity<BalanceWithDrawList> obj) {
                            BalanceWithDrawList data = obj.getResult();
                            initRvAdapter(data.getBalanceWithDrawList(), curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getBalanceWithDrawList(), obj.getNowTime());
                            RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                        }


                        @Override
                        public void onUnSuccessFinish() {
                            initRvAdapter(null, curPage == 1);
                            RefreshLayoutUtils.finish(mRefreshLayout);
                        }

                        @Override
                        public void onAllFinish() {
                            super.onAllFinish();

                            isRequestData = false;
                        }
                    });
        }

    }
    /**
     * 刷新适配器
     *
     * @param data
     * @param isRefresh
     */
    public void initRvAdapter(List<BalanceWithDraw> data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new WithDrawDetailAdapter(data);
            RecyclerView rv = getView(R.id.recyclerView);
            if (rv != null) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }



        } else {
            mAdapter.notifyData(data, isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
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

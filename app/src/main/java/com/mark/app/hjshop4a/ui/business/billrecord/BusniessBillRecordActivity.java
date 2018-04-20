package com.mark.app.hjshop4a.ui.business.billrecord;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.ui.business.billrecord.model.BillRecord;
import com.mark.app.hjshop4a.ui.business.billrecord.model.BillsRecord;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.white.lib.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/20.
 */

public class BusniessBillRecordActivity extends BaseActivity implements OnRefreshLoadmoreListener {

    SmartRefreshLayout mRefreshLayout;//刷新框架
    BusniessBillRecordAdapter mAdapter;


    int mSource;//来源
    PagingBaseModel mPagingData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }

    @Override
    public void findView() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void initView() {
     setTvText(R.id.titlebar_tv_title,"报单账单");
     setTvText(R.id.titlebar_tv_right,"筛选");
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
                break;
        }

    }

    private  void requestData(final  int curPage){
        BillsRecord data =new BillsRecord();
        BillRecord billRecord =new BillRecord();
        ArrayList<BillRecord> billRecordArrayList =new ArrayList<>();
        billRecordArrayList.add(billRecord);
        billRecordArrayList.add(billRecord);
        data.setBillRecords(billRecordArrayList);
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        mPagingData.setPagingInfo(curPage,billRecordArrayList );

        initRvAdapter(data, curPage == 1);
        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
    }
    private void initRvAdapter(BillsRecord data ,boolean isRefresh){
        if (mAdapter == null) {
            mAdapter = new BusniessBillRecordAdapter(data, data.getBillRecords());
            RecyclerView rv = getView(R.id.recyclerView);
            if (rv != null) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            mAdapter.setOnItemClickListener(new BusniessBillRecordAdapter.OnItemClickListener() {

                @Override
                public void onClickItem() {

                }
            });
        } else {
            mAdapter.notifyData(data.getBillRecords(), isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.getBillRecords().size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int nextPage) {
                requestData(nextPage);
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData(1);
    }
}

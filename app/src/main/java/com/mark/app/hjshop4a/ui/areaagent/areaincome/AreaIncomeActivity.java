package com.mark.app.hjshop4a.ui.areaagent.areaincome;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.ui.provinceagent.ProvinceAB;
import com.mark.app.hjshop4a.ui.provinceagent.ProvinceAreaBusniessAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaIncomeActivity extends BaseActivity implements OnRefreshLoadmoreListener{
    SmartRefreshLayout mRefreshLayout;
    PagingBaseModel mPagingData;
    AreaIncomeAdapter mAdapter;
    @Override
    public void findView() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
        mRefreshLayout.autoRefresh();
    }
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"区域收益");
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

    private void requestData(final int curPage) {
        OrderItemBean orderItemBean =new OrderItemBean();
        List<OrderItemBean> orderItemBeans =new ArrayList<>();
        for (int i= 1;i<7;i++){
            orderItemBean.setDay(i+"月");
            orderItemBean.setNumber(i+"00");
            orderItemBeans.add(orderItemBean);
            orderItemBean= new OrderItemBean();
        }

        AreaIncome areaBusniess =new AreaIncome();
        areaBusniess.setOrderMonthList(orderItemBeans);
        ArrayList<AreaIncome> data =new ArrayList<>();
        data.add(areaBusniess);

        initRvAdapter(data, curPage == 1);
        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
    }

    public void initRvAdapter(ArrayList<AreaIncome> data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new AreaIncomeAdapter(data);
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
    public void onLoadmore(RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                requestData(nextPage);
            }


        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData(1);
    }
}

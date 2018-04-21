package com.mark.app.hjshop4a.ui.areaagent.billreview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.ui.areaagent.areaincome.AreaIncome;
import com.mark.app.hjshop4a.ui.areaagent.areaincome.AreaIncomeAdapter;
import com.mark.app.hjshop4a.ui.areaagent.areaincome.OrderItemBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBillReviewActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    SmartRefreshLayout mRefreshLayout;
    PagingBaseModel mPagingData;
    AreaBillReviewAdapter mAdapter;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_rvlist;
    }

    @Override
    public void initView() {
            setTvText(R.id.titlebar_tv_title,"辖区报单申请");
    }
    @Override
    public void findView() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
        mRefreshLayout.autoRefresh();
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

        AreaBillReview areaBusniess =new AreaBillReview();

        ArrayList<AreaBillReview> data =new ArrayList<>();
        data.add(areaBusniess);

        initRvAdapter(data, curPage == 1);
        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
    }

    public void initRvAdapter(ArrayList<AreaBillReview> data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new AreaBillReviewAdapter(data.get(0),data);
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

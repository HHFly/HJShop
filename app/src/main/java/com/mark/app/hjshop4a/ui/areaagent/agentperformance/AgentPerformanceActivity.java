package com.mark.app.hjshop4a.ui.areaagent.agentperformance;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.AgentPrefermance;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class AgentPerformanceActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    AgentPrefermanceAdapter mAdapter;


    int mSource;//来源
    PagingBaseModel mPagingData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"代理业绩");
        setTvText(R.id.titlebar_tv_right,"筛选");
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
        AgentPrefermance areaBusniess =new AgentPrefermance();
        ArrayList<AgentPrefermance> data =new ArrayList<>();
        data.add(areaBusniess);
        data.add(areaBusniess);
        initRvAdapter(data, curPage == 1);
        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
    }

    public void initRvAdapter(ArrayList<AgentPrefermance> data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new AgentPrefermanceAdapter(data.get(0),data);
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

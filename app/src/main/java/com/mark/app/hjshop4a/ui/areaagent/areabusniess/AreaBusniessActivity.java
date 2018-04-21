package com.mark.app.hjshop4a.ui.areaagent.areabusniess;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBusniessActivity extends BaseActivity  implements OnRefreshLoadmoreListener {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    AreaBusniessAdapter mAdapter;


    int mSource;//来源
    PagingBaseModel mPagingData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"区域商户");
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
        AreaBusniess areaBusniess =new AreaBusniess();
        ArrayList<AreaBusniess> data =new ArrayList<>();
        data.add(areaBusniess);
        data.add(areaBusniess);
        initRvAdapter(data, curPage == 1);
        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
    }

    public void initRvAdapter(ArrayList<AreaBusniess> data, boolean isRefresh) {
//        data = new ArrayList<>();
//        RedItem item = new RedItem();
//        item.setRedPacketName("10元红包");
//        item.setRedPacketPrice("￡66.00");
//        item.setEndTime(66666666666l);
//        data.add(item);
        if (mAdapter == null) {
            mAdapter = new AreaBusniessAdapter(data.get(0),data);
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

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
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.white.lib.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class WithDrawDetailActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    WithDrawDetailAdapter mAdapter;
    PagingBaseModel mPagingData;
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
     *
     * @param curPage
     */
    private void requestData(final int curPage) {
        ArrayList<WithDraw> arrayList =new ArrayList<>();
        WithDraw withDraw =new WithDraw();
        arrayList.add(withDraw);
        arrayList.add(withDraw);
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        mPagingData.setPagingInfo(curPage,arrayList );
        initRvAdapter(arrayList, curPage == 1);
        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);

    }
    /**
     * 刷新适配器
     *
     * @param data
     * @param isRefresh
     */
    public void initRvAdapter(List<WithDraw> data, boolean isRefresh) {

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

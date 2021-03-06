package com.mark.app.hjshop4a.ui.consumptionbill.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.consumptionbill.adapter.RechargeAdpater;
import com.mark.app.hjshop4a.ui.consumptionbill.model.TopUp;
import com.mark.app.hjshop4a.ui.consumptionbill.model.TopUpList;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/17.
 */

public class RechargeFragment extends BaseFragment implements OnRefreshLoadMoreListener {
    //下拉刷新View
    SmartRefreshLayout mRefreshLayout;
    private RechargeAdpater rechargeAdpater;
    PagingBaseModel mPagingData;
    //是否正在刷新数据
    boolean isRequestData;
    boolean isInit;

    @Override
    public int getContentResId() {
        return R.layout.fragment_bill;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        isInit = false;
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        initRefresh();
        initEmpty();

        mRefreshLayout.autoRefresh();
        isInit = true;
    }

    /**
     * 初始化下拉刷新View
     */
    private void initRefresh() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    /**
     * 初始化空布局
     */
    private void initEmpty() {
        setVisibilityGone(R.id.empty_layout_empty, false);
        setTvText(R.id.empty_tv_title, "暂无记录");
    }

    /**
     * 刷新适配器
     *
     * @param isRefresh
     */
    private void initRvAdapter(List<TopUp> data, boolean isRefresh) {


        if (rechargeAdpater == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            rechargeAdpater = new RechargeAdpater(data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            if (rv != null) {
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(rechargeAdpater);
            }

        } else {
            rechargeAdpater.notifyData(data, isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
        setVisibilityGone(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 请求数据
     */
    private void requestData(final int curPage, final long timetamp) {
        if (!isRequestData) {
            isRequestData = true;
            PagingParam pagingParam = new PagingParam();
            pagingParam.setCurrentPage(curPage);
            pagingParam.setTimestamp(timetamp);

            App.getServiceManager().getPdmService().TopUpList(1, 4, pagingParam.getMap())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<TopUpList>() {
                        @Override
                        public void onSuccess(BaseResultEntity<TopUpList> obj) {
                            TopUpList data = obj.getResult();
                            initRvAdapter(data.getTopUpList(), curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getTopUpList(), obj.getNowTime());
                            RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                        }


                        @Override
                        public void onUnSuccessFinish() {
//                            initRvAdapter(null, curPage == 1);
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

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {

        @Override
        public void onLoadMore(int nextPage, long timestamp) {
            {
                requestData(nextPage, timestamp);
            }
        }
    });
}

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData(1, mPagingData.getTimestamp());
    }
}

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
import com.mark.app.hjshop4a.ui.consumptionbill.adapter.GoldBeanAdapter;
import com.mark.app.hjshop4a.ui.consumptionbill.adapter.MemberGoldBeanTradeInAdapter;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceWithDraw;
import com.mark.app.hjshop4a.ui.consumptionbill.model.Bean;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BeanList;
import com.mark.app.hjshop4a.ui.consumptionbill.model.MemberGoldBeanTradeIn;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/5/7.
 */

public class MemberGoldBeanTradeInFragment extends BaseFragment implements OnRefreshLoadmoreListener {
    //下拉刷新View
    SmartRefreshLayout mRefreshLayout;
    private MemberGoldBeanTradeInAdapter memberGoldBeanTradeInAdapter;
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
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
    }
    /**
     * 初始化空布局
     */
    private void initEmpty() {
        setVisibilityGone(R.id.empty_layout_empty, false);
        setTvText(R.id.empty_tv_title, "暂无记录");
    }
    @Override
    public void setListener() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 刷新适配器
     *
     *
     * @param isRefresh
     */
    private void initRvAdapter(List<MemberGoldBeanTradeIn> data, boolean isRefresh) {


        if (memberGoldBeanTradeInAdapter == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            memberGoldBeanTradeInAdapter = new MemberGoldBeanTradeInAdapter(data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            if (rv != null) {
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(memberGoldBeanTradeInAdapter);
            }

        } else {
            memberGoldBeanTradeInAdapter.notifyData(data, isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
        setVisibilityGone(R.id.empty_layout_empty, isShowEmpty);
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

            App.getServiceManager().getPdmService().memberBeanTrade(2,6,pagingParam.getMap())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<MemberGoldBeanTradeIn>() {
                        @Override
                        public void onSuccess(BaseResultEntity<MemberGoldBeanTradeIn> obj) {
                            MemberGoldBeanTradeIn data = obj.getResult();
                            if(data!=null) {
//                                initRvAdapter(data.(), curPage == 1);
                                if (mPagingData == null) {
                                    mPagingData = new PagingBaseModel();
                                }
//                                mPagingData.setPagingInfo(curPage, data.getBeanList(), obj.getNowTime());
                            }
                            RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                        }


                        @Override
                        public void onUnSuccessFinish() {
//                            initRvAdapter(new ArrayList<Bean>(), curPage == 1);
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
    public void onLoadmore(RefreshLayout refreshLayout) {
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

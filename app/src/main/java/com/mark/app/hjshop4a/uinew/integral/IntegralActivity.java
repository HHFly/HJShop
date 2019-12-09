package com.mark.app.hjshop4a.uinew.integral;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bill.Adapter.BillAdapter;
import com.mark.app.hjshop4a.uinew.bill.model.BillRecord;
import com.mark.app.hjshop4a.uinew.bill.param.BillPageParam;
import com.mark.app.hjshop4a.uinew.integral.adapter.IntegralAdapter;
import com.mark.app.hjshop4a.uinew.integral.model.IntegralChange;
import com.mark.app.hjshop4a.uinew.integral.param.IntegralParam;
import com.mark.app.hjshop4a.uinew.orderList.PageParam;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class IntegralActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    RefreshLayout refreshLayout;
    PagingBaseModel mPagingData;
    IntegralAdapter mAdapter;

    boolean isRequestData;
    public PagingBaseModel getmPagingData() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        return mPagingData;
    }

    @Override
    public int getContentViewResId() {
       return R.layout.activity_integarl;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"积分详情");
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        requestData(1);
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
    public void requestData( final  int curPage) {

        if(!isRequestData) {
            isRequestData = true;
            IntegralParam integralParam =new IntegralParam();
            integralParam.setPageNo(curPage);
            App.getServiceManager().getmService()
                    .integralRecord(integralParam.toPswJson())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RainbowObserver() {
                        @Override
                        public void onSuccess(RainbowResultEntity obj) {

                            List<IntegralChange> mData = JsonUtils.getList(obj.getResult(), IntegralChange.class);
                            if(mData!=null) {

                                getmPagingData().setPagingInfo(curPage, mData);

                                RefreshLayoutUtils.finish(refreshLayout, getmPagingData());



                                initRvAdapter(mData, curPage == 1);
                            }else {
                                initRvAdapter(null, curPage == 1);
                            }
                        }
                        @Override
                        public void onUnSuccessFinish() {
                            initRvAdapter(null, curPage == 1);

                        }
                        @Override
                        public void onAllFinish() {
                            super.onAllFinish();
//                        hideLoadingDialog();
                            RefreshLayoutUtils.finish(refreshLayout);
                            isRequestData = false;

                        }
                    });
        }
    }

    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter(List<IntegralChange> data, boolean isFresh) {

        RecyclerView rv = getView(R.id.recyclerView);

        if(mAdapter==null) {
            if (rv != null) {
                mAdapter =new IntegralAdapter(data);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(mAdapter);
            }

        }else {
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(mAdapter);
            }
            mAdapter.notifyData(data,isFresh);
        }
        boolean isShowEmpty = isFresh && (data == null || data.size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, getmPagingData(), new RefreshLayoutUtils.OnLoadMoreListener() {

            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                {
                   requestData(nextPage);
                }
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            requestData(1);
    }
}

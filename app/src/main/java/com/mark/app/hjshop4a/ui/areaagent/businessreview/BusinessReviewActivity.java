package com.mark.app.hjshop4a.ui.areaagent.businessreview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.areaagent.billreview.model.AreaBillReview;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.model.BusinessReview;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/21.
 */

public class BusinessReviewActivity extends BaseActivity implements OnRefreshLoadmoreListener{

    SmartRefreshLayout mRefreshLayout;//刷新框架
    BusinessReviewAdapter mAdapter;


    int mSource;//来源
    PagingBaseModel mPagingData;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商户申请");
    }
    @Override
    public void findView() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
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

    private void requestData(final  int curPage,final  long timetamp) {
        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timetamp);
        App.getServiceManager().getPdmService().busunessReview(pagingParam.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BusinessReview>() {
                    @Override
                    public void onSuccess(BaseResultEntity<BusinessReview> obj) {
                        BusinessReview data =obj.getResult();
                        initRvAdapter(data, curPage == 1);
                        if (mPagingData == null) {
                            mPagingData = new PagingBaseModel();
                        }
                        mPagingData.setPagingInfo(curPage,data.getMerchantAuditStayList(),obj.getNowTime());
                        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                    }


                    @Override
                    public void onUnSuccessFinish() {
                        initRvAdapter(new BusinessReview(), curPage == 1);
                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }


                });
    }

    public void initRvAdapter(BusinessReview data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new BusinessReviewAdapter(data,data.getMerchantAuditStayList());
            RecyclerView rv = getView(R.id.recyclerView);
            if (rv != null) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            mAdapter.setOnItemClickListener(new BusinessReviewAdapter.OnItemClickListener() {
                @Override
                public void onClickItemYes() {

                }

                @Override
                public void onClickItemNo() {

                }

                @Override
                public void onClickDetails() {
                    ActivityJumpUtils.actBusinesApply(getAppCompatActivity());
                }
            });
        } else {
            mAdapter.notifyData(data,data.getMerchantAuditStayList(),isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.getMerchantAuditStayList() == null|| data.getMerchantAuditStayList().size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
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

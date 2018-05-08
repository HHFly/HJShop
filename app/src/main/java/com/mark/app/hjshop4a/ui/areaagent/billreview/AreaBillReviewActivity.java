package com.mark.app.hjshop4a.ui.areaagent.billreview;

import android.content.Intent;
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
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.AgentPreformance;
import com.mark.app.hjshop4a.ui.areaagent.billreview.model.AreaBillReview;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.IsPass;
import com.mark.app.hjshop4a.ui.assedetail.model.AssetDetail;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBillReviewActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    SmartRefreshLayout mRefreshLayout;
    PagingBaseModel mPagingData;
    AreaBillReviewAdapter mAdapter;
    private long  startTime;
    private long endTime;



    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }

    @Override
    public void initView() {
            setTvText(R.id.titlebar_tv_title,"辖区报单申请");
//            setTvText(R.id.titlebar_tv_right,"筛选");
        setTvText(R.id.titlebar_tv_right,"");
    }
    @Override
    public void findView() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        startTime=System.currentTimeMillis()/1000;
        endTime=System.currentTimeMillis()/1000;
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
        mRefreshLayout.autoRefresh();
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
//                ActivityJumpUtils.actCalendarView(this,"辖区报单申请");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            startTime=data.getLongExtra("sTime", System.currentTimeMillis()/1000);
            endTime=data.getLongExtra("eTime", System.currentTimeMillis()/1000);
            mRefreshLayout.autoRefresh();
        }

    }
    private void requestData(final  int curPage,final  long timetamp) {
        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timetamp);
        App.getServiceManager().getPdmService().areaBillReview(pagingParam.getMap(),startTime,endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<AreaBillReview>() {
                    @Override
                    public void onSuccess(BaseResultEntity<AreaBillReview> obj) {
                        AreaBillReview data =obj.getResult();
                        if(data!=null&&data.getCustomsList()!=null) {
                            initRvAdapter(data, curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getCustomsList(), obj.getNowTime());
                        }
                        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                    }


                    @Override
                    public void onUnSuccessFinish() {
                        initRvAdapter(new AreaBillReview(), curPage == 1);
                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }


                });
    }
    public void initRvAdapter(AreaBillReview data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new AreaBillReviewAdapter(data,data.getCustomsList());
            RecyclerView rv = getView(R.id.recyclerView);
            if (rv != null) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            mAdapter.setOnItemClickListener(new AreaBillReviewAdapter.OnItemClickListener() {
                @Override
                public void onClickItemYes(String offlineOrderSn) {
                    merchantToAccept(IsPass.YES,offlineOrderSn,"");
                }

                @Override
                public void onClickItemNo(String offlineOrderSn) {
                    merchantToAccept(IsPass.NO,offlineOrderSn,"");
                }

            });
        } else {
            mAdapter.notifyData(data.getCustomsList(), isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.getCustomsList()==null);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    private void merchantToAccept(int  ispass,String offlineOrderSn,String remark){
                showLoadingDialog();
                App.getServiceManager().getPdmService()
                        .proxyToAccept(offlineOrderSn,ispass,remark)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DefaultObserver() {

                            @Override
                            public void onSuccess(BaseResultEntity obj) {
                                    mRefreshLayout.autoRefresh();
                            }

                            @Override
                            public void onUnSuccessFinish() {
                                super.onUnSuccessFinish();
                                ToastUtils.show("审核报单失败，请重试");
                            }

                            @Override
                            public void onAllFinish() {
                                super.onAllFinish();
                                hideLoadingDialog();
                            }
                        });

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

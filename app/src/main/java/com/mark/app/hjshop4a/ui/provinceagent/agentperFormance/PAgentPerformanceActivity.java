package com.mark.app.hjshop4a.ui.provinceagent.agentperFormance;

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
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.AgentPerformanceActivity;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.AgentPreformance;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/5/1.
 */

public class PAgentPerformanceActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    PAgentPrefermanceAdapter mAdapter;
    private long  startTime;
    private long endTime;

    private long cityId;

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
        setViewVisibility(R.id.titlebar_tv_right,false);
    }
    @Override
    public void findView() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        startTime=0;
        endTime=0;
        cityId= App.getAppContext().getUserInfo().getCityId();
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
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
                ActivityJumpUtils.actCalendarView(this,"代理业绩");
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            startTime=data.getLongExtra("sTime", 0);
            endTime=data.getLongExtra("eTime", 0);
            mRefreshLayout.autoRefresh();
        }

    }
    private void requestData(final  int curPage,final  long timetamp) {
        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timetamp);
        App.getServiceManager().getPdmService().agentPerformance(2,pagingParam.getMap(),startTime,endTime,cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<AgentPreformance>() {
                    @Override
                    public void onSuccess(BaseResultEntity<AgentPreformance> obj) {
                        AgentPreformance data =obj.getResult();
                        if(data!=null&&data.getPerformanceProvinceList()!=null) {
                            initRvAdapter(data, curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getPerformanceCityList(), obj.getNowTime());
                        }
                        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                    }


                    @Override
                    public void onUnSuccessFinish() {
                        initRvAdapter(new AgentPreformance(), curPage == 1);
                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }


                });
    }

    public void initRvAdapter(AgentPreformance data, boolean isRefresh) {

        if (mAdapter == null) {
            mAdapter = new PAgentPrefermanceAdapter(data,data.getPerformanceProvinceList());
            RecyclerView rv = getView(R.id.recyclerView);
            if (rv != null) {
                rv.setAdapter(mAdapter);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            mAdapter.setOnItemClickListener(new PAgentPrefermanceAdapter.OnItemClickListener() {
                @Override
                public void onClickDetails(long cityId, long userId) {
                    Intent intent = new Intent(getAppCompatActivity(), AgentPerformanceActivity.class);
                    BundleUtils.getInstance().putLong("cityId",cityId).putLong("userId",userId).addIntent(intent);
                    getAppCompatActivity().startActivity(intent);
                }


            });

        } else {
            mAdapter.notifyData(data,data.getPerformanceProvinceList(), isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null&& data.getPerformanceProvince() == null&&data.getPerformanceCityList()==null && data.getPerformanceCityList().size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
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

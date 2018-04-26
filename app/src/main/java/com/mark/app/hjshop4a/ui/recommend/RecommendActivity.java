package com.mark.app.hjshop4a.ui.recommend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.recommend.model.ZXingCode;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class RecommendActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    private  RecommendAdapter recommendAdapter;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    PagingBaseModel mPagingData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }
    @Override
    public void findView() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
        mRefreshLayout.autoRefresh();
    }
    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"我的推荐");
        setTvText(R.id.titlebar_tv_right,"分享");

    }

    private void initRvAdapter(ZXingCode data,boolean isRefresh) {
        if(recommendAdapter ==null){
            RecyclerView rv = getView(R.id.recyclerView);
            recommendAdapter = new RecommendAdapter(data, data.getRecommendList());
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(recommendAdapter);
        }
        else {
            if(isRefresh) {
                recommendAdapter.notifyData(data, data.getRecommendList(), true);
            }else {
                recommendAdapter.notifyData( data.getRecommendList(), false);
            }
        }

        boolean isShowEmpty = isRefresh && (data == null );
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.titlebar_iv_return:
                finish();
                break;
        }
    }

    /**
     * 请求数据
     */
    private void requestData(final  int curPage,final long timestamp) {
        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timestamp);

        App.getServiceManager().getPdmService()
                .recommend(pagingParam.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ZXingCode>() {


                    @Override
                    public void onSuccess(BaseResultEntity<ZXingCode> obj) {
                        ZXingCode data = obj.getResult();
                        initRvAdapter(data, curPage == 1);
                        if (mPagingData == null) {
                            mPagingData = new PagingBaseModel();
                        }
                        mPagingData.setPagingInfo(curPage,data.getRecommendList(),obj.getNowTime());
                        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                    }
                    @Override
                    public void onUnSuccessFinish() {
//                        initRvAdapter(new ZXingCode(), curPage == 1);
                        RefreshLayoutUtils.finish(mRefreshLayout);

                    }
                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();


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
        requestData(1,0);
    }
}

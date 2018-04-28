package com.mark.app.hjshop4a.ui.bankcard.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.bankcard.adapter.BankCardAdapter;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCards;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCardActivity extends BaseActivity implements OnRefreshLoadmoreListener{
    private BankCardAdapter bankCardAdapter;
    private BankCards bankCards ;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    PagingBaseModel mPagingData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
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
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"银行卡管理");
        setTvText(R.id.titlebar_tv_right,"新增");

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
//                添加银行卡
                Intent intent = new Intent(this, BankCardAddActivity.class);
                this.startActivity(intent);
                this.overridePendingTransition(0,0);
                break;
        }
    }
    private void delete(BankCard data){
        App.getServiceManager().getPdmService().editBnakCard(2,"",data.getBankName(),"",data.getBankNo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        ToastUtils.show("删除成功");
                        mRefreshLayout.autoRefresh();
                    }


                    @Override
                    public void onUnSuccessFinish() {
                        ToastUtils.show("删除失败");
                    }


                });
    }

    private  void requestData(final  int curPage,final  long timetamp){


        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timetamp);
        App.getServiceManager().getPdmService().getBankList(pagingParam.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ArrayList<BankCard>>() {
                    @Override
                    public void onSuccess(BaseResultEntity<ArrayList<BankCard>> obj) {
                        ArrayList<BankCard> data =obj.getResult();
                        initRvAdapter(data, curPage == 1);
                        if (mPagingData == null) {
                            mPagingData = new PagingBaseModel();
                        }
                        mPagingData.setPagingInfo(curPage,data,obj.getNowTime());
                        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                    }


                    @Override
                    public void onUnSuccessFinish() {
                        initRvAdapter(null, curPage == 1);
                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }


                });


    }
    private void initRvAdapter(  ArrayList<BankCard> data,boolean isRefresh){

        if(bankCardAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            bankCardAdapter = new BankCardAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(bankCardAdapter);
            bankCardAdapter.setOnItemClickListener(new BankCardAdapter.OnItemClickListener() {
                @Override
                public void onClickDelete(BankCard data) {
                    delete(data);
                }
            });
        }
        else {
            bankCardAdapter.notifyData(data,true);
        }
        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {

            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                {
                    requestData(nextPage,timestamp);
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData(1,mPagingData.getTimestamp());
    }
}

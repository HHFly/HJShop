package com.mark.app.hjshop4a.uinew.bindinfo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;

import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bindinfo.adapter.TaobaoAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfo;
import com.mark.app.hjshop4a.uinew.userinfo.model.BindStatus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BindTaobaoActivity extends BaseActivity {
    private TaobaoAdapter mAdapter ;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_taobaolist;
    }

    @Override
    public void findView() {
        mRefreshLayout =getView(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData();
            }
        });
        requestData();
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.账号列表);

    }

    @Override
    public void onUnFirstResume() {
        requestData();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_iv_add);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.titlebar_iv_add:
                ActivityJumpUtils.actAddTaobao(this,2,-1);
                break;
        }
    }
    //请求数据
    private void requestData() {
        App.getServiceManager().getmService()
                .getAcccountInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<List<AccountInfo>>() {

                    @Override
                    public void onNullResult() {
                        setViewVisibility(R.id.empty_layout_empty, true);
                    }

                    @Override
                    public void onSuccess(RainbowResultEntity<List<AccountInfo>> obj) {
                        if(obj.getResult()!=null) {
                            List<AccountInfo> data = JsonUtils.getList(obj.getResult(), AccountInfo.class);
                            initRvAdapter(data, true);
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(final List<AccountInfo> data, boolean isRefresh){

        if(mAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            mAdapter = new TaobaoAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new TaobaoAdapter.OnItemClickListener() {
                @Override
                public void onClickShow(AccountInfo data) {
                    ActivityJumpUtils.actAddTaobao(getActivity(),1,data.getBuyerAccountId());
                }

                @Override
                public void onClickUpdate(AccountInfo data) {
                    ActivityJumpUtils.actAddTaobao(getActivity(),0,data.getBuyerAccountId());
                }

            });
        }
        else {
            mAdapter.notifyData(data,isRefresh);
        }
        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== ActResultCode.RESULT_OK){
            mRefreshLayout.autoRefresh();
        }
    }
}

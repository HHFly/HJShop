package com.mark.app.hjshop4a.uinew.invitation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.invitation.adapter.InviteBountyRecordAdapter;
import com.mark.app.hjshop4a.uinew.invitation.adapter.InviteDetailAdapter;
import com.mark.app.hjshop4a.uinew.invitation.model.InviteBountyRecord;
import com.mark.app.hjshop4a.uinew.invitation.model.InviteDetailPage;
import com.mark.app.hjshop4a.uinew.invitation.param.InviteParam;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class InviteBountyRecordActivity  extends BaseActivity implements OnRefreshLoadMoreListener {
    RefreshLayout refreshLayout;
    PagingBaseModel mPagingData;
    InviteBountyRecordAdapter inviteBountyRecordAdapter;
    long id ;
    public PagingBaseModel getmPagingData() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        return mPagingData;
    }
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        id =bundle.getLong(BundleKey.ID);
    }
    @Override
    public int getContentViewResId() {
        return R.layout.activity_invitedetail;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.查看明细);
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
    @Override
    public void findView() {
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(this);
    }

    /**
     /**
     * 请求数据
     */
    private void requestData(final  int curPage) {
//        showLoadingDialog();
        InviteParam inviteParam=new InviteParam();
        inviteParam.setPageNo(curPage);
        inviteParam.setUserId(id);
        App.getServiceManager().getmService()
                .inviteDetail(inviteParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        List<InviteBountyRecord> mData = JsonUtils.getList(obj.getResult(),InviteBountyRecord.class);
                        //设置信息
                        if(mData!=null) {

                            getmPagingData().setPagingInfo(curPage, mData);

                            RefreshLayoutUtils.finish(refreshLayout, getmPagingData());



                            initRvAdapter(mData, curPage == 1);
                        }else {
                            initRvAdapter(null, curPage == 1);
                        }
//                        twoSearchAdapter.startTime();
                    }
                    @Override
                    public void onUnSuccessFinish() {
                        initRvAdapter(null, curPage == 1);

                    }
                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        RefreshLayoutUtils.finish(refreshLayout);
                    }
                });
    }
    private void initRvAdapter(final   List<InviteBountyRecord> data, boolean isFresh){
        RecyclerView rv = getView(R.id.recyclerView);
        if(inviteBountyRecordAdapter==null){

            inviteBountyRecordAdapter = new InviteBountyRecordAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(inviteBountyRecordAdapter);

        }
        else {
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(inviteBountyRecordAdapter);
            }
            inviteBountyRecordAdapter.notifyData(data,isFresh);
        }
        boolean isShowEmpty = isFresh && (data == null || data.size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, getmPagingData(), new RefreshLayoutUtils.OnLoadMoreListener() {

            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                requestData(nextPage);
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestData(1);
    }

}

package com.mark.app.hjshop4a.uinew.invitation;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.invitation.adapter.InvitationAdapter;
import com.mark.app.hjshop4a.uinew.invitation.model.InvitePage;
import com.mark.app.hjshop4a.uinew.performorder.adapter.ThreeBrowseAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThree;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvitationActivity extends BaseActivity{
    SmartRefreshLayout mRefreshLayout;
    InvitationAdapter invitationAdapter;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_invitation;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.邀请好友);
        requestData();
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
        mRefreshLayout =getView(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData();
            }
        });
    }
    /**
    /**
     * 请求数据
     */
    private void requestData() {
//        showLoadingDialog();

        App.getServiceManager().getmService()
                .invitePage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        InvitePage data = JsonUtils.fromJson(obj.getResult(),InvitePage.class);
                        //设置信息
                        initRvAdapter(data);
//                        twoSearchAdapter.startTime();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(final InvitePage data){

        if(invitationAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            invitationAdapter = new InvitationAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(invitationAdapter);
            invitationAdapter.setOnItemClickListener(new InvitationAdapter.OnItemClickListener() {
                @Override
                public void onClickCopy() {

                }

                @Override
                public void onClickDetial() {
                    ActivityJumpUtils.actActivity(getActivity(),InviteDetailActivity.class);
                }
            });

        }
        else {
            invitationAdapter.setData(data);
            invitationAdapter.notifyDataSetChanged();
        }
//        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
//        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
}

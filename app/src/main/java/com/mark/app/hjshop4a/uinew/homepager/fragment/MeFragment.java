package com.mark.app.hjshop4a.uinew.homepager.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;


import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.uinew.login.activity.LoginSwitchActivity;
import com.mark.app.hjshop4a.uinew.homepager.adapter.MeAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import javax.xml.transform.Result;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MeFragment extends BaseFragment{
    private final static int REQUESTCODE = 1; // 返回的结果码
    private MeAdapter mAdapter;
    RefreshLayout refreshLayout;
     UserCenter mData =new UserCenter();
    @Override
    public int getContentResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void findView() {
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    requestData();
            }
        });
    }

    @Override
    public void onUnFirstResume() {
        if(App.getAppContext().getUserInfo()==null){
            requestData();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case -1:
                requestData();
                break;
        }
    }

    @Override
    public void setListener() {

        setClickListener(R.id.titlebar_tv_title);
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,R.string.home_我的);

//        initRvAdapter();
        if(App.getAppContext().getUserInfo()!=null){
            initRvAdapter(App.getAppContext().getUserInfo());
        }
        requestData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.titlebar_tv_title:
                ActivityJumpUtils.actLogin(getActivity());
                break;
        }
    }

    /**
     * 请求数据
     */
    private void requestData( ) {
//        showLoadingDialog();
        App.getServiceManager().getmService()
                .getCenter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<UserCenter>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<UserCenter> obj) {
                       UserCenter mData = JsonUtils.fromJson(obj.getResult(),UserCenter.class);
                        initRvAdapter(mData);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
//                        hideLoadingDialog();
                        RefreshLayoutUtils.finish(refreshLayout);
                    }
                });
    }

    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter(UserCenter data) {

        RecyclerView rv = getView(R.id.recyclerView);
       if(mAdapter==null) {
           if (rv != null) {
               mAdapter =new MeAdapter();
               rv.setLayoutManager(new LinearLayoutManager(getContext()));
               rv.setAdapter(mAdapter);
           }
           mAdapter.setOnItemClickListener(new MeAdapter.OnItemClickListener() {
               @Override
               public void onClickUserInfo() {
                   ActivityJumpUtils.actUserInfo(getActivity());
               }

               @Override
               public void onClickUserPic(int id) {
                   FunctionDialogFactory.showTakePhoneIDDialog(getActivity(), id);
               }


           });
       }else {
           mAdapter.setmData(data);
           mAdapter.notifyDataSetChanged();
       }
    }
}

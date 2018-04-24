package com.mark.app.hjshop4a.ui.homepager.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.bankcard.model.InfoBank;
import com.mark.app.hjshop4a.ui.homepager.adapter.MeAdapter;
import com.mark.app.hjshop4a.ui.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.ui.login.activity.LoginSwitchActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页-我的
 * Created by lenovo on 2017/8/27.
 */

public class MeFragment extends BaseFragment {
    private MeAdapter mAdapter;
    private final static int REQUESTCODE = 1; // 返回的结果码

    @Override
    public void onResume() {
        super.onResume();
//        requestData(App.getAppContext().getRoleType());
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_tv_right);


    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_right,"切换账号");
        initRvAdapter(RoleType.PROVINCIALAGENT,true,new MeCenterInfo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_tv_right:
//            ActivityJumpUtils.actLoginSwicth(getActivity() );
//                退出
                Intent intent = new Intent(getActivity(), LoginSwitchActivity.class);
                this.startActivityForResult(intent,REQUESTCODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            LoginType loginType= (LoginType) data.getSerializableExtra("LoginType");
            initRvAdapter(loginType.getRoleType(),false,new MeCenterInfo());
        }

    }


    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter( int role,boolean isRefresh ,MeCenterInfo data) {
            RecyclerView rv = getView(R.id.recyclerView);
            switch (role){
                case RoleType.MEMBER: mAdapter = new MeAdapter(role,getActivity(),data);break;
                case RoleType.BUSINESS: mAdapter = new MeAdapter(role,getActivity(),data);break;
                case RoleType.AREAAGENT: mAdapter = new MeAdapter(role,getActivity(),data);break;
                case RoleType.PROVINCIALAGENT: mAdapter = new MeAdapter(role,getActivity(),data);break;
            }
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(mAdapter);


    }
    /**
     * 请求数据
     */
    private void requestData(final int roletype) {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .center(roletype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<MeCenterInfo>() {


                    @Override
                    public void onSuccess(BaseResultEntity<MeCenterInfo> obj) {
                        MeCenterInfo data = obj.getResult();
                        initRvAdapter(roletype,false,data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}

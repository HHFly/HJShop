package com.mark.app.hjshop4a.ui.homepager.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.homepager.adapter.MeAdapter;
import com.mark.app.hjshop4a.ui.login.activity.LoginSwitchActivity;

/**
 * 首页-我的
 * Created by lenovo on 2017/8/27.
 */

public class MeFragment extends BaseFragment {
    private MeAdapter mAdapter;
    private final static int REQUESTCODE = 1; // 返回的结果码
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
        initRvAdapter(RoleType.BUSINESS,true);
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
            initRvAdapter(loginType.getRoleType(),false);
        }

    }


    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter( int role,boolean isRefresh) {
            RecyclerView rv = getView(R.id.recyclerView);
            switch (role){
                case RoleType.MEMBER: mAdapter = new MeAdapter(role,getActivity());break;
                case RoleType.BUSINESS: mAdapter = new MeAdapter(role,getActivity());break;
                case RoleType.AREAAGENT: mAdapter = new MeAdapter(role,getActivity());break;
                case RoleType.PROVINCIALAGENT: mAdapter = new MeAdapter(role,getActivity());break;
            }
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(mAdapter);


    }

}

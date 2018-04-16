package com.mark.app.hjshop4a.ui.homepager.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.ui.homepager.adapter.MeAdapter;

/**
 * 首页-我的
 * Created by lenovo on 2017/8/27.
 */

public class MeFragment extends BaseFragment {
    private MeAdapter mAdapter;

    @Override
    public int getContentResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_tv_return);

    }

    @Override
    public void initView() {
        initRvAdapter(RoleType.MEMBER,true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_tv_return:
            ActivityJumpUtils.actLoginSwicth(getActivity());
//                退出
                break;
        }
    }
    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter( int role,boolean isRefresh) {

        if (mAdapter == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            switch (role){
                case RoleType.MEMBER: mAdapter = new MeAdapter(role,getActivity());break;
                case RoleType.BUSINESS: mAdapter = new MeAdapter(role,getActivity());break;
                case RoleType.AREAAGENT: mAdapter = new MeAdapter(role,getActivity());break;
                case RoleType.PROVINCIALAGENT: mAdapter = new MeAdapter(role,getActivity());break;
            }

            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(mAdapter);
            setRvListener();
        } else {
//            mAdapter.notifyData(int role,boolean isRefresh);

            mAdapter.notifyDataSetChanged();
        }
    }
    /**
     * 设置监听
     */
    private void setRvListener() {
        mAdapter.setOnItemClickListener(new MeAdapter.OnItemClickListener() {
            @Override
            public void onClickUserInfo() {
                ActivityJumpUtils.actLogin(getActivity());
            }


        });
    }
}

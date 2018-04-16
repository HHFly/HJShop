package com.mark.app.hjshop4a.ui.login.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.login.adapter.LoginSwitchAdapter;

import java.util.ArrayList;

/**
 * Created by hui on 2018/4/15.
 */

public class LoginSwitchActivity extends BaseActivity {
    private LoginSwitchAdapter mAdapter;
        private ArrayList<LoginType> loginTypes =new ArrayList<>();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_login_switch;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.login_账号管理);
        initRvAdapter(false);
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_iv_return:
                finish();
                break;
        }
    }

    /**
     * 初始化adapter
     */
    private void initRvAdapter( boolean isRefresh) {

        LoginType a = new LoginType();
        a.setTpye("省代理账号");
        a.setPhone("15151515151");
        loginTypes.add(a);
        a = new LoginType();
        a.setTpye("区代理账号");
        a.setPhone("15151515151");
        loginTypes.add(a);
        a = new LoginType();
        a.setTpye("商家账号");
        a.setPhone("15151515151");
        loginTypes.add(a);
        a = new LoginType();
        a.setTpye("会员账号");
        a.setPhone("15151515151");
        loginTypes.add(a);
        if (mAdapter == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            mAdapter = new LoginSwitchAdapter(loginTypes);

            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(mAdapter);
            setRvListener();
        }else {
            mAdapter.notifyData(loginTypes,true);
        }
    }

    private void setRvListener() {
        mAdapter.setOnItemClickListener(new LoginSwitchAdapter.OnItemClickListener() {
            @Override
            public void onClickUserInfo(LoginType data) {
                finish();
            }
        });
    }
}
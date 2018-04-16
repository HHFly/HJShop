package com.mark.app.hjshop4a.login.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.login.adapter.LoginSwitchAdapter;

import static java.security.AccessController.getContext;

/**
 * Created by hui on 2018/4/15.
 */

public class LoginSwitchActivity extends BaseActivity {
    private LoginSwitchAdapter mAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_login_switch;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.login_账号管理);
    }

    @Override
    public void setClickListener(int id) {
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
    private void initRvAdapter(int role, boolean isRefresh) {

        if (mAdapter == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            mAdapter = new LoginSwitchAdapter();

            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(mAdapter);
            setRvListener();
        }
    }

    private void setRvListener() {
    }
}
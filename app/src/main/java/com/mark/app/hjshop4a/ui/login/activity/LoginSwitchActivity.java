package com.mark.app.hjshop4a.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.model.login.AreaAgentInfo;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.ui.login.adapter.LoginSwitchAdapter;
import com.white.lib.utils.intent.BundleUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
         LoginType loginType1=new LoginType(App.getAppContext().getMemberInfo());
        LoginType loginType2=new LoginType(App.getAppContext().getBusniessInfo());
        LoginType loginType3=new LoginType(App.getAppContext().getAreaAgentInfo());
        LoginType loginType4=new LoginType(App.getAppContext().getProvenceAgentInfo());
        loginTypes.add(loginType1);
        loginTypes.add(loginType2);
        loginTypes.add(loginType3);
        loginTypes.add(loginType4);

        initRvAdapter();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.hm_layout_logout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(App.hasToken()) {
            finish();
        }else {
            ActivityJumpUtils.actHomePager(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_iv_return:
                if(App.hasToken()) {
                    finish();
                }else {
                    ActivityJumpUtils.actHomePager(this);
                }
                break;
            case R.id.hm_layout_logout:
                App.get().setLogin(null);
                requestData();
                ActivityJumpUtils.actHomePager(this);
                finish();
                break;
        }
    }

    /**
     * 初始化adapter
     */
    private void initRvAdapter( ) {


        if (mAdapter == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            mAdapter = new LoginSwitchAdapter(loginTypes,this);

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
                App.get().setLogin(null);
                requestData();
                Intent intent =new Intent(getAppCompatActivity(),LoginActivity.class);
                BundleUtils.getInstance().putInt("RoleType",data.getRoleType()).putString("Phone",data.getPhone()).addIntent(intent);
                startActivity(intent);
            }
        });
    }

    /**
     * 请求数据登出
     */
    private void requestData() {
        App.getServiceManager().getPdmService()
                .logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {

                    @Override
                    public void onSuccess(BaseResultEntity obj) {

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
package com.mark.app.hjshop4a.ui.onlinerecharge;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.pay.PayType;
import com.mark.app.hjshop4a.model.onlinerecharhe.Pay;
import com.mark.app.hjshop4a.model.onlinerecharhe.PayInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/17.
 */

public class OnlineRechargeActivity extends BaseActivity {
    private OnlineRechargeRvAdapter mAdapter;
    private PayInfo mData;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_onlinerecharge;
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }
    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, "在线充值");
        initRvAdapter(new PayInfo());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_iv_return: {
                finish();
                break;
            }
        }
    }
    /**
     * 初始化列表
     *
     * @param data
     */
    private void initRvAdapter(PayInfo data) {
        List<Pay> list = new ArrayList<>();
       Pay a  =new Pay();
       a.setPayType(PayType.ALIPAY);
       a.setPayWayName("支付宝");
       list.add(a);
       a =new Pay();
        a.setPayType(PayType.WECHAT);
        a.setPayWayName("微信");
        list.add(a);
        if (mAdapter == null) {
            mAdapter = new OnlineRechargeRvAdapter(data, list);
            RecyclerView rv = getView(R.id.recyclerView);
            rv.setAdapter(mAdapter);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mAdapter.setOnItemClickListener(new OnlineRechargeRvAdapter.OnItemClickListener() {
                @Override
                public void onClickPay(Pay data) {

                }
            });
        } else {
            mAdapter.notifyData(data, list, true);
        }
    }
}

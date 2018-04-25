package com.mark.app.hjshop4a.ui.onlinerecharge;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.pay.PayType;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.model.onlinerecharhe.Pay;
import com.mark.app.hjshop4a.model.onlinerecharhe.PayInfo;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.BeanConsume;
import com.mark.app.hjshop4a.ui.onlinerecharge.model.OnlineRecharge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .onLineGet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ArrayList<OnlineRecharge>>() {


                    @Override
                    public void onSuccess(BaseResultEntity<ArrayList<OnlineRecharge>> obj) {
                        ArrayList<OnlineRecharge> data = obj.getResult();
                        initRvAdapter(data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /*
    *提交
    * */
    private void commit(OnlineRecharge data, String count ) {

        if(TextUtils.isEmpty(count)){
            ToastUtils.show(getApplicationContext(),"请输入充值金额");
            return;
        }

        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .onLine(count,data.getPayWayCode())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        requestData();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /**
     * 初始化列表
     *
     * @param data
     */
    private void initRvAdapter( ArrayList<OnlineRecharge> data) {

        if (mAdapter == null) {
            mAdapter = new OnlineRechargeRvAdapter(data);
            RecyclerView rv = getView(R.id.recyclerView);
            rv.setAdapter(mAdapter);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mAdapter.setOnItemClickListener(new OnlineRechargeRvAdapter.OnItemClickListener() {
                @Override
                public void onClickPay(OnlineRecharge data, String count) {
                            commit(data,count);
                }

            });
        } else {
            mAdapter.notifyData(data, true);
        }
    }
}

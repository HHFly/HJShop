package com.mark.app.hjshop4a.ui.assedetail;

import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.assedetail.model.AssetDetail;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/17.
 */

public class AssetDetailActivity extends BaseActivity {
    private int role;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_asset_detail;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
         role =bundle.getInt(BundleKey.ROLE);
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"资产明细");
        initRole();
        requestData(role);
    }

    private void initRole() {
        switch (role){
            case RoleType.MEMBER:
                setViewVisibility(R.id.declaration_income,false);
        }
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
    /**
     * 请求数据
     */
    private void requestData(final int roletype) {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .getAssetDetail(roletype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<AssetDetail>() {


                    @Override
                    public void onSuccess(BaseResultEntity<AssetDetail> obj) {
                        AssetDetail data = obj.getResult();
                        bindData(data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    private void bindData(AssetDetail data){
        setTvText(R.id.account_balance,data.getAccountBalance());
        setTvText(R.id.canuse_account_balance,data.getAccountBalanceUsable());
        setTvText(R.id.frozen_amount,data.getAccountBalanceFreeze());
        setTvText(R.id.invite,data.getInvite());
        setTvText(R.id.account_gold_bean,data.getBeanUsable());
        setTvText(R.id.given_gold_bean,data.getBeanStayAway());
        setTvText(R.id.gived_gold_bean,data.getBeanHasPresented());
        setTvText(R.id.money_tree,data.getTreeHad());
        setTvText(R.id.money_tree_get,data.getTreeIncomeDay());
        setTvText(R.id.total_income,data.getCustomsTotal());
        setTvText(R.id.yesterday_income,data.getCustomsYestoday());
    }
}

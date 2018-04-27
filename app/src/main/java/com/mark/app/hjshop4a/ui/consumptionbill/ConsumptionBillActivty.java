package com.mark.app.hjshop4a.ui.consumptionbill;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.consumptionbill.BillTabType;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.FragmentUtils;
import com.mark.app.hjshop4a.ui.consumptionbill.fragment.BalanceFragment;
import com.mark.app.hjshop4a.ui.consumptionbill.fragment.BalanceWithDrawFragment;
import com.mark.app.hjshop4a.ui.consumptionbill.fragment.BeanTradeInFragment;
import com.mark.app.hjshop4a.ui.consumptionbill.fragment.GoldBeanFragment;
import com.mark.app.hjshop4a.ui.consumptionbill.fragment.RechargeFragment;

/**
 * Created by pc on 2018/4/17.
 */

public class ConsumptionBillActivty extends BaseActivity {
    //当前选中的fragment
    private BaseFragment mCurFragment;
    //fragment
    private GoldBeanFragment mFragmentGoldBean;
    private BalanceFragment mFragmentBalance;
    private RechargeFragment mFragmentRecharge;
    private BalanceWithDrawFragment mFragmentBalanceWithDraw;
    private BeanTradeInFragment mFragmentBeanTradeIn;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_consumptionbill;
    }
    //类型
    int mType =0;
//    角色
    int Role ;

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        Role= App.getAppContext().getRoleType();
    }

    @Override
    public void initView() {
        switch (Role){
            case RoleType.MEMBER:
                setTvText(R.id.titlebar_tv_title,"消费账单");
                setViewVisibility(R.id.text4,false);
                setViewVisibility(R.id.text5,false);
                break;
            case RoleType.BUSINESS:
                setTvText(R.id.titlebar_tv_title,"申请记录");
                setViewVisibility(R.id.text1,false);
                setViewVisibility(R.id.text2,false);
        }


    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mCurFragment == null) {
            initFragmentList();
            selectTab(mType);
        } else {
//            mCurFragment.updateData();
        }
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.text1);
        setClickListener(R.id.text2);
        setClickListener(R.id.text3);
        setClickListener(R.id.text4);
        setClickListener(R.id.text5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.text1:
            case R.id.text2:
            case R.id.text3:
            case R.id.text4:
            case R.id.text5:
                selectTab(v);
                break;
        }
    }
    /**
     * 初始化fragment列表
     */
    private void initFragmentList() {
        mFragmentGoldBean =new GoldBeanFragment();
        mFragmentBalance = new BalanceFragment();
        mFragmentRecharge = new RechargeFragment();
        mFragmentBalanceWithDraw= new BalanceWithDrawFragment();
        mFragmentBeanTradeIn= new BeanTradeInFragment();
    }

    /**
     * 选择tab
     *
     * @param type
     */
    private void selectTab(int type) {
        switch (type) {
            default:
            case BillTabType.GOLDBEAN: {
                //金豆消费
                View view = getView(R.id.text1);
                selectTab(view);
                break;
            }
            case BillTabType.BALANCE: {
                //余额消费
                View view = getView(R.id.text2);
                selectTab(view);
                break;
            }
            case BillTabType.RECHARGE: {
                //充值
                View view = getView(R.id.text3);
                selectTab(view);
                break;

            }
            case BillTabType.BEANTRADEIN:{
                //金豆兑换
                View view = getView(R.id.text4);
                selectTab(view);
                break;
            }
            case BillTabType.BALANCEWITHDRAW:{
                //金豆兑换
                View view = getView(R.id.text5);
                selectTab(view);
                break;
            }
        }
    }

    /**
     * 选择tab
     *
     * @param view
     */
    public void selectTab(View view) {
        setViewSelected(R.id.text1, false);
        setViewSelected(R.id.text2, false);
        setViewSelected(R.id.text3, false);
        setViewSelected(R.id.text4, false);
        setViewSelected(R.id.text5, false);
        setViewSelected(view, true);
        switch (view.getId()) {
            case R.id.text1:
                switchFragment(mFragmentGoldBean);
                break;
            case R.id.text2:
                switchFragment(mFragmentBalance);
                break;
            case R.id.text3:
                switchFragment(mFragmentRecharge);
                break;
            case R.id.text4:
                switchFragment(mFragmentBeanTradeIn);
                break;
            case R.id.text5:
                switchFragment(mFragmentBalanceWithDraw);
                break;
        }
    }

    /**
     * 选择fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        if (mCurFragment != fragment) {
//            SoundService.stopIntent(this);
        }
        mCurFragment = FragmentUtils.selectFragment(this, mCurFragment, fragment, R.id.content);
    }

}

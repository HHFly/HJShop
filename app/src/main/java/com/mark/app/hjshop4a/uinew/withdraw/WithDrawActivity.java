package com.mark.app.hjshop4a.uinew.withdraw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.withdraw.WithDrawRole;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;

import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.NumberUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bindinfo.BankCardAddActivity;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.dialog.BankCardDialog;

import com.mark.app.hjshop4a.uinew.dialog.PswDialog;
import com.mark.app.hjshop4a.uinew.performorder.model.EvaluationInfo;
import com.mark.app.hjshop4a.uinew.userinfo.ModifyPayPwActivity;
import com.mark.app.hjshop4a.uinew.withdraw.model.WithdrawPage;
import com.mark.app.hjshop4a.uinew.withdraw.param.WithDrawParam;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/20.
 */

public class WithDrawActivity extends BaseActivity {
    private static BankCardDialog bankCardDialog;
    private Activity activity;
    private ArrayList<BankCard> bankCards;
    private BankCard currentCard;
    private WithdrawPage withDrawData;//可提现金额
    private PswDialog pswDialog;
    private boolean hasCard =false;
    double canuser =0;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();

    }

    @Override
    public void initView() {
        this.activity=this;
        setTvText(R.id.titlebar_tv_title,"提现");
        pswDialog =PswDialog.getInstance();
        pswDialog.setOnDialogClickListener(new PswDialog.OnDialogClickListener() {
            @Override
            public void onClickCancel(PswDialog dialog) {

            }

            @Override
            public void onClickCommit(PswDialog dialog, String data) {
                if (TextUtils.isEmpty(data)) {
                    ToastUtils.show("请输入支付密码");
                    return;
                }
                if(!ValidUtils.verifyCode(data)){
                    ToastUtils.show("密码为6位数字");
                    return;
                }
                commit(data);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.button);
        setClickListener(R.id.withdraw_bankcard);
        setClickListener(R.id.tv_withdraw_gold_balance);
        setClickListener(R.id.tv_withdraw_goods_balance);
        setClickListener(R.id.tv_withdraw_password_reset);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.button:
//                提交
//                commit();
                pswDialog.show(getFragmentManager());
                break;

            case R.id.withdraw_bankcard:
                ActivityJumpUtils.actBankCard(this);
                break;
            case R.id.tv_withdraw_gold_balance:
                if(withDrawData==null)
                    return;
                setTvText(R.id.et_gold,withDrawData.getGold());
                break;
            case R.id.tv_withdraw_goods_balance:
                if(withDrawData==null)
                    return;
                setTvText(R.id.et_goods,withDrawData.getBalance());
                break;
            case R.id.tv_withdraw_password_reset:
                ActivityJumpUtils.actActivity(App.get().getCurActivity(), ModifyPayPwActivity.class);
                break;
        }

    }

    private void commit(String payPasswd) {

            String money = getTvText(R.id.et_goods);
            String gold = getTvText(R.id.et_gold);
//            String payPasswd = getTvText(R.id.et_withdraw_password);
            if (TextUtils.isEmpty(gold)) {
                ToastUtils.show("请输入提现金币金额");
                return;
            }
            if (TextUtils.isEmpty(money)) {
                ToastUtils.show("请输入提现货款金额");
                return;
            }
             if (TextUtils.isEmpty(payPasswd)) {
                 ToastUtils.show("请输入支付密码");
                 return;
             }
             if(!ValidUtils.verifyCode(payPasswd)){
                  ToastUtils.show("密码为6位数字");
                  return;
            }

            if (Double.parseDouble(money)<1){
                ToastUtils.show("可提现货款金额不足1元");
                return;
            }
            showLoadingDialog();
        WithDrawParam withDrawParam =new WithDrawParam();
        withDrawParam.setGold(gold);
        withDrawParam.setMoney(money);
        withDrawParam.setPayPasswd(payPasswd);
        App.getServiceManager().getmService()
                .withdraw(withDrawParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        ToastUtils.show("申请成功");
                        requestData();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){

        }

    }

    private  void requestData(){
//        showLoadingDialog();
        App.getServiceManager().getmService().getWithdrawPage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {
                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        withDrawData = JsonUtils.fromJson(obj.getResult(),WithdrawPage.class);

                        initRvAdapter(withDrawData);

                    }


                    @Override
                    public void onUnSuccessFinish() {
                        hideLoadingDialog();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
//                        hideLoadingDialog();
                    }
                });



    }
    @SuppressLint("StringFormatInvalid")
    private void initRvAdapter(WithdrawPage data) {
        if(data==null){
            return;
        }
        setTvText(R.id.gold_coin_balance, data.getGold());
        setTvText(R.id.goods_balance, data.getBalance());
        EditText et =getView(R.id.et_gold);
        et.setHint(String.valueOf(data.getScale())+"%手续费");

        if(1==data.getBindBankFlage()) {
            setTvText(R.id.tv_bank_num,data.getAccountNumber());
        }else {
            setTvText(R.id.tv_bank_num, "请先绑定银行卡");
        }

    }





}

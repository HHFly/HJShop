package com.mark.app.hjshop4a.ui.withdraw;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.withdraw.WithDrawRole;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;

import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.bankcard.activity.BankCardActivity;
import com.mark.app.hjshop4a.ui.bankcard.activity.BankCardAddActivity;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.dialog.BankCardDialog;
import com.mark.app.hjshop4a.ui.withdraw.model.WithDraw;

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
    private WithDraw withDrawData;//可提现金额
    @Override
    public int getContentViewResId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData(1);
    }

    @Override
    public void initView() {
        this.activity=this;
        setTvText(R.id.titlebar_tv_title,"提现");


    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.button);
        setClickListener(R.id.item_bank_card);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.button:
//                提交
                commit();
                break;
            case R.id.item_bank_card:
            showBankCardDialog();
//                Intent intent =new Intent(this, BankCardActivity.class);
//                this.startActivityForResult(intent,1);
                break;
        }

    }
    private  int  SwitchRole(int Role){
        switch (Role){
            case RoleType.MEMBER:
                return 0;
            case RoleType.BUSINESS:
                 return WithDrawRole.BUSNIESS;
            case RoleType.AREAAGENT:
                return WithDrawRole.AREAGENT;
            case RoleType.PROVINCIALAGENT:
                return WithDrawRole.PAREAGENT;
        }
        return 0;
    }
    private void commit() {
        String withDraw =getTvText(R.id.withDraw);
        String remark =getTvText(R.id.remark);
        String bankId =String.valueOf(currentCard.getBankId());
        if (TextUtils.isEmpty(withDraw)){
            ToastUtils.show("请输入金额");
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getPdmService().withDraw(SwitchRole(App.getAppContext().getRoleType()),withDraw,bankId,currentCard.getBankNo(),remark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        ToastUtils.show("成功提交申请");
                       setTvText(R.id.withDraw,"");
                       setTvText(R.id.remark,"");
                    }


                    @Override
                    public void onUnSuccessFinish() {

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

    private  void requestData(final  int curPage){
        showLoadingDialog();
        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);

        App.getServiceManager().getPdmService().getBankList(pagingParam.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ArrayList<BankCard>>() {
                    @Override
                    public void onSuccess(BaseResultEntity<ArrayList<BankCard>> obj) {
                        bankCards =obj.getResult();
                        if(bankCards!=null&bankCards.size()!=0) {
                            currentCard =bankCards.get(0);
                            bankCards.get(0).setSelect(true);
                            setSdvSmall(R.id.iv_bnak_card, bankCards.get(0).getBankPic());
                            setTvText(R.id.item_tv_bank_name, bankCards.get(0).getBankName());
                            requestWithDrawCash();
                        }else {
                            ToastUtils.show("请先添加银行卡");
                            ActivityJumpUtils.actBankCard(getAppCompatActivity());
                            finish();
                        }

                    }


                    @Override
                    public void onUnSuccessFinish() {
                        hideLoadingDialog();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });



    }

    private void showBankCardDialog() {

        bankCardDialog =BankCardDialog.getInstance(bankCards,this);
        bankCardDialog.setOnBtnClickListenr(new BankCardDialog.OnBtnClickListenr() {
            @Override
            public void onItemClick(View view, BankCard data, int position) {
                currentCard =data;
                selectBank(position);
                setTvText(R.id.item_tv_bank_name,data.getBankName());
                setSdvSmall(R.id.sdv_bnak_card,data.getBankPic());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void addbank() {
                ActivityJumpUtils.actActivity(activity, BankCardAddActivity.class);

            }
        });
        bankCardDialog.show(getFragmentManager());
    }

    private void selectBank(int pos){
        for (int i=0;i<bankCards.size();i++){
            bankCards.get(i).setSelect(false);
        }
        bankCards.get(pos).setSelect(true);
    }

    /**
     * 请求可提现金额
     */
    private void requestWithDrawCash() {
        App.getServiceManager().getPdmService()
                .withDrawget()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<WithDraw>() {


                    @Override
                    public void onSuccess(BaseResultEntity<WithDraw> obj) {
                        withDrawData = obj.getResult();
                        setTvText(R.id.tv_cash_useful,withDrawData.getAccountBalance());
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}

package com.mark.app.hjshop4a.ui.withdraw;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;

import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.bankcard.activity.BankCardAddActivity;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.dialog.BankCardDialog;

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
    @Override
    public int getContentViewResId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initView() {
        this.activity=this;
        setTvText(R.id.titlebar_tv_title,"提现");
        requestData(1);
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
                break;
        }

    }

    private void commit() {
        String withDraw =getTvText(R.id.withDraw);
        String remark =getTvText(R.id.remark);
        int Role =App.getAppContext().getRoleType();
        if (TextUtils.isEmpty(withDraw)){
            ToastUtils.show("请输入金额");
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getPdmService().withDraw(Role,withDraw,currentCard.getBankName(),currentCard.getBankNo(),remark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseResultEntity obj) {

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
                        if(bankCards!=null) {
                            currentCard =bankCards.get(0);
                            bankCards.get(0).setSelect(true);
                            setSdvSmall(R.id.iv_bnak_card, bankCards.get(0).getBankPic());
                            setTvText(R.id.item_tv_bank_name, bankCards.get(0).getBankName());
                        }
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
}

package com.mark.app.hjshop4a.ui.bankcard.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.adapter.BaseSpinnerAdapter;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCategory;
import com.mark.app.hjshop4a.ui.bankcard.model.InfoBank;
import com.mark.app.hjshop4a.ui.bankcard.model.InfoBankItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCardAddActivity extends BaseActivity {
    Spinner sp;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_info_bank;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"银行卡管理");
        requestData();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.button:
                commit();
                break;
        }
    }
    /**
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .bangkCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<BankCategory>>() {
                    @Override
                    public void onSuccess(@NonNull BaseResultEntity<List<BankCategory>> obj) {
                        List<BankCategory>data = obj.getResult();
                        bindData(data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /**
     * 绑定数据
     *
     * @param data
     */
    private void bindData(List<BankCategory> data) {

        if (data != null) {

            List<BankCategory> list = data;
            initSpinner(data.get(0).getBankCategoryId(), list);
        }
    }
    /**
     * 初始化开户行类型Spinner
     *
     * @param curId
     * @param data
     */
    private void initSpinner(long curId, List<BankCategory> data) {
        sp = getView(R.id.spinner);
        BaseSpinnerAdapter<BankCategory> adapter = new BaseSpinnerAdapter<BankCategory>(data, R.layout.spinner_item_80_gravity_right) {
            @Override
            public SpinnerModel getSpinnerModelItem(BankCategory data) {
                SpinnerModel item = new SpinnerModel();
                item.setId(data.getBankCategoryId());
                item.setName(data.getBankCategoryName());
                return item;
            }
        };
        sp.setAdapter(adapter);
        sp.setSelection(adapter.getPositionById(curId));
    }
    private void commit() {
        String accountHolder =getTvText(R.id.account_name);
        long bankCategoryId  =sp.getSelectedItemId();
        String bankBranchName =getTvText(R.id.item_et_bank_name);
        String bankAccount =getTvText(R.id.card_number).replaceAll(" ","");


        if (TextUtils.isEmpty(accountHolder)) {
            ToastUtils.show("请输入开户人姓名");
            return;
        }

        if (TextUtils.isEmpty(bankBranchName)) {
            ToastUtils.show("请输入开户银行名称");
            return;
        }
        if (TextUtils.isEmpty(bankAccount)) {
            ToastUtils.show("请输入开户银行账号");
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .editBnakCard(0,1,accountHolder, bankCategoryId, bankBranchName, bankAccount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(@NonNull BaseResultEntity obj) {
                        ToastUtils.show("保存成功");

                        setResult(ActResultCode.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}

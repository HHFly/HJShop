package com.mark.app.hjshop4a.uinew.bindinfo;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.adapter.BaseSpinnerAdapter;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCategory;
import com.mark.app.hjshop4a.uinew.bindinfo.model.BankCardAddParam;
import com.mark.app.hjshop4a.uinew.userinfo.model.UserBank;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCardAddActivity extends BaseActivity {
    Spinner sp;
    BankCardAddParam bankCardAddParam =new BankCardAddParam();
    @Override
    public int getContentViewResId() {
        return R.layout.activitity_bind_bankcard;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.绑定银行卡);

        setProhibitEmoji((EditText) getView(R.id.et_name));
        setProhibitEmoji((EditText) getView(R.id.et_num));
        setProhibitEmoji((EditText) getView(R.id.et_phone));
        requestbankData();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.btn:
                commit();
                break;
        }
    }
    /**
     * 请求数据
     */
    private void requestbankData() {
        showLoadingDialog();
        App.getServiceManager().getmService()
                .getBankList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {

                               @Override
                               public void onSuccess(RainbowResultEntity obj) {
                                   List<BankCategory>data = JsonUtils.getList(obj.getResult(),BankCategory.class);
                                   if(data!=null&&data.size()!=0) {
                                       bindData(data);
                                       requestData();
                                   }
                               }

                               @Override
                               public void onAllFinish() {
                                   hideLoadingDialog();
                               }
                           }
                );
    }
    //请求数据
    private void requestData() {
        showLoadingDialog();
        App.getServiceManager().getmService()
                .getUserBank()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<UserBank>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<UserBank> obj) {
                        UserBank data = JsonUtils.fromJson(obj.getResult(), UserBank.class);
                        if(data!=null) {
                            setTvText(R.id.et_name, data.getAccountName());
                            setTvText(R.id.et_num, data.getAccountNumber());
                            setTvText(R.id.et_phone, data.getUserPhone());
                            sp.setSelection(data.getBankId());

                        }
                    }

                    @Override
                    public void onAllFinish() {
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

        if (data != null&&data.size()!=0) {

            List<BankCategory> list = data;
            initSpinner(data.get(0).getBankId(), list);
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
                item.setId(data.getBankId());
                item.setName(data.getBankName());
                return item;
            }
        };
        sp.setAdapter(adapter);
        sp.setSelection(adapter.getPositionById(curId));
    }
    private void commit() {
        String accountHolder =getTvText(R.id.et_name);
        long bankCategoryId  =sp.getSelectedItemId();
        String bankAccount =getTvText(R.id.et_num);
        String phone =getTvText(R.id.et_phone);

        if (TextUtils.isEmpty(accountHolder)) {
            ToastUtils.show("请输入开户人");
            return;
        }
        if (TextUtils.isEmpty(bankAccount)) {
            ToastUtils.show("请输入开户账号");
            return;
        }

        if (!ValidUtils.phone(phone)) {
            ToastUtils.show("请输入预留手机号");
            return;
        }
        bankCardAddParam.setAccountName(accountHolder);
        bankCardAddParam.setAccountNumber(bankAccount);
        bankCardAddParam.setUserPhone(phone);
        bankCardAddParam.setBankId((int) bankCategoryId);
        showLoadingDialog();
        App.getServiceManager().getmService()
                .bindBank(bankCardAddParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {
                    @Override
                    public void onSuccess(@NonNull RainbowResultEntity obj) {
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

package com.mark.app.hjshop4a.ui.withdraw;

import android.app.Activity;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.model.bankcard.BankCard;
import com.mark.app.hjshop4a.ui.bankcard.BankCardAddActivity;
import com.mark.app.hjshop4a.ui.dialog.BankCardDialog;

import java.util.ArrayList;

import static com.mark.app.hjshop4a.ui.dialog.BankCardDialog.getInstance;

/**
 * Created by pc on 2018/4/20.
 */

public class WithDrawActivity extends BaseActivity {
    private static BankCardDialog bankCardDialog;
    private Activity activity;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_withdraw;
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
        setClickListener(R.id.iv_bnak_card_slsect);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.button:
//                提交
                break;
            case R.id.iv_bnak_card_slsect:
            showBankCardDialog();
                break;
        }

    }

    private void showBankCardDialog() {
        ArrayList<BankCard> arrayList =new ArrayList<>();
        BankCard bankCard =new BankCard();
        arrayList.add(bankCard);
        arrayList.add(bankCard);
        bankCardDialog =BankCardDialog.getInstance(arrayList,this);
        bankCardDialog.setOnBtnClickListenr(new BankCardDialog.OnBtnClickListenr() {
            @Override
            public void onItemClick(View view, BankCard data, int position) {
                setTvText(R.id.item_tv_bank_name,"中国农业银行（8888）");
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
}

package com.mark.app.hjshop4a.ui.bankcard;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.model.bankcard.BankCard;
import com.mark.app.hjshop4a.ui.userinfo.UserInfoActivity;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCardActivity extends BaseActivity {
        private BankCardAdapter bankCardAdapter;
        private ArrayList<BankCard> bankCards =new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tiltle_right_iv_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"银行卡管理");
        initRvadapter();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_iv_add);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.titlebar_iv_add:
//                添加银行卡
                Intent intent = new Intent(this, BankCardAddActivity.class);
                this.startActivity(intent);
                this.overridePendingTransition(0,0);
                break;
        }
    }
    private void initRvadapter(){
        BankCard a =new BankCard();
        bankCards.add(a);
        if(bankCardAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            bankCardAdapter = new BankCardAdapter(bankCards);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(bankCardAdapter);
        }
        else {
            bankCardAdapter.notifyData(bankCards,true);
        }
    }
}

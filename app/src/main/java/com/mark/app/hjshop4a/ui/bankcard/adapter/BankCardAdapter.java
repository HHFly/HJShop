package com.mark.app.hjshop4a.ui.bankcard;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCardAdapter extends BaseListRvAdapter<BankCard> {
    public BankCardAdapter(ArrayList<BankCard> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_bankcard;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, BankCard data) {
        if(data!=null) {
            holder.sdvSmall(R.id.item_bank_logo, data.getBankPic());
            holder.text(R.id.item_tv_bank_name, data.getBankName());
            holder.text(R.id.item_tv_card_type, CardType(data.getBankType()));
            holder.text(R.id.item_tv_card_number,data.getBankNo());
        }
    }

    private String CardType(int bankType) {
        switch (bankType){
            case 0:return "普通卡";
            case 1:return  "信用卡";
        }
        return "";
    }
}

package com.mark.app.hjshop4a.ui.bankcard;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.model.bankcard.BankCard;

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
        return R.layout.item_bankcard_list;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, BankCard data) {

    }
}

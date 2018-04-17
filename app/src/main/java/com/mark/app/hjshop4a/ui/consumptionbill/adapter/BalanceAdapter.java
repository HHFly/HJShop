package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.model.consumptionbill.BalanceRepo;

import java.util.List;

/**
 * Created by pc on 2018/4/17.
 */

public class BalanceAdapter extends BaseListRvAdapter<BalanceRepo> {
    public BalanceAdapter(List<BalanceRepo> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_bill_balance;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, BalanceRepo data) {

    }
}

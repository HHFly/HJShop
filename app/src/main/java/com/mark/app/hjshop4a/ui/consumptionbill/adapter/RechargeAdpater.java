package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.model.consumptionbill.RechargeRepo;

import java.util.List;

/**
 * Created by pc on 2018/4/17.
 */

public class RechargeAdpater extends BaseListRvAdapter<RechargeRepo> {
    public RechargeAdpater(List<RechargeRepo> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_bill_recharge;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, RechargeRepo data) {

    }
}

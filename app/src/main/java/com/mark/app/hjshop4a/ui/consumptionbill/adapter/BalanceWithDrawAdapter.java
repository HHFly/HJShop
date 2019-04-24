package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.common.utils.BillUtil;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceWithDraw;

import java.util.List;

/**
 * Created by pc on 2018/4/25.
 */

public class BalanceWithDrawAdapter extends BaseListRvAdapter<BalanceWithDraw> {
    public BalanceWithDrawAdapter(List<BalanceWithDraw> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_balancewithdraw;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, BalanceWithDraw data) {
            holder.text(R.id.withDrawTime,data.getWithDrawTime());
            holder.text(R.id.withDrawMoney,data.getWithDrawMoney());
            holder.text(R.id.toAccountMoney,data.getToAccountMoney());
            holder.text(R.id.auditStatus, BillUtil.swichAuditStatus(data.getAuditStatus()));
    }
}

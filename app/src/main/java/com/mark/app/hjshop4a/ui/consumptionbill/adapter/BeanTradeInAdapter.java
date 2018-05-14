package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.common.utils.BillUtil;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BeanTradeIn;

import java.util.List;

/**
 * Created by pc on 2018/4/25.
 */

public class BeanTradeInAdapter extends BaseListRvAdapter<BeanTradeIn> {
    public BeanTradeInAdapter(List<BeanTradeIn> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_beantradein;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, BeanTradeIn data) {
            holder.text(R.id.toAccountTime,data.getToAccountTime());
            holder.text(R.id.tradeInMoney,data.getTradeInMoney());
            holder.text(R.id.discountsSn,data.getTradeInSn());
            holder.text(R.id.toAccountMoney,data.getToAccountMoney());
            holder.text(R.id.auditStatus, BillUtil.swichAuditStatus(data.getAuditStatus()));
    }
}

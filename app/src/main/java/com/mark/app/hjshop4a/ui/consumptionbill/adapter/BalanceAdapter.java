package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.common.utils.BillUtil;
import com.mark.app.hjshop4a.model.consumptionbill.BalanceRepo;
import com.mark.app.hjshop4a.ui.consumptionbill.model.Balance;

import java.util.List;

/**
 * Created by pc on 2018/4/17.
 */

public class BalanceAdapter extends BaseListRvAdapter<Balance> {
    public BalanceAdapter(List<Balance> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_bill_balance;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, Balance data) {
            holder.text(R.id.offlineOrderTime,data.getOfflineOrderTime());
            holder.text(R.id.consumeMoney,data.getConsumeMoney());
            holder.text(R.id.shopName,data.getShopName());
            holder.text(R.id.shopId,data.getShopId());
            holder.text(R.id.comsumerType, BillUtil.swicthcomsumerType(data.getComsumerType()));
            holder.text(R.id.offlineOrderAuditStatus,BillUtil.swichAuditStatus(data.getOfflineOrderAuditStatus()));
            holder.text(R.id.offlineOrderSn,data.getOfflineOrderSn());
            holder.text(R.id.offlineOrderSn,data.getOfflineOrderSn());

    }
}

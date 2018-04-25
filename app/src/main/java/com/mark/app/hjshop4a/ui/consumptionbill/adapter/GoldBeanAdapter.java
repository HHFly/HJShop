package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.common.utils.BillUtil;
import com.mark.app.hjshop4a.model.consumptionbill.GoldBeanRepo;
import com.mark.app.hjshop4a.ui.consumptionbill.model.Bean;

import java.util.List;

/**
 * Created by pc on 2018/4/17.
 */

public class GoldBeanAdapter  extends BaseListRvAdapter<Bean> {
    public GoldBeanAdapter(List<Bean> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_bill_gold_bean;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, Bean data) {
                holder.text(R.id.offlineOrderTime,data.getOfflineOrderTime());
                holder.text(R.id.beanNum,data.getBeanNum());
                holder.text(R.id.deductionMoney,data.getDeductionMoney());
                holder.text(R.id.shopName,data.getShopName());
                holder.text(R.id.comsumerType, BillUtil.swicthcomsumerType(data.getComsumerType()));
                holder.text(R.id.offlineOrderAuditStatus,BillUtil.swichAuditStatus(data.getOfflineOrderAuditStatus()));

    }




}

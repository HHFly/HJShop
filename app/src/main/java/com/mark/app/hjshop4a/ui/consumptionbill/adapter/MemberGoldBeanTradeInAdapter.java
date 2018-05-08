package com.mark.app.hjshop4a.ui.consumptionbill.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceWithDraw;
import com.mark.app.hjshop4a.ui.consumptionbill.model.MemberGoldBeanTradeIn;

import java.util.List;

/**
 * Created by pc on 2018/5/7.
 */

public class MemberGoldBeanTradeInAdapter  extends BaseListRvAdapter<MemberGoldBeanTradeIn> {
    public MemberGoldBeanTradeInAdapter(List<MemberGoldBeanTradeIn> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.fragment_member_bean_tradein;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, MemberGoldBeanTradeIn data) {

            holder.text(R.id.offlineOrderTime, data.getTradeInTime());
            holder.text(R.id.tradeInSn, data.getTradeInSn());
            holder.text(R.id.userCellphone, data.getUserCellphone());
            holder.text(R.id.tradeInBean, data.getTradeInBean());
            holder.text(R.id.tradeInPrice, data.getTradeInPrice());


    }
}

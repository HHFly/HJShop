package com.mark.app.hjshop4a.ui.business.billrecord;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.common.utils.BillUtil;
import com.mark.app.hjshop4a.model.consumptionbill.Bill;
import com.mark.app.hjshop4a.ui.business.billrecord.model.Customs;
import com.mark.app.hjshop4a.ui.business.billrecord.model.BillsRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/20.
 */

public class BusniessBillRecordAdapter extends BaseHasTopListRvAdapter <BillsRecord,Customs> {

    public BusniessBillRecordAdapter(BillsRecord billsRecord, List<Customs> customs) {
        super(billsRecord, customs);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_busniess_billrecord_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_busniess_order_bill;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, BillsRecord billsRecord) {
        if(billsRecord!=null) {

            holder.text(R.id.tv_turnOverTotal, billsRecord.getCustomsTotal());
            holder.text(R.id.turnOverMonth, billsRecord.getCustomsYestoday());
        }
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, Customs customs) {
        if(customs!=null) {
            holder.text(R.id.shopName, customs.getShopName());
            holder.text(R.id.offlineOrderSn, customs.getCustomsTime());
            holder.text(R.id.customsUerName, customs.getCustomsUerName());
            holder.text(R.id.productName, customs.getProductName());
            holder.text(R.id.productNum, String.valueOf(customs.getProductNum()));
            holder.text(R.id.customsTypeName, customs.getCustomsTypeName());
            holder.text(R.id.customsMoney, customs.getCustomsMoney());
            holder.text(R.id.serviceMony, customs.getServiceMony());
            holder.text(R.id.customsStatus, BillUtil.swichAuditStatus(customs.getCustomsStatus()));
        }

    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onClickItemYes();

        void onClickItemNo();
    }
}

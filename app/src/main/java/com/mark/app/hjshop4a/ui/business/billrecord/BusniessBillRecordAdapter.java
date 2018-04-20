package com.mark.app.hjshop4a.ui.business.billrecord;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.ui.business.billrecord.model.BillRecord;
import com.mark.app.hjshop4a.ui.business.billrecord.model.BillsRecord;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/20.
 */

public class BusniessBillRecordAdapter extends BaseHasTopListRvAdapter <BillsRecord,BillRecord> {
    public BusniessBillRecordAdapter(BillsRecord billsRecord, ArrayList<BillRecord> billRecords) {
        super(billsRecord, billRecords);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.activity_busniess_bill;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_busniess_order_bill;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, BillsRecord billsRecord) {


    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, BillRecord billRecord) {

    }
private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onClickItem();
    }
}

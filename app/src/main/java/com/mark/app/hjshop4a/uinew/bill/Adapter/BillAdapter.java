package com.mark.app.hjshop4a.uinew.bill.Adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.bill.model.BillRecord;
import com.mark.app.hjshop4a.uinew.order.OrderAdapter;
import com.mark.app.hjshop4a.uinew.order.OrderInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BillAdapter extends BaseListRvAdapter<BillRecord> {

    public BillAdapter(List<BillRecord> billRecords) {
        super(billRecords);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_bill_body;
    }





    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, BillRecord billRecord) {
            holder.text(R.id.tv_bill_gold,billRecord.getGold());
        holder.text(R.id.tv_bill_status,billRecord.getRecordName());
        holder.text(R.id.tv_bill_money,billRecord.getMoney());
        holder.text(R.id.tv_bill_time,billRecord.getCreateTime());

    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 点击个人信息
         *
         *
         */
        void onClickTime();
        void onClickSn();

    }

}

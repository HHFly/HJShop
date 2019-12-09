package com.mark.app.hjshop4a.uinew.integral.adapter;

import android.widget.TextView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.common.utils.NumberUtils;
import com.mark.app.hjshop4a.uinew.integral.model.IntegralChange;

import java.util.List;

public class IntegralAdapter extends BaseListRvAdapter<IntegralChange> {
    public IntegralAdapter(List<IntegralChange> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_intetarl_body;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, IntegralChange data) {
        holder.text(R.id.tv_intetarl_reason,data.getReason());
        holder.text(R.id.tv_intetarl_time, NumberUtils.getFormatDateTimeYMD(data.getCreateTime()));
        TextView tv =holder.get(R.id.tv_intetarl);
        switch (data.getType()){
            case 1:
                tv.setTextColor(getContext().getResources().getColor(R.color.color_add));
                tv.setText("+"+String.valueOf(data.getIntegral()));
                break;
            case 2:
                tv.setTextColor(getContext().getResources().getColor(R.color.red_fo3a53));
                tv.setText("-"+String.valueOf(data.getIntegral()));
                break;

        }
    }
}

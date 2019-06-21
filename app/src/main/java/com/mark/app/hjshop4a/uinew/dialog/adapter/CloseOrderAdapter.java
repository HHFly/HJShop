package com.mark.app.hjshop4a.uinew.dialog.adapter;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.dialog.model.CloseData;

import java.util.List;

public class CloseOrderAdapter extends BaseListRvAdapter<CloseData> {
    private int mSelectedItem = -1;
    int type ;
    public CloseOrderAdapter(List<CloseData> data) {
        super(data);
    }

    public int getType() {
        return type;
    }

    @Override
    public int getItemResId() {
        return R.layout.item_closeoreder;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, final CloseData data) {
        RadioButton radioButton =holder.get(R.id.radioButton);
        radioButton.setText(data.getSeason());
        radioButton.setChecked(bodyPos == mSelectedItem);
        holder.itemView.setTag(bodyPos);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedItem = (int) v.getTag();
                type=data.getType();
                notifyItemRangeChanged(0, getData().size());
            }
        });

    }
}

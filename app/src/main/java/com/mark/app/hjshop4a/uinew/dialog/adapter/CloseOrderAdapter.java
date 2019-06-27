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
    public void bindBodyData(AutoViewHolder holder, final int bodyPos, final CloseData data) {
        RadioButton radioButton =holder.get(R.id.radioButton);
        radioButton.setText(data.getSeason());
        radioButton.setChecked(data.isCheck());
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNoCheck();
                mSelectedItem = bodyPos;
                type=data.getType();
                data.setCheck(true);
                notifyDataSetChanged();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNoCheck();
                mSelectedItem = bodyPos;
                type=data.getType();
                data.setCheck(true);
                notifyDataSetChanged();
            }
        });

    }
    private void setNoCheck(){
        for (int i =0;i<getData().size();i++){
            getData().get(i).setCheck(false);
        }
    }
}

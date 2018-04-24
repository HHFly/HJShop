package com.mark.app.hjshop4a.ui.dialog.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;

import java.util.List;

/**
 * Created by pc on 2018/4/20.
 */

public class BankCardRvAdapter extends BaseListRvAdapter<BankCard> {
    public BankCardRvAdapter(List<BankCard> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_withdraw_bankcard;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, final int bodyPos, BankCard data) {
        final  BankCard item =data;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useOnItemClick(v, bodyPos, item);
            }
        });
    }
}
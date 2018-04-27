package com.mark.app.hjshop4a.ui.dialog.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/20.
 */

public class BankCardRvAdapter extends BaseListRvAdapter<BankCard> {
    private ArrayList<AutoViewHolder> holders =new ArrayList<>();
    public BankCardRvAdapter(List<BankCard> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_withdraw_bankcard;
    }

    @Override
    public void bindBodyData(final AutoViewHolder holder, final int bodyPos, BankCard data) {
        final  BankCard item =data;
        holder.sdvSmall(R.id.sdv_bnak_card,data.getBankPic());
        holder.text(R.id.item_tv_bank_name,data.getBankName());
        if(data.isSelect())
        {  holder.visibility(R.id.iv_bnak_card_slsect,true);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useOnItemClick(v, bodyPos, item);
                for(int i =0;i<holders.size();i++){
                    holders.get(i).visibility(R.id.iv_bnak_card_slsect,false);
                }
                holder.visibility(R.id.iv_bnak_card_slsect,true);

            }
        });
        holders.add(holder);
    }
}

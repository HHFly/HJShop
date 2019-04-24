package com.mark.app.hjshop4a.ui.bankcard.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;


import java.util.ArrayList;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCardAdapter extends BaseListRvAdapter<BankCard> {
    public BankCardAdapter(ArrayList<BankCard> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_bankcard;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos,final BankCard data) {
        if(data!=null) {
            holder.sdvSmall(R.id.item_bank_logo, data.getBankPic());
            holder.text(R.id.item_tv_bank_name, data.getBankName());
//            holder.text(R.id.item_tv_card_type, data.getBankType());
            holder.text(R.id.item_tv_card_number,data.getBankNo());
            holder.get(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClickDelete(data);
                    }
                }
            });
        }
    }

    private String CardType(int bankType) {
        switch (bankType){
            case 0:return "普通卡";
            case 1:return  "信用卡";
        }
        return "";
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 点击修改分类
         *
         * @param data
         */
        void onClickDelete(BankCard data);

    }
}

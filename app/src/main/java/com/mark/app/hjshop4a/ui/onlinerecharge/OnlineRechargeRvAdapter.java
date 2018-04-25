package com.mark.app.hjshop4a.ui.onlinerecharge;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopBottomListRvAdapter;
import com.mark.app.hjshop4a.common.androidenum.pay.PayType;

import com.mark.app.hjshop4a.ui.onlinerecharge.model.OnlineRecharge;

import java.util.ArrayList;
import java.util.List;

/**
 * 收银台列表
 * Created by lenovo on 2017/10/6.
 */

public class OnlineRechargeRvAdapter extends BaseHasTopBottomListRvAdapter<ArrayList<OnlineRecharge>, OnlineRecharge> {
    private TextWatcher textWatcher;
    private int mSelectPos = 1;//默认第一个付款方式
    private  String count ="";

    public OnlineRechargeRvAdapter(List<OnlineRecharge> onlineRecharges) {
        super(onlineRecharges);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.adapter_pay_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.adapter_pay_body;
    }

    @Override
    public int getBottomItemResId() {
        return R.layout.adapter_pay_bottom;
    }


    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, ArrayList<OnlineRecharge> onlineRecharges) {
        textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                count =s.toString();
            }
        };
        holder.setEtTextWatcher(R.id.et_recharge_count,textWatcher);

    }
    @Override
    public void bindBottomData(AutoViewHolder holder, int position, ArrayList<OnlineRecharge> onlineRecharges) {
        holder.get(R.id.pay_btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    OnlineRecharge pay = getDataItem(mSelectPos);
                    if (pay != null) {
                        onItemClickListener.onClickPay(pay,count);
                    }
                }
            }
        });
    }



    @Override
    public void bindBodyData(AutoViewHolder holder, final int bodyPosition, OnlineRecharge data) {
        boolean isSelected = mSelectPos == bodyPosition;
        holder.itemView.setSelected(isSelected);


        switch (data.getPayWayCode()) {
            case PayType.ALIPAY: {
                holder.image(R.id.item_sdv_icon, R.mipmap.ic_pay_zfb);


                break;
            }
            case PayType.WECHAT: {
                holder.image(R.id.item_sdv_icon, R.mipmap.ic_third_wx);

                break;
            }
        }

        holder.text(R.id.item_tv_name, data.getPayWayName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPos = bodyPosition;
                notifyDataSetChanged();
            }
        });
    }



    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 确认支付
         *
         * @param data
         */
        void onClickPay(OnlineRecharge data,String count);
    }
}

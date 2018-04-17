package com.mark.app.hjshop4a.ui.onlinerecharge;

import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopBottomListRvAdapter;
import com.mark.app.hjshop4a.common.androidenum.pay.PayType;
import com.mark.app.hjshop4a.model.onlinerecharhe.Pay;
import com.mark.app.hjshop4a.model.onlinerecharhe.PayInfo;

import java.util.List;

/**
 * 收银台列表
 * Created by lenovo on 2017/10/6.
 */

public class OnlineRechargeRvAdapter extends BaseHasTopBottomListRvAdapter<PayInfo, Pay> {

    private int mSelectPos = 0;

    public OnlineRechargeRvAdapter(PayInfo payInfo, List<Pay> bodyData) {
        super(payInfo, bodyData);
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
    public void bindTopData(AutoViewHolder holder, int topPos, PayInfo payInfo) {
        //设置订单编号

//        holder.text(R.id.pay_tv_order_sn, strOrderSn);

        //设置应付款
//        holder.text(R.id.pay_tv_should_pay, payInfo.getFixedPrice());
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, final int bodyPosition, Pay data) {
        boolean isSelected = mSelectPos == bodyPosition;
        holder.itemView.setSelected(isSelected);


        switch (data.getPayType()) {
            case PayType.ALIPAY: {
                holder.image(R.id.item_sdv_icon, R.mipmap.ic_pay_zfb);

                break;
            }
            case PayType.WECHAT: {
                holder.image(R.id.item_sdv_icon, R.mipmap.ic_third_wx);

                break;
            }
            case PayType.STRIPE: {
                holder.image(R.id.item_sdv_icon,  R.mipmap.ic_pay_stripe);

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

    @Override
    public void bindBottomData(AutoViewHolder holder, int position, final PayInfo payInfo) {
        holder.get(R.id.pay_btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    Pay pay = getDataItem(mSelectPos);
                    if (pay != null) {
                        onItemClickListener.onClickPay(pay);
                    }
                }
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
        void onClickPay(Pay data);
    }
}

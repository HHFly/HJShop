package com.mark.app.hjshop4a.uinew.homepager.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.order.OrderInfo;
import com.mark.app.hjshop4a.widget.OrderLinearLayout;

import java.util.List;

public class OrderAdapter extends BaseListRvAdapter<OrderInfo> {
    public OrderAdapter(List<OrderInfo> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_get_order;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, final OrderInfo data) {
        holder.text(R.id.tv_orderType,data.getOrderType());
        holder.text(R.id.tv_subOrderSn,data.getSubOrderSn());
        holder.text(R.id.tv_shopPayPrice,String.format(getString(R.string.元),data.getShopPayPric()));
        holder.text(R.id.tv_gold,String.format(getString(R.string.元),data.getGold()));
        holder.sdvInside(R.id.hm_sdv_productPic,data.getProductPic());
        OrderLinearLayout orderLinearLayout =holder.get(R.id.warp_label);
        String[] tip =data.getRequire().split("/");

        for(int i=0;i<tip.length;i++){
            orderLinearLayout.addChild(tip[i]);
        }
        holder.get(R.id.order_btn_getOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(data);
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
         * 点击修改分类
         *
         * @param data
         */
        void onClick( OrderInfo data);

    }
}

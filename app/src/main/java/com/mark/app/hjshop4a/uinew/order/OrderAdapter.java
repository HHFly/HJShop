package com.mark.app.hjshop4a.uinew.order;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.common.utils.StringUtils;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.uinew.login.adapter.LoginSwitchAdapter;
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
            holder.text(R.id.tv_subOrderSn,data.getSubOrderSn());
            holder.text(R.id.tv_orderType,data.getOrderType());
            holder.text(R.id.tv_shopPayPrice, StringUtils.DoubletoString(data.getShopPayPric()));
            holder.text(R.id.tv_gold,StringUtils.DoubletoString(data.gold));
           OrderLinearLayout orderLinearLayout = holder.get(R.id.warp_label);
          String[] requires= data.getRequire().split(",");
          for(int i = 0;i<requires.length;i++){
             orderLinearLayout.addChild(requires[i]);
          }
            holder.get(R.id.order_btn_getOrder).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickOrder(data);
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
         * 点击个人信息
         *
         *
         */
        void onClickOrder(OrderInfo data);


    }

}

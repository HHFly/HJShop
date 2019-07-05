package com.mark.app.hjshop4a.ui.onlinerecharge;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopBottomListRvAdapter;
import com.mark.app.hjshop4a.common.androidenum.pay.PayType;

import com.mark.app.hjshop4a.ui.onlinerecharge.model.PayWay;
import com.mark.app.hjshop4a.ui.onlinerecharge.model.PayWayList;

import java.util.List;

/**
 * 收银台列表
 * Created by lenovo on 2017/10/6.
 */

public class OnlineRechargeRvAdapter extends BaseHasTopBottomListRvAdapter<PayWayList, PayWay> {
    private TextWatcher textWatcher;
    private int mSelectPos = 0;//默认第一个付款方式
    private  String count ="";




    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public int getBottomItemResId() {
        return 0;
    }

    @Override
    public void bindBottomData(AutoViewHolder holder, int position, PayWayList payWayList) {

    }

    @Override
    public int getTopItemResId() {
        return 0;
    }

    @Override
    public int getBodyItemResId() {
        return 0;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, PayWayList payWayList) {

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, PayWay payWay) {

    }

    public interface OnItemClickListener {
        /**
         * 确认支付
         *
         * @param data
         */
        void onClickPay(PayWay data, String count);
    }
}

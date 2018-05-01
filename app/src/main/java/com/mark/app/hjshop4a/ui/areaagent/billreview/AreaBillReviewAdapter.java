package com.mark.app.hjshop4a.ui.areaagent.billreview;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.ui.areaagent.billreview.model.AreaBillReview;
import com.mark.app.hjshop4a.ui.areaagent.billreview.model.Customs;
import com.mark.app.hjshop4a.ui.business.billrecord.BusniessBillRecordAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBillReviewAdapter extends BaseHasTopListRvAdapter<AreaBillReview,Customs> {
    public AreaBillReviewAdapter(AreaBillReview areaBillReview, List<Customs> customs) {
        super(areaBillReview, customs);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_areabillreview_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_areabillreview_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, AreaBillReview areaBillReview) {
        if(areaBillReview !=null){
            holder.text(R.id.customsTotal,String.valueOf(areaBillReview.getCustomsTotal()));
            holder.text(R.id.customsYestoday,String.valueOf(areaBillReview.getCustomsYestoday()));

        }
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, Customs customs) {
        if(customs!=null){
            holder.text(R.id.shopName,customs.getShopName());
            holder.text(R.id.customsTime,customs.getCustomsTime());
            holder.text(R.id.customsUerName,customs.getCustomsUerName());
            holder.text(R.id.productName,customs.getProductName());
            holder.text(R.id.productNum,String.valueOf(customs.getProductNum()));
            holder.text(R.id.customsTypeName,customs.getCustomsTypeName());
            holder.text(R.id.serviceMony,customs.getCustomsMoney());
            holder.text(R.id.customsTypeName,customs.getCustomsTypeName());
            holder.text(R.id.customsTypeName,customs.getCustomsTypeName());
            holder.text(R.id.customsTypeName,customs.getCustomsTypeName());
            holder.get(R.id.tv_1_pass).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickItemYes();
                    }
                }
            });
            holder.get(R.id.tv_1_unpass).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickItemNo();
                    }
                }
            });

        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onClickItemYes();
        void onClickItemNo();
    }

}

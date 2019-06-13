package com.mark.app.hjshop4a.uinew.homepager.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.uinew.homepager.model.Banner;
import com.mark.app.hjshop4a.uinew.homepager.model.IndexModel;
import com.mark.app.hjshop4a.uinew.homepager.model.ShowProduct;

import java.util.List;

public class HomeAdapter extends BaseHasTopListRvAdapter<List<Banner>,IndexModel> {
    public HomeAdapter(List<Banner> banners, List<IndexModel> showProducts) {
        super(banners, showProducts);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_home_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_home_bottom;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, List<Banner> banners) {

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, final IndexModel showProduct) {
            holder.sdvBig(R.id.sdv_img1,showProduct.getProduct1().getProductUrl());
            holder.text(R.id.tv_price1,showProduct.getProduct1().getProductPrice());
            holder.text(R.id.tv_num1,String.format(getString(R.string.人付款),showProduct.getProduct1().getPayCount()));
            if(showProduct.getProduct2()!=null){
                holder.sdvBig(R.id.sdv_img2,showProduct.getProduct2().getProductUrl());
                holder.text(R.id.tv_price2,showProduct.getProduct2().getProductPrice());
                holder.text(R.id.tv_num2,String.format(getString(R.string.人付款),showProduct.getProduct2().getPayCount()));
            }else {
                holder.get(R.id.ll_2).setVisibility(View.INVISIBLE);
            }
            holder.get(R.id.ll_1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onItemClickListener!=null){
                        onItemClickListener.onClickProduct(showProduct.getProduct1());
                    }
                }
            });
        holder.get(R.id.ll_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onItemClickListener!=null){
                    onItemClickListener.onClickProduct(showProduct.getProduct2());
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
        void onClickProduct(ShowProduct data);
        //

    }

}

package com.mark.app.hjshop4a.uinew.homepager.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.uinew.homepager.model.Banner;
import com.mark.app.hjshop4a.uinew.homepager.model.ShowProduct;

import java.util.List;

public class HomeAdapter extends BaseHasTopListRvAdapter<List<Banner>,ShowProduct> {
    public HomeAdapter(List<Banner> banners, List<ShowProduct> showProducts) {
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
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, final ShowProduct showProduct) {
            holder.sdvBig(R.id.sdv_img,showProduct.getProductUrl());
            holder.text(R.id.tv_price,showProduct.getProductPrice());
            holder.text(R.id.tv_num,String.format(getString(R.string.人付款),showProduct.getPayCount()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onItemClickListener!=null){
                        onItemClickListener.onClickProduct(showProduct);
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

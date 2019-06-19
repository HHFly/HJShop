package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwo;
import com.mark.app.hjshop4a.widget.UpdateStepTipLayout;

public class TwoSearchAdapter extends MultipleSourcesRvAdapter {
    StepTwo data ;

    public void setData(StepTwo data) {
        this.data = data;
    }

    public TwoSearchAdapter(StepTwo data) {
        this.data = data;
    }

    @Override
    public int getRowsCountInSection(int var1) {
        return 1;
    }
    @Override
    public int getSectionsCount() {

        return 2;
    }
    @Override
    public View onCreateView(ViewGroup var1, int var2) {
        switch (var2){
            case 0:
                return inflater(var1, R.layout.item_order_steptwo);
            default:
                return inflater(var1, R.layout.item_step_bottom);
        }
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()) {
            case 0:
                holder.text(R.id.tv_shopName, data.getShopName());
                holder.text(R.id.tv_wwName, data.getWwName());
                holder.text(R.id.tv_searchKeyWord, data.getSearchKeyWord());
                holder.text(R.id.tv_priceRange, data.getPriceRange());
                holder.text(R.id.tv_mainProductPrice, String.format(App.get().getString(R.string.元), data.getMainProductPrice()));
                holder.text(R.id.tv_require, data.getRequire());
                holder.sdvBig(R.id.sdv_productImg, data.getProductImg());
                UpdateStepTipLayout updateStepTipLayout =holder.get(R.id.up_step_two);
                             if (1 == data.getIsAddProductFlage()) {
                    holder.visibility(R.id.rl_AddProduct, true);

                }
                break;
            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickNext();
                        }
                    }
                });
                break;
        }
    }


    public void  startTime(){
        mHandler.sendEmptyMessage(1);
    }
    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
//                if(data!=null&&data.getCompltetTime()>0){
//                    data.countTime();
//                    notifyDataSetChanged();
//
//                }
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        };
    };

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
        //
        void onClickNext();

    }
}

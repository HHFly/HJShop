package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThree;

import java.util.List;

public class ThreeBrowseAdapter extends MultipleSourcesRvAdapter {
    StepThree data;

    public ThreeBrowseAdapter(StepThree data) {
        this.data = data;
    }

    public void setData(StepThree data) {
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
                return inflater(var1, R.layout.item_order_stepthree) ;
            default:
                return inflater(var1, R.layout.item_order_steptwo_bottom);
        }
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()) {
            case 0:
                holder.text(R.id.tv_subOrderSn,data.getSubOrderSn());
                holder.text(R.id.tv_orderType,data.getOrderType());
                holder.text(R.id.tv_verificationMethod,data.getVerificationMethod());
                holder.text(R.id.tv_payMethod,data.getPayMethod());
                holder.text(R.id.tv_remarks,data.getRemarks());
                holder.text(R.id.tv_compltetTime,getTimeString(data.getCompltetTime()));

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
    private String  getTimeString(long time){
        String Ctime ="";
        time /= 1000;
        Ctime =getTime(time / 60) + App.get().getString(R.string.分钟) + getTime(time % 60) + App.get().getString(R.string.秒);
        return Ctime;
    }

    // 时间格式化为00
    public static String getTime(long time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return "" + time;
        }
    }
    public void  startTime(){
        mHandler.sendEmptyMessage(1);
    }
    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if(data!=null&&data.getCompltetTime()>0){
                    data.countTime();
                    notifyDataSetChanged();

                }
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

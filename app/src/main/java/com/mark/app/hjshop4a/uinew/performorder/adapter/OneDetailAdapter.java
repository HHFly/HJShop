package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopBottomListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.StepOne;
import com.mark.app.hjshop4a.widget.WarpLinearLayout;

import android.os.Handler;

public class OneDetailAdapter extends MultipleSourcesRvAdapter {
    StepOne data;

    public OneDetailAdapter(StepOne data) {
        this.data = data;
    }

    public void setData(StepOne data) {
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
                return inflater(var1, R.layout.item_order_stepone) ;
            default:
                return inflater(var1, R.layout.item_step_bottom);
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
                WarpLinearLayout warpLinearLayout =holder.get(R.id.ll_process);
                warpLinearLayout.removeAllViews();
                if (data.getProcess()!=null) {
                    String[] pro = data.getProcess().split("/");
                    for (int i = 0; i < pro.length; i++) {
                        warpLinearLayout.addChild(pro[i]);
                    }
                }
                break;
            case 1:
                holder.get(R.id.btn).setOnClickListener(new View.OnClickListener() {
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

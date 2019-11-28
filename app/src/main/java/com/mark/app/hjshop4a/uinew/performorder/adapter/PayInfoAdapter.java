package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasBottomListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopBottomListRvAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.PayInfo;
import com.mark.app.hjshop4a.uinew.performorder.model.PayInfoWithName;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PayInfoAdapter extends BaseHasTopBottomListRvAdapter<PayInfo,PayInfoWithName> {
    public PayInfoAdapter(PayInfo payInfo, List<PayInfoWithName> payInfoWithNames) {
        super(payInfo, payInfoWithNames);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_title;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_payinifo;
    }


    @Override
    public int getBottomItemResId() {
        return R.layout.item_payinfo_bottom;
    }
    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, PayInfo payInfo) {
        holder.text(R.id.tv_title,"核对以下信息，确认无误后下单付款");
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, final PayInfoWithName payInfoWithName) {
        CheckBox checkBox =holder.get(R.id.cb_title);
        checkBox.setText(payInfoWithName.getName());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                payInfoWithName.setCheck(isChecked);
            }
        });
        holder.text(R.id.tv_string,payInfoWithName.getPayinfo());
    }
        public boolean getIsAllCheck(){
        for (int i=0;i<getBodyData().size();i++){
            if(!getBodyData().get(i).isCheck()){
                return false;
            }
        }
        return true;
        }


    @Override
    public void bindBottomData(final AutoViewHolder holder, int position, PayInfo payInfo) {
        holder.text(R.id.tv_compltetTime,getTimeString(payInfo.getCompltetTime()));
        final UpdateStepOneLayout updateStepLayout1 =holder.get(R.id.up_img_1);
        if(payInfo!=null&&payInfo.getPayPic()!=null){
            updateStepLayout1.setImg(payInfo.getPayPic());
        }
        updateStepLayout1.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickHuobisanjiaPic1(updateStepLayout1);
                }
            }
        });
        holder.get(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickNext(holder.getTvText(R.id.et_tbOrderSn),holder.getTvText(R.id.et_payPrice));
                }
            }
        });
    }

    private String  getTimeString(long time){
        Calendar c = Calendar.getInstance();
        time =time-c.getTimeInMillis();
        if(time<0){
            return  "00"+ App.get().getString(R.string.分钟) + "00" + App.get().getString(R.string.秒);
        }
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
                if(getTopData()!=null){
                    notifyItemChanged(getTopItemCount()+getBodyItemCount(),1);

                }
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        };
    };

    @Override
    public void customBindLocalRefresh(AutoViewHolder holder, int position, List payloads) {

        holder.text(R.id.tv_compltetTime,getTimeString(getTopData().getCompltetTime()));

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
        //
        void onClickNext(String tbOrderSn,String payPrice);
        void onClickHuobisanjiaPic1(UpdateStepOneLayout updateStepOneLayout);

    }
}

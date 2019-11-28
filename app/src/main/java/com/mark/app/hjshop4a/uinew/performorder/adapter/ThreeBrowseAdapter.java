package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThree;
import com.mark.app.hjshop4a.widget.UpdateImgLayout;
import com.mark.app.hjshop4a.widget.UpdateStepLayout;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;

import java.util.Calendar;
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
    public void onBindViewHolder(final AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()) {
            case 0:
                holder.sdvInside(R.id.hm_sdv_productPic,data.getProductImg());

                holder.text(R.id.tv_shopName,data.getShopName());
                holder.text(R.id.tv_wwName,data.getWwName());
                holder.text(R.id.tv_mainProductPrice,String.format(App.get().getString(R.string.S元),String.valueOf(data.getMainProductPrice())));
                holder.text(R.id.tv_priceRange, data.getPriceRange());
                holder.text(R.id.tv_searchKeyWord, data.getSearchKeyWord());
                holder.text(R.id.tv_require,data.getRequire());
                holder.get(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickClose();
                        }
                    }
                });
                //任务截图
                final UpdateStepOneLayout updateStepLayout1 =holder.get(R.id.up_img_1);
                if(data.getTaskPic()!=null&&data.getTaskPic().getEvaluationBrowsePic()!=null){
                    updateStepLayout1.setImg(data.getTaskPic().getEvaluationBrowsePic());
                }
                updateStepLayout1.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
                    @Override
                    public void onClickImg() {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickHuobisanjiaPic1(updateStepLayout1);
                        }
                    }
                });
                final UpdateStepOneLayout updateStepLayout2 =holder.get(R.id.up_img_2);
                if(data.getTaskPic()!=null&&data.getTaskPic().getWwChatPic()!=null){
                    updateStepLayout2.setImg(data.getTaskPic().getWwChatPic());
                }
                updateStepLayout2.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
                    @Override
                    public void onClickImg() {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickHuobisanjiaPic2(updateStepLayout2);
                        }
                    }
                });
                final UpdateStepOneLayout updateStepLayout3 =holder.get(R.id.up_img_3);
                if(data.getTaskPic()!=null&&data.getTaskPic().getAskEveryonePic()!=null){
                    updateStepLayout3.setImg(data.getTaskPic().getAskEveryonePic());
                }
                updateStepLayout3.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
                    @Override
                    public void onClickImg() {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickHuobisanjiaPic3(updateStepLayout3);
                        }
                    }
                });
                break;
            case 1:
                holder.text(R.id.tv_steptime,getStepTimeString(data.getStepTime()));
                holder.text(R.id.tv_compltetTime,getTimeString(data.getCompltetTime()));
//                holder.get(R.id.btn).setEnabled(data.getStepTime()==0);
                holder.get(R.id.btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.getStepTime()>0){
                            ToastUtils.show("本步骤时间还剩:"+holder.getTvText(R.id.tv_steptime));
                            return;
                        }
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickNext();
                        }
                    }
                });
                break;
        }
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
    private String  getStepTimeString(long time){

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



    @Override
    public void customBindLocalRefresh(AutoViewHolder holder, int position, List payloads) {
        holder.text(R.id.tv_steptime,getStepTimeString(data.getStepTime()));
        holder.text(R.id.tv_compltetTime,getTimeString(data.getCompltetTime()));
    }

    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if(data!=null){
                    data.countAllTime();
                 notifyItemChanged(getItemCount()-1,1);
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
        void onClickClose();
        void onClickHuobisanjiaPic1(UpdateStepOneLayout updateStepOneLayout);
        void onClickHuobisanjiaPic2(UpdateStepOneLayout updateStepOneLayout);
        void onClickHuobisanjiaPic3(UpdateStepOneLayout updateStepOneLayout);
    }

}

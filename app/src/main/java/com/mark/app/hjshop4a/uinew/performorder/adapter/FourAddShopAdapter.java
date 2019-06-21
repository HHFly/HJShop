package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.StepFour;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;

public class FourAddShopAdapter extends MultipleSourcesRvAdapter {
    StepFour data;

    public FourAddShopAdapter(StepFour data) {
        this.data = data;
    }

    public StepFour getData() {
        return data;
    }

    public void setData(StepFour data) {
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
                return inflater(var1, R.layout.item_order_stepfour) ;
            default:
                return inflater(var1, R.layout.item_order_steptwo_bottom);
        }
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()) {
            case 0:
                holder.sdvInside(R.id.hm_sdv_productPic,data.getProductImg());

                holder.text(R.id.tv_shopName,data.getShopName());
                holder.text(R.id.tv_wwName,data.getWwName());
                holder.text(R.id.tv_mainProductPrice,String.format(App.get().getString(R.string.元),data.getMainProductPrice()));
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
                if(data.getTaskPic()!=null&&data.getTaskPic().getAddShoppingCartPic()!=null){
                    updateStepLayout1.setImg(data.getTaskPic().getAddShoppingCartPic());
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
                if(data.getTaskPic()!=null&&data.getTaskPic().getCollectionProductPic()!=null){
                    updateStepLayout2.setImg(data.getTaskPic().getCollectionProductPic());
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
                if(data.getTaskPic()!=null&&data.getTaskPic().getCollectionShopPic()!=null){
                    updateStepLayout3.setImg(data.getTaskPic().getCollectionShopPic());
                }
                updateStepLayout3.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
                    @Override
                    public void onClickImg() {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickHuobisanjiaPic3(updateStepLayout3);
                        }
                    }
                });
                final UpdateStepOneLayout updateStepLayout4 =holder.get(R.id.up_img_4);
                if(data.getTaskPic()!=null&&data.getTaskPic().getNotPayPic()!=null){
                    updateStepLayout4.setImg(data.getTaskPic().getNotPayPic());
                }
                updateStepLayout4.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
                    @Override
                    public void onClickImg() {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickHuobisanjiaPic4(updateStepLayout4);
                        }
                    }
                });
                break;
            case 1:
                holder.text(R.id.tv_steptime,getTimeString(data.getStepTime()));
                holder.text(R.id.tv_compltetTime,getTimeString(data.getCompltetTime()));
                holder.itemView.setEnabled(data.getStepTime()==0);
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
                if(data!=null){
                    if(data.getStepTime()>0){
                        data.countAllTime();
                        notifyDataSetChanged();
                        //所以倒计时
                    }else {
                        if(data.getCompltetTime()>0){
                            data.countCompleteTime();
                            notifyDataSetChanged();
                            //总时间倒计时
                        }
                    }

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
        void onClickHuobisanjiaPic4(UpdateStepOneLayout updateStepOneLayout);
    }
}

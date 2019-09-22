package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.os.Handler;
import android.view.View;

import com.dd.CircularProgressButton;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopBottomListRvAdapter;
import com.mark.app.hjshop4a.common.utils.StringUtils;
import com.mark.app.hjshop4a.uinew.performorder.model.AddProduct;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwo;
import com.mark.app.hjshop4a.widget.UpdateImgLayout;
import com.mark.app.hjshop4a.widget.UpdateStepLayout;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;

import java.util.ArrayList;
import java.util.List;

public class TwoSearchAdapter extends BaseHasTopBottomListRvAdapter<StepTwo,AddProduct> {
    private List<CircularProgressButton> circularProgressButtonList =new ArrayList<>();

//    boolean isVerity
    public TwoSearchAdapter(StepTwo stepTwo, List<AddProduct> addProducts) {
        super(stepTwo, addProducts);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_order_steptwo;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_order_steptwo_3;
    }
    @Override
    public int getBottomItemResId() {
        return R.layout.item_order_steptwo_bottom;
    }
    @Override
    public void bindTopData(final AutoViewHolder holder, int topPos, final StepTwo stepTwo) {
        switch (stepTwo.getOperationType()){
            case 1:
                holder.text(R.id.tv_1, "第一步:验证详情关键词--详情提示: "+ StringUtils.getStarString2(stepTwo.getAuthKeyWord(),1,1)+ String.format(App.get().getString(R.string.共n个字),stepTwo.getAuthKeyWord().length()));
                break;
            case 2:
                holder.text(R.id.tv_1,R.string.第一步验证店铺名 );
                default: holder.text(R.id.tv_1, "错误验证类型"+ stepTwo.getOperationType());
                break;
        }
        holder.text(R.id.tv_shopName, stepTwo.getShopName());
        holder.text(R.id.tv_wwName, stepTwo.getWwName());
        holder.text(R.id.tv_searchKeyWord, stepTwo.getSearchKeyWord());
        holder.text(R.id.tv_priceRange, stepTwo.getPriceRange());
        holder.text(R.id.tv_mainProductPrice, String.format(App.get().getString(R.string.S元), String.valueOf(stepTwo.getMainProductPrice())));
        holder.text(R.id.tv_require, stepTwo.getRequire());
        holder.sdvBig(R.id.hm_sdv_productPic, stepTwo.getProductImg());
        //第一步：验证是否找对店铺
       CircularProgressButton circularPropagation =holder.get(R.id.circularButton);
        circularPropagation.setIndeterminateProgressMode(true);
        circularProgressButtonList.add(circularPropagation);
        circularPropagation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularProgressButton btn = (CircularProgressButton) v;
                int progress = btn.getProgress();
                if (progress == 0) { // 初始时progress = 0
                    btn.setProgress(50); // 点击后开始不精准进度，不精准进度的进度值一直为50
                } else if (progress == 100) { // 如果当前进度为100，即完成状态，那么重新回到未完成的状态
                    btn.setProgress(0);
                } else if (progress == 50) { // 如果当前进度为50，那么点击后就显示完成的状态
                    btn.setProgress(0); // -1表示出错，显示出错的图片和背景，100表示完成，显示完成的图片和背景
                }else if (progress == -1) { // 如果当前进度为50，那么点击后就显示完成的状态
                    btn.setProgress(0); // -1表示出错，显示出错的图片和背景，100表示完成，显示完成的图片和背景
                }
                String text =holder.getTvText(R.id.et_shopname);

                if(onItemClickListener!=null){
                    onItemClickListener.onCircularProgressButton(text,btn ,stepTwo);
                }


            }
        });
//                第二步：请根据商家要求上传任务截图
        //主宝贝
        final UpdateStepOneLayout updateStepLayout =holder.get(R.id.up_img_mainProductBrowsePic);
        if(stepTwo.getTaskPic()!=null&&stepTwo.getTaskPic().getBrowsePice()!=null){
            updateStepLayout.setImg(stepTwo.getTaskPic().getBrowsePice());
        }

        updateStepLayout.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickMainProductBrowsePic(updateStepLayout);
                }
            }
        });
        //搜索
        final UpdateStepOneLayout updateStepLayout3 =holder.get(R.id.up_img_searchPic);
        if(stepTwo.getTaskPic()!=null&&stepTwo.getTaskPic().getSearchPic()!=null){
            updateStepLayout3.setImg(stepTwo.getTaskPic().getBrowsePice());
        }
        updateStepLayout3.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickSearchPic(updateStepLayout3);
                }
            }
        });
        //第三步：货比三家，每个一分钟，提供三个竞品详情页顶部截图
        final UpdateStepOneLayout updateStepOneLayout1 =holder.get(R.id.up_img_1);
        if(stepTwo.getTaskPic()!=null&&stepTwo.getTaskPic().getHbsjPic1()!=null){
            updateStepOneLayout1.setImg(stepTwo.getTaskPic().getHbsjPic1());
        }
        updateStepOneLayout1.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickHuobisanjiaPic1(updateStepOneLayout1);
                }
            }
        });
        final UpdateStepOneLayout updateStepOneLayout2 =holder.get(R.id.up_img_2);
        if(stepTwo.getTaskPic()!=null&&stepTwo.getTaskPic().getHbsjPic2()!=null){
            updateStepOneLayout2.setImg(stepTwo.getTaskPic().getHbsjPic2());
        }
        updateStepOneLayout2.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickHuobisanjiaPic2(updateStepOneLayout2);
                }
            }
        });
        final UpdateStepOneLayout  updateStepOneLayout3 =holder.get(R.id.up_img_3);
        if(stepTwo.getTaskPic()!=null&&stepTwo.getTaskPic().getHbsjPic3()!=null){
            updateStepOneLayout3.setImg(stepTwo.getTaskPic().getHbsjPic3());
        }
        updateStepOneLayout3.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickHuobisanjiaPic3(updateStepOneLayout3);
                }
            }
        });
    }

    @Override
    public void bindBodyData(final AutoViewHolder holder, final int bodyPosition, final AddProduct addProduct) {
        holder.visibility(R.id.rl_AddProduct, true);
        holder.sdvInside(R.id.deputy_sdv_productPic,addProduct.getBrowsePic());
        holder.text(R.id.tv_deputy_productPrice,addProduct.getProductPrice());
        holder.text(R.id.tv_deputy_auditKeyWord,addProduct.getAuditKeyWord());
//                    第一步是：是否找对商品
       CircularProgressButton circularPropagation1 =holder.get(R.id.circularButton_deputy);
        circularPropagation1.setIndeterminateProgressMode(true);
        circularProgressButtonList.add(circularPropagation1);
        circularPropagation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularProgressButton btn = (CircularProgressButton) v;
                int progress = btn.getProgress();
                if (progress == 0) { // 初始时progress = 0
                    btn.setProgress(50); // 点击后开始不精准进度，不精准进度的进度值一直为50
                } else if (progress == 100) { // 如果当前进度为100，即完成状态，那么重新回到未完成的状态
                    btn.setProgress(0);
                } else if (progress == 50) { // 如果当前进度为50，那么点击后就显示完成的状态
                    btn.setProgress(0); // -1表示出错，显示出错的图片和背景，100表示完成，显示完成的图片和背景
                }else if (progress == -1) { // 如果当前进度为50，那么点击后就显示完成的状态
                    btn.setProgress(0); // -1表示出错，显示出错的图片和背景，100表示完成，显示完成的图片和背景
                }
                if(onItemClickListener!=null){
                    onItemClickListener.onCircularProgressButtonDeputy(holder.getTvText(R.id.et_name_deputy),btn ,addProduct);
                }


            }
        });
        //第二步:上传宝贝浏览截图
        final UpdateStepLayout updateStepLayout1 =holder.get(R.id.up_step_two_deputy);
        if(addProduct.getBrowsePic()!=null){
            updateStepLayout1.setImg(addProduct.getBrowsePic());
        }
        updateStepLayout1.setOnItemClickListener(new UpdateImgLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickProductBrowsePic(updateStepLayout1,addProduct,bodyPosition);
                }
            }
        });
    }



    @Override
    public void bindBottomData(AutoViewHolder holder, int position, StepTwo stepTwo) {
        holder.text(R.id.tv_steptime,getTimeString(stepTwo.getStepTime()));
        holder.text(R.id.tv_compltetTime,getTimeString(stepTwo.getCompltetTime()));
        holder.get(R.id.btn).setEnabled(stepTwo.getStepTime()==0);
        holder.get(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickNext();
                }
            }
        });
    }


    public boolean getVertify(){
        for (int i=0;i<circularProgressButtonList.size();i++){
           if(100!= circularProgressButtonList.get(i).getProgress()){
               return false;
           }
        }
        return true;
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
                if(getTopData()!=null){
                    if(getTopData().getStepTime()>0){
                        getTopData().countAllTime();
                        notifyDataSetChanged();
                        //所以倒计时
                    }else {
                        if(getTopData().getCompltetTime()>0){
                            getTopData().countCompleteTime();
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
        void onCircularProgressButton(String et,CircularProgressButton btn,StepTwo stepTwo);
        void onClickMainProductBrowsePic(UpdateStepOneLayout updateStepOneLayout);
        void onClickSearchPic(UpdateStepOneLayout updateStepOneLayout);
        void onClickHuobisanjiaPic1(UpdateStepOneLayout updateStepOneLayout);
        void onClickHuobisanjiaPic2(UpdateStepOneLayout updateStepOneLayout);
        void onClickHuobisanjiaPic3(UpdateStepOneLayout updateStepOneLayout);
        void onCircularProgressButtonDeputy(String et,CircularProgressButton btn,AddProduct addProduct);
        void onClickProductBrowsePic(UpdateStepLayout updateStepLayout,AddProduct addProduct,int pos);
    }
}

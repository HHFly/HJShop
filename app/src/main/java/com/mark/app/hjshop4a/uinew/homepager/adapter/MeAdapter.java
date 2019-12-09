package com.mark.app.hjshop4a.uinew.homepager.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.uinew.bill.BillActivity;
import com.mark.app.hjshop4a.uinew.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.uinew.integral.IntegralActivity;
import com.mark.app.hjshop4a.uinew.invitation.InvitationActivity;
import com.mark.app.hjshop4a.uinew.withdraw.WithDrawActivity;

import java.util.List;


public class MeAdapter extends MultipleSourcesRvAdapter {
    private UserCenter mData;
    public MeAdapter() {
    }

    public void setmData(UserCenter mData) {
        this.mData = mData;
    }

    @Override
    public int getRowsCountInSection(int i) {
        return 1;
    }

    @Override
    public int getSectionsCount() {

        return 6;
    }


    @Override
    public View onCreateView(ViewGroup var1, int var2) {
        switch (var2){
            case 0:
                return inflater(var1, R.layout.item_me_top_2) ;
            default:
                return inflater(var1, R.layout.item_me_bottom_2);
        }
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()){
            case 0:
                holder.get(R.id.hm_sdv_logo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickUserPic(R.id.hm_sdv_logo);
                        }

                    }
                });
                if(mData!=null) {
//                        用户名
                    holder.text(R.id.hm_tv_user_name,mData.getUserPhone());
                    //c会员账号
                    holder.get(R.id.withdraw).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityJumpUtils.actActivity(App.get().getCurActivity(), WithDrawActivity.class);
                        }
                    });
                    holder.get(R.id.bill).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityJumpUtils.actActivity(App.get().getCurActivity(), BillActivity.class);
                        }
                    });
//                    holder.sdvInside(R.id.hm_sdv_logo,mData.getUserPic());
                    holder.text(R.id.hm_tv_goldcoin,String.valueOf(mData.getUsableGold()));
                    holder.text(R.id.hm_tv_balance,String.valueOf(mData.getUsableMoney()));
                    holder.text(R.id.hm_tv_interal,String.valueOf(mData.getIntegral()));
                }
                break;//顶部数据
            case 1:
                holder.image(R.id.hm_iv_logo,R.mipmap.ic_phone);
                holder.text(R.id.hm_tv_item_name, R.string.账号绑定);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actBind(App.get().getCurActivity());
                    }
                });
                break;
            case 2:
                holder.image(R.id.hm_iv_logo,R.mipmap.ic_msg);
                holder.text(R.id.hm_tv_item_name, R.string.积分详情);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(App.get().getCurActivity(), IntegralActivity.class);
                    }
                });
                break;
            case 3:
                holder.image(R.id.hm_iv_logo,R.mipmap.ic_ext);
                holder.text(R.id.hm_tv_item_name, R.string.推广赚金);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(App.get().getCurActivity(), InvitationActivity.class);
                    }
                });
                break;
            case 4:
                holder.image(R.id.hm_iv_logo,R.mipmap.ic_set);
                holder.text(R.id.hm_tv_item_name, R.string.设置);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actSetting(App.get().getCurActivity());
                    }
                });
                break;
            case 5:
                holder.image(R.id.hm_iv_logo,R.mipmap.ic_up);
                holder.text(R.id.hm_tv_item_name, R.string.版本信息);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actVersion(App.get().getCurActivity());
                    }
                });
                break;
        }
    }

    @Override
    public void customBindLocalRefresh(AutoViewHolder holder, int position, List payloads) {

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
        void onClickUserInfo();
        //
        void onClickUserPic(int id);

    }

}

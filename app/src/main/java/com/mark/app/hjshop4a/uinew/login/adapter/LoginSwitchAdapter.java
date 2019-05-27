package com.mark.app.hjshop4a.uinew.login.adapter;

import android.app.Activity;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;

import com.mark.app.hjshop4a.model.login.model.LoginType;

import java.util.ArrayList;


/**
 * Created by hui on 2018/4/15.
 */

public class LoginSwitchAdapter extends BaseListRvAdapter<LoginType> {

    private  Activity activity ;
    public LoginSwitchAdapter(ArrayList<LoginType> data,Activity activity) {

        super(data);
        this.activity= activity;
    }

    @Override
    public int getItemResId() {
        return R.layout.item_account_number;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, final LoginType data) {
        holder.text(R.id.item_account_number_type,data.getTpye());
        holder.sdvSmall(R.id.item_sdv_logo,data.getHeadImg());
        holder.text(R.id.item_account_number,data.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             if(onItemClickListener!=null){
                 onItemClickListener.onClickUserInfo(data);
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
        void onClickUserInfo(LoginType data);


    }

}

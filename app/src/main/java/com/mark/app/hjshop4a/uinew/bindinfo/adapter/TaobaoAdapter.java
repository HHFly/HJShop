package com.mark.app.hjshop4a.uinew.bindinfo.adapter;

import android.support.annotation.StringRes;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.bankcard.adapter.BankCardAdapter;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfo;

import java.util.List;

public class TaobaoAdapter extends BaseListRvAdapter<AccountInfo> {
    public TaobaoAdapter(List<AccountInfo> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_account_info;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, final AccountInfo data) {
            holder.text(R.id.tv_wwname,data.getAccountName());
        holder.text(R.id.tv_phone,data.getReceiverPhone());
        holder.text(R.id.tv_name,data.getReceiverName());
        holder.text(R.id.tv_sex,data.getSex()==1?R.string.男:R.string.女);
        holder.text(R.id.tv_status,accountStatus(data.getStatus()));
        holder.visibility(R.id.ll_show,data.getStatus()==1);
        holder.visibility(R.id.ll_update,data.getStatus()!=1);
        holder.get(R.id.ll_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickShow(data);
                }
            }
        });
        holder.get(R.id.ll_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickUpdate(data);
                }
            }
        });
    }
    private @StringRes  int accountStatus(int status){
        switch (status){
            case 0:
                return R.string.待审核;

            case 1:
                return R.string.成功;
            case 2:
                return R.string.失败;
        }
        return R.string.待审核;
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 点击修改分类
         *
         * @param data
         */
        void onClickShow(AccountInfo data);
        void onClickUpdate(AccountInfo data);
    }
}

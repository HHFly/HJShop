package com.mark.app.hjshop4a.uinew.bindinfo.dialog.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfo;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoPass;

import java.util.List;

public class ChooseAdapter extends BaseListRvAdapter<AccountInfoPass> {
    public ChooseAdapter(List<AccountInfoPass> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_choose_account;
    }

    @Override
    public void bindBodyData(final AutoViewHolder holder, final int bodyPos, final AccountInfoPass data) {
                holder.itemView.setSelected(data.isSelect());
                holder.text(R.id.tv_num,data.getAccountName());
                holder.text(R.id.tv_count,String.format(getString(R.string.剩余接单量),data.getOrderCount()));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClick(bodyPos,data);
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
         * 点击修改分类
         *
         * @param data
         */
        void onClick(int pos, AccountInfoPass data);

    }
}

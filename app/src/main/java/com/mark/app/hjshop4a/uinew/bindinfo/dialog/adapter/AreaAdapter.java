package com.mark.app.hjshop4a.uinew.bindinfo.dialog.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AAddress;

import java.util.List;

public class AreaAdapter extends BaseListRvAdapter<AAddress> {
    public AreaAdapter(List<AAddress> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_address;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, final int bodyPos, final AAddress data) {
        holder.text(R.id.textview,data.getCityName());
        holder.get(R.id.textview).setSelected(data.isStatus());
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
        void onClick(int bodyPos, AAddress data);

    }

}

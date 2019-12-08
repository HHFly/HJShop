package com.mark.app.hjshop4a.uinew.invitation.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.invitation.model.InviteDetailPage;
import com.mark.app.hjshop4a.uinew.invitation.model.InvitePage;

import java.util.List;

public class InviteDetailAdapter extends BaseListRvAdapter<InviteDetailPage> {
    public InviteDetailAdapter(List<InviteDetailPage> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_invite_detail;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, final InviteDetailPage data) {
        holder.text(R.id.tv_num,String.format(getString(R.string.试客账号),data.getUserName()));
        holder.text(R.id.tv_today_gold,String.format(getString(R.string.今日带来的赏金),data.getTodayIncome()));
        holder.text(R.id.tv_all_gold,String.format(getString(R.string.累计带来的赏金),data.getTotalIncome()));
        holder.get(R.id.btn_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickDetial(data);
                }
            }
        });
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
    public interface OnItemClickListener {

        void onClickDetial(InviteDetailPage data);
    }
}

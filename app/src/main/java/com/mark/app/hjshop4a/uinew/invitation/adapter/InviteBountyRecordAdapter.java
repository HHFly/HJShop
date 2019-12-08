package com.mark.app.hjshop4a.uinew.invitation.adapter;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.invitation.model.InviteBountyRecord;

import java.util.List;

public class InviteBountyRecordAdapter extends BaseListRvAdapter<InviteBountyRecord> {
    public InviteBountyRecordAdapter(List<InviteBountyRecord> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_invite_record;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, InviteBountyRecord data) {
            holder.text(R.id.tv_subOrderSn,data.getActivityOrderSn());
            holder.text(R.id.tv_date,data.getShowTime());
            holder.text(R.id.tv_gold,String.valueOf(data.getBounty()));
    }
}

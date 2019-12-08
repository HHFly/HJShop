package com.mark.app.hjshop4a.uinew.invitation.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;

import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.uinew.invitation.model.InvitePage;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThree;

import java.util.List;

public class InvitationAdapter extends MultipleSourcesRvAdapter {
    InvitePage data;
    ClipboardManager cm;
    public InvitationAdapter(InvitePage data) {
        this.data = data;
        //获取剪贴板管理器：
        cm = (ClipboardManager) App.get().getSystemService(Context.CLIPBOARD_SERVICE);

    }
    public void setData(InvitePage data) {
        this.data = data;
    }
    @Override
    public int getRowsCountInSection(int var1) {
        return 1;
    }
    @Override
    public int getSectionsCount() {

        return 1;
    }

    @Override
    public View onCreateView(ViewGroup var1, int var2) {

        return inflater(var1, R.layout.item_invitation);

    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()){
            case 0:
                holder.text(R.id.tv_invitation_count,String.valueOf(data.getTotalCount()));
                holder.text(R.id.tv_invitation_complete,String.valueOf(data.getCompleteOrderCount()));
                holder.text(R.id.tv_invitation_gold,String.valueOf(data.getTotalMoney()));
                holder.text(R.id.tv_url,data.getInviteUrl());
                holder.get(R.id.btn_copy).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipData mClipData = ClipData.newPlainText("Label", data.getInviteUrl());
                        cm.setPrimaryClip(mClipData);
                        ToastUtils.show("复制成功");
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickCopy();
                        }
                    }
                });
                holder.get(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.onClickDetial();
                        }
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
        void onClickCopy();
        void onClickDetial();
    }
}

package com.mark.app.hjshop4a.uinew.orderList;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.common.utils.ToastUtils;

import java.util.List;

public class ShowOrderAdapter extends BaseListRvAdapter<ShowOrder> {
    ClipboardManager cm;
    public ShowOrderAdapter(List<ShowOrder> data) {
        super(data);
        //获取剪贴板管理器：
         cm = (ClipboardManager) App.get().getSystemService(Context.CLIPBOARD_SERVICE);

    }

    @Override
    public int getItemResId() {
        return R.layout.item_getorder_list;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, final ShowOrder data) {
        holder.text(R.id.tv_subOrderSn,data.getSubOrderSn());
        holder.get(R.id.tv_subOrderSn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData mClipData = ClipData.newPlainText("Label", data.getSubOrderSn());
                cm.setPrimaryClip(mClipData);
                ToastUtils.show("复制成功");
            }
        });
        holder.get(R.id.tv_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData mClipData = ClipData.newPlainText("Label", data.getSubOrderSn());
                cm.setPrimaryClip(mClipData);
                ToastUtils.show("复制成功");
            }
        });
        holder.text(R.id.tv_accountName,data.getAccountName());
        holder.text(R.id.tv_payMent,String.format(getString(R.string.S元),String.valueOf(data.getPayMent())));
        holder.text(R.id.tv_step,data.getStatusName());
        holder.text(R.id.tv_bounty,String.format(getString(R.string.S元),String.valueOf(data.getBounty())));
        holder.get(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickClose(data);
                }
            }
        });
        holder.get(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickStart(data);
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
        void onClickClose( ShowOrder data);
        void onClickStart( ShowOrder data);
    }
}

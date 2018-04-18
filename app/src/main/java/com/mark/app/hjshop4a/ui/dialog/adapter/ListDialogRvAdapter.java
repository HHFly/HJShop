package com.mark.app.hjshop4a.ui.dialog.adapter;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.ui.dialog.ListDialog;

import java.util.List;


/**
 * 列表对话框模型
 * Created by lenovo on 2017/9/5.
 */

public class ListDialogRvAdapter extends BaseListRvAdapter<ListDialog.ListDialogModel> {
    public ListDialogRvAdapter(List<ListDialog.ListDialogModel> data) {
        super(data);
    }


    @Override
    public int getItemResId() {
        return R.layout.item_dialog_list;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, final int bodyPos, ListDialog.ListDialogModel data) {
        final ListDialog.ListDialogModel item = data;
        holder.text(R.id.item_dialog_tv, item.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useOnItemClick(v, bodyPos, item);
            }
        });
        boolean isLast = bodyPos == getDataCount() - 1;
        holder.visibility(R.id.item_dialog_line, !isLast);
    }
}

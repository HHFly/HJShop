package com.mark.app.hjshop4a.ui.withdrawdetail;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class WithDrawDetailAdapter extends BaseListRvAdapter<WithDraw> {
    public WithDrawDetailAdapter(List<WithDraw> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_withdraw_detail;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, WithDraw data) {

    }
}

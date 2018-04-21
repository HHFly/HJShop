package com.mark.app.hjshop4a.ui.areaagent.billreview;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBillReviewAdapter extends BaseHasTopListRvAdapter<AreaBillReview,AreaBillReview> {
    public AreaBillReviewAdapter(AreaBillReview areaBillReview, ArrayList<AreaBillReview> areaBillReviews) {
        super(areaBillReview, areaBillReviews);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_areabillreview_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_areabillreview_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, AreaBillReview areaBillReview) {

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, AreaBillReview areaBillReview) {

    }
}

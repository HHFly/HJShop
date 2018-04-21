package com.mark.app.hjshop4a.ui.areaagent.businessreview;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class BusinessReviewAdapter extends BaseHasTopListRvAdapter<BusinessReview,BusinessReview> {
    public BusinessReviewAdapter(BusinessReview businessReview, ArrayList<BusinessReview> businessReviews) {
        super(businessReview, businessReviews);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_business_review_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_business_review_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, BusinessReview businessReview) {

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, BusinessReview businessReview) {

    }
}

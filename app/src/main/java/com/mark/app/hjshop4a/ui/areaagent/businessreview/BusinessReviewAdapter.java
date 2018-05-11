package com.mark.app.hjshop4a.ui.areaagent.businessreview;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.common.utils.BillUtil;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.eumn.AuditStatusProxy;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.model.BusinessReview;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.model.MerchantAuditStays;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class BusinessReviewAdapter extends BaseHasTopListRvAdapter<BusinessReview,MerchantAuditStays> {

    public BusinessReviewAdapter(BusinessReview businessReview, List<MerchantAuditStays> merchantAuditStays) {
        super(businessReview, merchantAuditStays);
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
        if(businessReview!=null) {
            holder.text(R.id.tv_1, businessReview.getMerchantAuditStay().getAuditStayNum());
            holder.text(R.id.tv_2, businessReview.getMerchantAuditStay().getNewShopNum());
            holder.text(R.id.tv_3, businessReview.getMerchantAuditStay().getShopNumTotal());
        }
    }

    @Override
    public void bindBodyData(final AutoViewHolder holder, int bodyPosition, final MerchantAuditStays merchantAuditStays) {
        if(merchantAuditStays!=null){
            holder.visibility(R.id.rl_2,false);
            holder.text(R.id.shopName_2,merchantAuditStays.getShopName());
            holder.text(R.id.userName_2,merchantAuditStays.getUserName());
            holder.text(R.id.applyTime_2,merchantAuditStays.getApplyTime());
            holder.text(R.id.cellPhone_2,merchantAuditStays.getCellPhone());
            switch (merchantAuditStays.getAuditStatusProxy()){
                case AuditStatusProxy.NOREVIEW:
                    break;
                case AuditStatusProxy.PASS:
                    holder.visibility(R.id.rl_review_1,false);
                    holder.text(R.id.auditStatusProxy, BillUtil.swichAuditStatusProxy(merchantAuditStays.getAuditStatusProxy()));
                    break;
                case AuditStatusProxy.UNPASS:
                    holder.visibility(R.id.rl_review_1,false);
                    holder.text(R.id.auditStatusProxy, BillUtil.swichAuditStatusProxy(merchantAuditStays.getAuditStatusProxy()));
                    break;
            }
//            holder.text(R.id.auditStatusProxy, BillUtil.swichAuditStatus(merchantAuditStays.getAuditStatusProxy()));
//            holder.text(R.id.auditStatusProvice, BillUtil.swichAuditStatus(merchantAuditStays.getAuditStatusProvice()));
            holder.get(R.id.tv_1_pass).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickItemYes(merchantAuditStays.getShopId(),holder);
                    }
                }
            });
            holder.get(R.id.tv_1_unpass).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickItemNo(merchantAuditStays.getShopId(),holder);
                    }
                }
            });
            holder.get(R.id.business_review).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickDetails(merchantAuditStays.getShopId());
                    }
                }
            });
        }

    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onClickItemYes(long shopID,AutoViewHolder holder);
        void onClickItemNo(long shopID,AutoViewHolder holder);
        void onClickDetails(long shopID);
    }

}

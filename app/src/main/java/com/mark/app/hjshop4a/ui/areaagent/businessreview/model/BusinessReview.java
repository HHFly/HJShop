package com.mark.app.hjshop4a.ui.areaagent.businessreview.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class BusinessReview extends BaseModel {
    private MerchantAuditStay merchantAuditStay; //商户待审数据
    private List<MerchantAuditStays> merchantAuditStayList;//待审商户列表

    public MerchantAuditStay getMerchantAuditStay() {
        return merchantAuditStay;
    }

    public void setMerchantAuditStay(MerchantAuditStay merchantAuditStay) {
        this.merchantAuditStay = merchantAuditStay;
    }

    public List<MerchantAuditStays> getMerchantAuditStayList() {
        return merchantAuditStayList;
    }

    public void setMerchantAuditStayList(List<MerchantAuditStays> merchantAuditStayList) {
        this.merchantAuditStayList = merchantAuditStayList;
    }
}

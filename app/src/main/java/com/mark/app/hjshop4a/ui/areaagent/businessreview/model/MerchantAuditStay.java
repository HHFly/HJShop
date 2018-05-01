package com.mark.app.hjshop4a.ui.areaagent.businessreview.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/5/1.
 */

public class MerchantAuditStay extends BaseModel {
    private  String auditStayNum;//待审核
    private  String newShopNum;//新增店铺（日）
    private  String shopNumTotal;//店铺数量（累积）

    public String getAuditStayNum() {
        return auditStayNum;
    }

    public void setAuditStayNum(String auditStayNum) {
        this.auditStayNum = auditStayNum;
    }

    public String getNewShopNum() {
        return newShopNum;
    }

    public void setNewShopNum(String newShopNum) {
        this.newShopNum = newShopNum;
    }

    public String getShopNumTotal() {
        return shopNumTotal;
    }

    public void setShopNumTotal(String shopNumTotal) {
        this.shopNumTotal = shopNumTotal;
    }
}

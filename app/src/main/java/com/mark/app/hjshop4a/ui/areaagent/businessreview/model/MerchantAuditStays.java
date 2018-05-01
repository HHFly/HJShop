package com.mark.app.hjshop4a.ui.areaagent.businessreview.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/5/1.
 */

public class MerchantAuditStays extends BaseModel {
    private String shopName;//店铺名称
    private long shopId;//店铺ID
    private String userName;//店主
    private String cellPhone;//联系电话
    private String applyTime;//	申请时间
    private int auditStatus;//审核状态 0 待审核 1 审核中 2 审核通过 3 审核不通过
    private int auditStatusProxy;//代理商（市）审核状态 0 待审核 1 审核通过 2 审核不通过
    private int auditStatusProvice;//代理商（省）审核状态 0 待审核 1 审核通过 2 审核不通过

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public int getAuditStatusProxy() {
        return auditStatusProxy;
    }

    public void setAuditStatusProxy(int auditStatusProxy) {
        this.auditStatusProxy = auditStatusProxy;
    }

    public int getAuditStatusProvice() {
        return auditStatusProvice;
    }

    public void setAuditStatusProvice(int auditStatusProvice) {
        this.auditStatusProvice = auditStatusProvice;
    }
}

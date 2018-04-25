package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class Balance extends BaseModel {
    private String consumeMoney;//消费金额
    private String shopName;//消费店铺名称
    private String shopId;//店铺ID
    private int comsumerType;//消费类型 1 线下消费
    private int offlineOrderAuditStatus;//订单状态 0 待审核 1 审核中 2 审核通过 3 审核不通过
    private String offlineOrderSn;//订单号
    private String offlineOrderTime;//订单消费时间

    public String getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(String consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getComsumerType() {
        return comsumerType;
    }

    public void setComsumerType(int comsumerType) {
        this.comsumerType = comsumerType;
    }

    public int getOfflineOrderAuditStatus() {
        return offlineOrderAuditStatus;
    }

    public void setOfflineOrderAuditStatus(int offlineOrderAuditStatus) {
        this.offlineOrderAuditStatus = offlineOrderAuditStatus;
    }

    public String getOfflineOrderSn() {
        return offlineOrderSn;
    }

    public void setOfflineOrderSn(String offlineOrderSn) {
        this.offlineOrderSn = offlineOrderSn;
    }

    public String getOfflineOrderTime() {
        return offlineOrderTime;
    }

    public void setOfflineOrderTime(String offlineOrderTime) {
        this.offlineOrderTime = offlineOrderTime;
    }
}

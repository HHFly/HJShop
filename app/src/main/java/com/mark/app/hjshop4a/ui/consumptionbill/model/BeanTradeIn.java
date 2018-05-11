package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class BeanTradeIn extends BaseModel {
    private  String tradeInMoney;//兑换金额
    private  String discounts;//手续费
    private  String toAccountMoney;//到账金额
    private  int auditStatus;//状态 1 待审核 1 审核中 2 审核通过 3 审核不通过
    private  String toAccountTime;//到账时间
    private String tradeInSn;

    public String getTradeInSn() {
        return tradeInSn;
    }

    public void setTradeInSn(String tradeInSn) {
        this.tradeInSn = tradeInSn;
    }

    public String getTradeInMoney() {
        return tradeInMoney;
    }

    public void setTradeInMoney(String tradeInMoney) {
        this.tradeInMoney = tradeInMoney;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public String getToAccountMoney() {
        return toAccountMoney;
    }

    public void setToAccountMoney(String toAccountMoney) {
        this.toAccountMoney = toAccountMoney;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getToAccountTime() {
        return toAccountTime;
    }

    public void setToAccountTime(String toAccountTime) {
        this.toAccountTime = toAccountTime;
    }
}

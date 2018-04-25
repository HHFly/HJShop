package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class BalanceWithDraw extends BaseModel {
    private String withDrawMoney;//提现金额
    private String toAccountMoney;//到账金额
    private int auditStatus;//状态 0 待审核 1 审核中 2 审核通过 3 审核不通过
    private String withDrawTime;//提现时间

    public String getWithDrawMoney() {
        return withDrawMoney;
    }

    public void setWithDrawMoney(String withDrawMoney) {
        this.withDrawMoney = withDrawMoney;
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

    public String getWithDrawTime() {
        return withDrawTime;
    }

    public void setWithDrawTime(String withDrawTime) {
        this.withDrawTime = withDrawTime;
    }
}

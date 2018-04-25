package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class TopUp extends BaseModel {
    private  String topUpMoney;//充值金额
    private  String toAccountMoney;//到账金额
    private  String topUptime;//充值时间


    public String getTopUpMoney() {
        return topUpMoney;
    }

    public void setTopUpMoney(String topUpMoney) {
        this.topUpMoney = topUpMoney;
    }

    public String getToAccountMoney() {
        return toAccountMoney;
    }

    public void setToAccountMoney(String toAccountMoney) {
        this.toAccountMoney = toAccountMoney;
    }

    public String getTopUptime() {
        return topUptime;
    }

    public void setTopUptime(String topUptime) {
        this.topUptime = topUptime;
    }
}

package com.mark.app.hjshop4a.ui.withdraw.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/5/3.
 */

public class WithDraw extends BaseModel {
    /**
     * 账户余额
     */
    private String accountBalance;

    /**
     * 折扣
     */
    private String discounts;

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }
}

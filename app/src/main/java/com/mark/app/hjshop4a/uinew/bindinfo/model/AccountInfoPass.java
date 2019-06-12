package com.mark.app.hjshop4a.uinew.bindinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class AccountInfoPass extends BaseModel {
    long buyerAccountId;
    String accountName;
    int orderCount;
    boolean isSelect=false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public long getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(long buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}

package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class ReciveOrderParam extends BaseModel {
    long buyerAccountId;
    String subOrderSn;

    public long getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(long buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public String getSubOrderSn() {
        return subOrderSn;
    }

    public void setSubOrderSn(String subOrderSn) {
        this.subOrderSn = subOrderSn;
    }
}

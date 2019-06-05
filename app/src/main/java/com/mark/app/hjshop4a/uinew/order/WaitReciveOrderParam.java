package com.mark.app.hjshop4a.uinew.order;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class WaitReciveOrderParam extends BaseModel {
    long buyerAccountId; //买手账号Id

    public long getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(long buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }
}

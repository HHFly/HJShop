package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class CloseOrderParam extends BaseModel {
    String subOrderSn;
    int failReasonId;

    public String getSubOrderSn() {
        return subOrderSn;
    }

    public void setSubOrderSn(String subOrderSn) {
        this.subOrderSn = subOrderSn;
    }

    public int getFailReasonId() {
        return failReasonId;
    }

    public void setFailReasonId(int failReasonId) {
        this.failReasonId = failReasonId;
    }
}

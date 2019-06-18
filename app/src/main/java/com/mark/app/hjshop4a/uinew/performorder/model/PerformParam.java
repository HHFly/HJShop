package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class PerformParam extends BaseModel {
    String subOrderSn;
    int step;

    public String getSubOrderSn() {
        return subOrderSn;
    }

    public void setSubOrderSn(String subOrderSn) {
        this.subOrderSn = subOrderSn;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}

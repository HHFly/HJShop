package com.mark.app.hjshop4a.ui.onlinerecharge.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/24.
 */

public class OnlineRecharge extends BaseModel {
    private int payWayCode; //支付方式ID
    private  String payWayName;//支付方式名称

    public int getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(int payWayCode) {
        this.payWayCode = payWayCode;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }
}

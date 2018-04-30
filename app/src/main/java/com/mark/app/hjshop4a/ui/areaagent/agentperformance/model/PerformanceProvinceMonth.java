package com.mark.app.hjshop4a.ui.areaagent.agentperformance.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by hui on 2018/4/29.
 */

public class PerformanceProvinceMonth extends BaseModel {
    private String turnOver;//营业额
    private String userNum;//会员数量
    private String  shopNum;//商家数量

    public String getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(String turnOver) {
        this.turnOver = turnOver;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }
}

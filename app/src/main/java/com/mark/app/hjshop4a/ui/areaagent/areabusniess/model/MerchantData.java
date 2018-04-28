package com.mark.app.hjshop4a.ui.areaagent.areabusniess.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/28.
 */

public class MerchantData extends BaseModel {
    private String shopName;//店铺名称
    private String userName;//店主
    private String cellPhone;//联系方式
    private String regTime;//注册时间
    private String turnOver;//营业额(截止前一天)


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(String turnOver) {
        this.turnOver = turnOver;
    }
}

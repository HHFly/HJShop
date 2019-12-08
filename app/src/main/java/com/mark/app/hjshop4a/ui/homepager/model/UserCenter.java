package com.mark.app.hjshop4a.ui.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class UserCenter extends BaseModel {
    String userPhone;//电话
    double usableMoney;//货款余额

    double usableGold;//金币余额
    int integral;//积分

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public double getUsableMoney() {
        return usableMoney;
    }

    public void setUsableMoney(double usableMoney) {
        this.usableMoney = usableMoney;
    }

    public double getUsableGold() {
        return usableGold;
    }

    public void setUsableGold(double usableGold) {
        this.usableGold = usableGold;
    }
}

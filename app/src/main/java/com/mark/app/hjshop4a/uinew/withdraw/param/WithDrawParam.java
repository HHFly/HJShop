package com.mark.app.hjshop4a.uinew.withdraw.param;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class WithDrawParam extends BaseModel {
    String money;
    String gold;
    String payPasswd;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getPayPasswd() {
        return payPasswd;
    }

    public void setPayPasswd(String payPasswd) {
        this.payPasswd = payPasswd;
    }
}

package com.mark.app.hjshop4a.uinew.bill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class BillRecord extends BaseModel {
    String recordName;//记录名称
    String createTime;//创建时间
    String money;//货款
    String gold;//金币

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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
}

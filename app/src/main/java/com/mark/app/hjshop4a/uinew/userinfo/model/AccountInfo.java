package com.mark.app.hjshop4a.uinew.userinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class AccountInfo extends BaseModel {
    long buyerAccountId;//账号Id
    String accountName;//账号名称
    int status;//状态 0待审 1成功 2失败
    String receiverName; //收货人名称
    String receiverPhone;//收货人电话
    int sex;//1男 2女

    public long getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(long buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}

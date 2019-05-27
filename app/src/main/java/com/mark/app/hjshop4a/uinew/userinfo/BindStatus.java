package com.mark.app.hjshop4a.uinew.userinfo;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class BindStatus extends BaseModel {
    int idCardStatus;//身份绑定状态 -1待上传 0待审核 1审核通过 2审核失败
    int bankStatus;//银行绑定状态 0否 1是
    int qqStatus;//qq绑定状态0否 1是

    int accountStatus;//账号绑定状态0否 1是
    int wechatStatus;//微信绑定状态0否 1是


    public int getIdCardStatus() {
        return idCardStatus;
    }

    public void setIdCardStatus(int idCardStatus) {
        this.idCardStatus = idCardStatus;
    }

    public int getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(int bankStatus) {
        this.bankStatus = bankStatus;
    }

    public int getQqStatus() {
        return qqStatus;
    }

    public void setQqStatus(int qqStatus) {
        this.qqStatus = qqStatus;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getWechatStatus() {
        return wechatStatus;
    }

    public void setWechatStatus(int wechatStatus) {
        this.wechatStatus = wechatStatus;
    }
}

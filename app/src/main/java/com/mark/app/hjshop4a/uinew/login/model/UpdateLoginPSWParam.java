package com.mark.app.hjshop4a.uinew.login.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class UpdateLoginPSWParam extends BaseModel {
    int type;//修改类型1旧密码 2短信验证
    String  newPassword;
    String oldPassword;
    String verificationCode;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

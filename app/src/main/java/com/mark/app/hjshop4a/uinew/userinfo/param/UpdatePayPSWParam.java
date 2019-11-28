package com.mark.app.hjshop4a.uinew.userinfo.param;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class UpdatePayPSWParam extends BaseModel {

    String newPassword;//新密码


    String verificationCode;//验证码

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

package com.mark.app.hjshop4a.uinew.login.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.base.model.ParamBaseModel;

public class LoginParam extends BaseModel {
    String userPhone;//电话
    int type;//类型1密码登陆 2短信登陆

    String verification;
    String password;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

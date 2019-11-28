package com.mark.app.hjshop4a.uinew.login.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class SMSParam extends BaseModel {
    String userPhone;//手机号码
    int type; //1注册 2登陆 3重置密码 4绑定银行卡 5 支付密码

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
}

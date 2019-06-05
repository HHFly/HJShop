package com.mark.app.hjshop4a.uinew.userinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class UserInfo extends BaseModel {
    String  wechat;
    String qq;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}

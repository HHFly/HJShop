package com.mark.app.hjshop4a.uinew.login.model;

import android.text.TextUtils;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class Token extends BaseModel {
    private String bindPhone;//登陆账户
    private String accessToken;//token
    @Override
    public String toString() {
        return "[用户名]" + bindPhone + "\n" +
                "[token]" + accessToken + "\n" ;
    }
    public boolean isNull() {
        return TextUtils.isEmpty(getAccessToken());
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

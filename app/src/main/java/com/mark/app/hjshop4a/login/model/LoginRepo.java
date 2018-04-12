package com.mark.app.hjshop4a.login.model;

import android.text.TextUtils;

import com.mark.app.hjshop4a.base.model.BaseModel;


/**
 * 登录成功返回的token信息
 * Created by zhuwh on 2017/10/10.
 */

public class LoginRepo extends BaseModel {
    private String username;//用户名
    private String accessToken;//token
    private int expiresIn;//有效时长（毫秒）
    private long nowTime;//系统时间
    private String refresh_token;//刷新token
    private long endTime;//token过期时间
    private String userPushToken;//推送token

    public LoginRepo() {
    }

    @Override
    public String toString() {
        return "[用户名]" + username + "\n" +
                "[token]" + accessToken + "\n" +
                "[推送别名]" + userPushToken;
    }

    public String getUsername() {
        return username;
    }

    public LoginRepo setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getNowTime() {
        return nowTime;
    }

    public LoginRepo setNowTime(long nowTime) {
        this.nowTime = nowTime;
        return this;
    }

    public String getUserPushToken() {
        return userPushToken;
    }

    public void setUserPushToken(String userPushToken) {
        this.userPushToken = userPushToken;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isNull() {
        return TextUtils.isEmpty(getAccessToken())
                || TextUtils.isEmpty(getRefreshToken())
                || getExpiresIn() == 0;
    }

    public LoginRepo setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public LoginRepo setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public LoginRepo setRefreshToken(String refreshToken) {
        this.refresh_token = refreshToken;
        return this;
    }

}

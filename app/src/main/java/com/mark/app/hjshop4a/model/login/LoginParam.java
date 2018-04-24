package com.mark.app.hjshop4a.model.login;


import com.mark.app.hjshop4a.base.model.ParamBaseModel;
import com.mark.app.hjshop4a.common.utils.MD5Utils;

/**
 * 登陆注册接口请求参数
 * Created by lenovo on 2017/9/14.
 */

public class LoginParam extends ParamBaseModel {
    //登陆account、password
    //注册account、password、captcha、inviteCode
    private String account;     //电话
    private String captcha;     //验证码
    private String password;    //密码
    private String inviteCode;  //邀请码
    private  long addressConfigId; //地址配置Id
    //找回密码userName、verification、passwd
    private String userName;
    private String verification;
    private String passwd;

    /**
     * 设置加密密码
     *
     * @param password
     */
    public void setPassword(String password) {
        password = MD5Utils.md5(password);
        this.password = password;
    }

    /**
     * 设置加密密码
     *
     * @param passwd
     */
    public void setPasswd(String passwd) {
        passwd = MD5Utils.md5(passwd);
        this.passwd = passwd;
    }

    public long getAddressConfigId() {
        return addressConfigId;
    }

    public void setAddressConfigId(long addressConfigId) {
        this.addressConfigId = addressConfigId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}

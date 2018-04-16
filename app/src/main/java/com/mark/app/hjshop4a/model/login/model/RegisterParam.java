package com.mark.app.hjshop4a.model.login.model;


import com.mark.app.hjshop4a.base.model.ParamBaseModel;

/**
 * 注册接口参数
 * Created by lenovo on 2017/10/29.
 */

public class RegisterParam extends ParamBaseModel {
    private String proposer;
    private String account;
    private String mobile;
    private String captcha;
    private String password;
    private String shopName;
    private String shopTelephone;
    private long shopCategoryId;
    private String shopAddress;
    private String shopLong;
    private String shopLag;
    private String shopContent;

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTelephone() {
        return shopTelephone;
    }

    public void setShopTelephone(String shopTelephone) {
        this.shopTelephone = shopTelephone;
    }

    public long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLong() {
        return shopLong;
    }

    public void setShopLong(String shopLong) {
        this.shopLong = shopLong;
    }

    public String getShopLag() {
        return shopLag;
    }

    public void setShopLag(String shopLag) {
        this.shopLag = shopLag;
    }

    public String getShopContent() {
        return shopContent;
    }

    public void setShopContent(String shopContent) {
        this.shopContent = shopContent;
    }
}

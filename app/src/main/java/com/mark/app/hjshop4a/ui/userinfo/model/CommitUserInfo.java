package com.mark.app.hjshop4a.ui.userinfo.model;

import com.mark.app.hjshop4a.base.model.ParamBaseModel;

/**
 * Created by pc on 2018/4/26.
 */

public class CommitUserInfo extends ParamBaseModel {
    private  String userName;
    private  long provinceId;
    private  long cityId;
    private  long countyId;
    private  String completeAddress;
    private  String userRealName;
    private  String birthday;
    private  String gender;
    private  String userIdCard;
    private  String userIdCardFront;
    private  String userIdCardSide;
    private  String oldPassword;
    private  String newPassword;
    private  String payPassword;
    private  String userHeadImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getCountyId() {
        return countyId;
    }

    public void setCountyId(long countyId) {
        this.countyId = countyId;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserIdCardFront() {
        return userIdCardFront;
    }

    public void setUserIdCardFront(String userIdCardFront) {
        this.userIdCardFront = userIdCardFront;
    }

    public String getUserIdCardSide() {
        return userIdCardSide;
    }

    public void setUserIdCardSide(String userIdCardSide) {
        this.userIdCardSide = userIdCardSide;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }
}

package com.mark.app.hjshop4a.ui.userinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.base.model.ParamBaseModel;

/**
 * Created by pc on 2018/4/26.
 */

public class UserInfo extends BaseModel {
    private  String userName;
    private  long provinceId;
    private  String provinceName;
    private  String cityName;
    private  long cityId;
    private  String countyName;
    private  long countyId;
    private  String completeAddress;
    private  int userAuditStatus;
    private  String userRealName;
    private  String birthday;
    private  int gender;
    private  String recommend;
    private  String userHeadImg;
    private String userTypeId;

    private  String userIdCard;
    private  String userIdCardFront;
    private  String userIdCardSide;

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public int getUserAuditStatus() {
        return userAuditStatus;
    }

    public void setUserAuditStatus(int userAuditStatus) {
        this.userAuditStatus = userAuditStatus;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }
}

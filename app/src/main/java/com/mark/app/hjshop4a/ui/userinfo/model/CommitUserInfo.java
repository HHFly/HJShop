package com.mark.app.hjshop4a.ui.userinfo.model;

import com.mark.app.hjshop4a.base.model.ParamBaseModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private  long birthday;
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

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = getStringToDate(birthday);
    }
    public static long getStringToDate(String dateString) {
        String pattern = "yyyy年MM月dd日";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
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

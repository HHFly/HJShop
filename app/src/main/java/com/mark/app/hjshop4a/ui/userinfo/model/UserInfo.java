package com.mark.app.hjshop4a.ui.userinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.base.model.ParamBaseModel;

/**
 * Created by pc on 2018/4/26.
 */

public class UserInfo extends BaseModel {
    private  String userName; //用户名
    private  long provinceId; //	省ID
    private  String provinceName;//省份名称
    private  String cityName;//市
    private  long cityId;//市ID
    private  String countyName;//区县
    private  long countyId;//区县ID
    private  String completeAddress;//详细地址
    private  int userAuditStatus;//审核状态 0 待审核 1审核中 2审核通过 3 审核不通过
    private  String userRealName;//	姓名
    private  String birthday;//出生日期
    private  int gender;//性别 0 保密 1 男 2 女
    private  String recommend;//推荐人
    private  String userHeadImg;//用户头像
    private String userTypeId;//用户角色ID

    private  String idCard;
    private  String idCardFront;
    private  String idCardSide;

    public String getIdcard() {
        return idCard;
    }

    public void setIdcard(String idcard) {
        this.idCard = idcard;
    }

    public String getIdcardFront() {
        return idCardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idCardFront = idcardFront;
    }

    public String getIdcardSide() {
        return idCardSide;
    }

    public void setIdcardSide(String idcardSide) {
        this.idCardSide = idcardSide;
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

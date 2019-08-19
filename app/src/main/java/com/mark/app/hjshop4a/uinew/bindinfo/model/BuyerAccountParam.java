package com.mark.app.hjshop4a.uinew.bindinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.base.model.ParamBaseModel;

public class BuyerAccountParam extends BaseModel {
    String accountName;//账号名称
    String receiverName;//收货人
    String receiverPhone;//收货电话
    int addressId;//收货地址Id
    String addresDetail;//收货详细地址
    int sex;//性别
    int age;//年龄
    int level; //等级
    String shoppingType;//购买类型（"1/2/3"）
    String levelPic;//等级图片
    String huabeiPic;//花呗图片
    int isHuabei;//是否花呗

    String realAuthenticatePic;//认证图片
    String provinceId;
    String cityId;
    String areaId;
    String provinceName;
    String cityName;
    String areaName;
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddresDetail() {
        return addresDetail;
    }

    public void setAddresDetail(String addresDetail) {
        this.addresDetail = addresDetail;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(String shoppingType) {
        this.shoppingType = shoppingType;
    }

    public String getLevelPic() {
        return levelPic;
    }

    public void setLevelPic(String levelPic) {
        this.levelPic = levelPic;
    }

    public String getHuabeiPic() {
        return huabeiPic;
    }

    public void setHuabeiPic(String huabeiPic) {
        this.huabeiPic = huabeiPic;
    }

    public int getIsHuabei() {
        return isHuabei;
    }

    public void setIsHuabei(int isHuabei) {
        this.isHuabei = isHuabei;
    }

    public String getRealAuthenticatePic() {
        return realAuthenticatePic;
    }

    public void setRealAuthenticatePic(String realAuthenticatePic) {
        this.realAuthenticatePic = realAuthenticatePic;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

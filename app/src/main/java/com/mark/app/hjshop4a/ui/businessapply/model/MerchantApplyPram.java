package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.ParamBaseModel;

/**
 * Created by pc on 2018/5/3.
 */

public class MerchantApplyPram extends ParamBaseModel {
    private  String shopName; //店铺名称
    private  long provinceId;//省ID
    private  long cityId;//市ID
    private  long countyId;//区ID
    private  String completeAddress;//详细地址
    private  long shopCateogryId;//	类目ID
    private  String companyName;//公司名称
    private  String shopLicence;//营业执照
    private  String shopImg;//店铺形象照片
    private  String shopInImg1;//店铺内照片1
    private  String shopInImg2;//	店铺内照片2
    private  String shopInImg3;//店铺内照片3
    private  String shopInImg4;//店铺内照片4
    private  String isReadProtocol;//用户是否阅读协议 0 否 1 是

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

    public long getShopCateogryId() {
        return shopCateogryId;
    }

    public void setShopCateogryId(long shopCateogryId) {
        this.shopCateogryId = shopCateogryId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShopLicence() {
        return shopLicence;
    }

    public void setShopLicence(String shopLicence) {
        this.shopLicence = shopLicence;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopInImg1() {
        return shopInImg1;
    }

    public void setShopInImg1(String shopInImg1) {
        this.shopInImg1 = shopInImg1;
    }

    public String getShopInImg2() {
        return shopInImg2;
    }

    public void setShopInImg2(String shopInImg2) {
        this.shopInImg2 = shopInImg2;
    }

    public String getShopInImg3() {
        return shopInImg3;
    }

    public void setShopInImg3(String shopInImg3) {
        this.shopInImg3 = shopInImg3;
    }

    public String getShopInImg4() {
        return shopInImg4;
    }

    public void setShopInImg4(String shopInImg4) {
        this.shopInImg4 = shopInImg4;
    }

    public String getIsReadProtocol() {
        return isReadProtocol;
    }

    public void setIsReadProtocol(String isReadProtocol) {
        this.isReadProtocol = isReadProtocol;
    }
}

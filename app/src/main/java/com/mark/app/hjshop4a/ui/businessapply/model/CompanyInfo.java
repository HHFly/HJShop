package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class CompanyInfo extends BaseModel {
    private  String companyName;//公司名称
    private  AddressInfo addressInfo;//地址信息
    private  String shopName;//店铺名称
    private  long shopCategoryId;//店铺id(当前被选中)
    private String shopCategoryName;//类目名称
    private  String licencePic;//营业执照照片
    private  String shopImg;//店铺形象照片
    private  String shopInImg1;//店铺内照片1
    private  String shopInImg2;//店铺形象照片
    private  String shopInImg3;//店铺形象照片
    private  String shopInImg4;//店铺形象照片
    private long  shopId;//

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public String getLicencePic() {
        return licencePic;
    }

    public void setLicencePic(String licencePic) {
        this.licencePic = licencePic;
    }

    public long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
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
}

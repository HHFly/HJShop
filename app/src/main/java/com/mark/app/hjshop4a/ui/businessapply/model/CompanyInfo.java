package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/25.
 */

public class CompanyInfo extends BaseModel {
    private  String companyName;//公司名称
    private  AddressInfo addressInfo;//地址信息
    private  String shopName;//店铺名称
    private  ShopCategory shopCategory;//店铺分类(当前被选中)
    private  String licencePic;//营业执照照片
    private List<String> shopImagesIn;//店铺内照片
    private  String shopImages;//店铺形象照片

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


    public String getShopImages() {
        return shopImages;
    }

    public void setShopImages(String shopImages) {
        this.shopImages = shopImages;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public List<String> getShopImagesIn() {
        return shopImagesIn;
    }

    public void setShopImagesIn(List<String> shopImagesIn) {
        this.shopImagesIn = shopImagesIn;
    }
}

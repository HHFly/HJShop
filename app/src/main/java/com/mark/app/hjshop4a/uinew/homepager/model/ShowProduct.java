package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class ShowProduct extends BaseModel {
    String productUrl; //商品图片地址
    String productPrice;//商品价格

    String payCount;//商品销售数量

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getPayCount() {
        return payCount;
    }

    public void setPayCount(String payCount) {
        this.payCount = payCount;
    }
}

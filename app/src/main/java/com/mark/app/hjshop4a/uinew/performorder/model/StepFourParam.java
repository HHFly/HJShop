package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class StepFourParam extends BaseModel {
    String notPay;
    String  collectProduct;
    String collectShop;
    String addShoppingCart;

    public String getNotPay() {
        return notPay;
    }

    public void setNotPay(String notPay) {
        this.notPay = notPay;
    }

    public String getCollectProduct() {
        return collectProduct;
    }

    public void setCollectProduct(String collectProduct) {
        this.collectProduct = collectProduct;
    }

    public String getCollectShop() {
        return collectShop;
    }

    public void setCollectShop(String collectShop) {
        this.collectShop = collectShop;
    }

    public String getAddShoppingCart() {
        return addShoppingCart;
    }

    public void setAddShoppingCart(String addShoppingCart) {
        this.addShoppingCart = addShoppingCart;
    }
}

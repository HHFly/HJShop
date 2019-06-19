package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

public class StepTwo extends BaseModel {
    int isAddProductFlage;//是否需要附加商品
    String productImg;//主商品图片
    String searchKeyWord;//主商品搜索关键词
    String shopName;//商家名称
    String wwName;//旺旺名称
    String priceRange;//商品价格区间

    double mainProductPrice;//商品
    String require;//主商品需要上传图片信息
    TaskPic taskPic;//截图信息
    AddProduct addProductList;//附加商品列表


    public int getIsAddProductFlage() {
        return isAddProductFlage;
    }

    public void setIsAddProductFlage(int isAddProductFlage) {
        this.isAddProductFlage = isAddProductFlage;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getWwName() {
        return wwName;
    }

    public void setWwName(String wwName) {
        this.wwName = wwName;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public double getMainProductPrice() {
        return mainProductPrice;
    }

    public void setMainProductPrice(double mainProductPrice) {
        this.mainProductPrice = mainProductPrice;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public TaskPic getTaskPic() {
        return taskPic;
    }

    public void setTaskPic(TaskPic taskPic) {
        this.taskPic = taskPic;
    }

    public AddProduct getAddProductList() {
        return addProductList;
    }

    public void setAddProductList(AddProduct addProductList) {
        this.addProductList = addProductList;
    }
}

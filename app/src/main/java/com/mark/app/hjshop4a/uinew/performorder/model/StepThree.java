package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class StepThree extends BaseModel {
    String productImg;
    String searchKeyWord;
    String shopName;
    String wwName;
    String priceRange;
    double mainProductPrice;
    String require;
    TaskPic taskPic;

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
}

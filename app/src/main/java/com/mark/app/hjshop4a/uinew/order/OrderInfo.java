package com.mark.app.hjshop4a.uinew.order;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class OrderInfo extends BaseModel {
    String orderType;//订单类型名称
    String subOrderSn; //子订单编号
    String productPic;//商品图片
    double shopPayPrice;//垫付货款
    double gold;//佣金
    String require;//需求

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSubOrderSn() {
        return subOrderSn;
    }

    public void setSubOrderSn(String subOrderSn) {
        this.subOrderSn = subOrderSn;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public double getShopPayPric() {
        return shopPayPrice;
    }

    public void setShopPayPric(double shopPayPric) {
        this.shopPayPrice = shopPayPric;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }
}

package com.mark.app.hjshop4a.model.onlinerecharhe;


import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * 创建订单返回模型
 * Created by lenovo on 2017/9/11.
 */

public class PayInfo extends BaseModel {
    /*
    * 国家
    * */
    private  String country;
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 支付方式
     */
    private List<Pay> payList;
    /**
     * 支付方式
     */
    private String payWay;
    /**
     * 一口价
     */
    private String fixedPrice;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public List<Pay> getPayList() {
        return payList;
    }

    public void setPayList(List<Pay> payList) {
        this.payList = payList;
    }

    public String getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(String fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
}

package com.mark.app.hjshop4a.uinew.homepager.orderList;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class ShowOrder extends BaseModel {

    String subOrderSn;//订单编号
    String productImg;//商品主图
    String accountName;//接单账号
    double payMent;//货款

    int step;//当前步骤

    int status;//status状态
    String statusName;//状态名称
    double bounty;//金币

    public String getSubOrderSn() {
        return subOrderSn;
    }

    public void setSubOrderSn(String subOrderSn) {
        this.subOrderSn = subOrderSn;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getPayMent() {
        return payMent;
    }

    public void setPayMent(double payMent) {
        this.payMent = payMent;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }
}

package com.mark.app.hjshop4a.ui.areaagent.billreview.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/30.
 */

public class Customs extends BaseModel {
    private String offlineOrderSn;//	订单号
    private String  customsUerName;//	消费会员
    private String  productName;//	商品名称
    private int  productNum;//商品数量
    private String  customsMoney; //消费金额
    private String  serviceMony;  //	服务费
    private String  customsTypeName;//类型名
    private int  customsStatus;//状态 0 待审核 1 审核中 2 审核通过 3 审核不通过
    private String  customsTime;//时间
    private String shopName;//店铺名称
    private  int roleCustomsStatus;// 状态 0 待审核 1 审核通过 2 审核不通过

    public int getRoleCustomsStatus() {
        return roleCustomsStatus;
    }

    public void setRoleCustomsStatus(int roleCustomsStatus) {
        this.roleCustomsStatus = roleCustomsStatus;
    }

    public String getOfflineOrderSn() {
        return offlineOrderSn;
    }

    public void setOfflineOrderSn(String offlineOrderSn) {
        this.offlineOrderSn = offlineOrderSn;
    }

    public String getCustomsUerName() {
        return customsUerName;
    }

    public void setCustomsUerName(String customsUerName) {
        this.customsUerName = customsUerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getCustomsMoney() {
        return customsMoney;
    }

    public void setCustomsMoney(String customsMoney) {
        this.customsMoney = customsMoney;
    }

    public String getServiceMony() {
        return serviceMony;
    }

    public void setServiceMony(String serviceMony) {
        this.serviceMony = serviceMony;
    }

    public String getCustomsTypeName() {
        return customsTypeName;
    }

    public void setCustomsTypeName(String customsTypeName) {
        this.customsTypeName = customsTypeName;
    }

    public int getCustomsStatus() {
        return customsStatus;
    }

    public void setCustomsStatus(int customsStatus) {
        this.customsStatus = customsStatus;
    }

    public String getCustomsTime() {
        return customsTime;
    }

    public void setCustomsTime(String customsTime) {
        this.customsTime = customsTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}

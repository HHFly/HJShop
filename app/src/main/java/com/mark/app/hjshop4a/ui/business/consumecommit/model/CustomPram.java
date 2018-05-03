package com.mark.app.hjshop4a.ui.business.consumecommit.model;

import com.mark.app.hjshop4a.base.model.ParamBaseModel;

/**
 * Created by pc on 2018/5/3.
 */

public class CustomPram extends ParamBaseModel {
    private  String cellPhone; //会员账户（手机号）
    private  long modelId;//模式ID
    private  String ocMoeny;//线下消费金额
    private  String productName;//商品名称
    private  int productNum;//商品数量
    private  String productPrice;//商品单价
    private  String buyProof;//购买单据
    private  String captchaPc;//	验证码

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getOcMoeny() {
        return ocMoeny;
    }

    public void setOcMoeny(String ocMoeny) {
        this.ocMoeny = ocMoeny;
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getBuyProof() {
        return buyProof;
    }

    public void setBuyProof(String buyProof) {
        this.buyProof = buyProof;
    }

    public String getCaptchaPc() {
        return captchaPc;
    }

    public void setCaptchaPc(String captchaPc) {
        this.captchaPc = captchaPc;
    }
}

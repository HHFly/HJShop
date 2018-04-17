package com.mark.app.hjshop4a.model.onlinerecharhe;


import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * 支付方式
 * Created by lenovo on 2017/10/6.
 */

public class Pay extends BaseModel {
    /**
     * 支付id
     */
    private long payWayId;
    /**
     * 支付名称
     */
    private String payWayName;
    /**
     * 支付图片
     */
    private String payWayImg;
    /**
     * 平台
     */
    private int platform;
    /**
     * 版本号
     */
    private String version;
    /**
     * 支付类型
     */
    private int payType;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public long getPayWayId() {
        return payWayId;
    }

    public void setPayWayId(long payWayId) {
        this.payWayId = payWayId;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public String getPayWayImg() {
        return payWayImg;
    }

    public void setPayWayImg(String payWayImg) {
        this.payWayImg = payWayImg;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

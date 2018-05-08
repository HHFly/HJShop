package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/5/7.
 */

public class MemberGoldBeanTradeIn extends BaseModel {
    /**
     * 时间
     */
    private String tradeInTime;

    /**
     * 会员账号
     */
    private String userCellphone;

    /**
     * 消费金豆
     */
    private String tradeInBean;

    /**
     * 抵扣金额
     */
    private String tradeInPrice;

    /**
     * 换购编号
     */
    private String tradeInSn;

    /**
     * 消费类型
     */
    private String consumeType;

    public String getTradeInTime() {
        return tradeInTime;
    }

    public void setTradeInTime(String tradeInTime) {
        this.tradeInTime = tradeInTime;
    }

    public String getUserCellphone() {
        return userCellphone;
    }

    public void setUserCellphone(String userCellphone) {
        this.userCellphone = userCellphone;
    }

    public String getTradeInBean() {
        return tradeInBean;
    }

    public void setTradeInBean(String tradeInBean) {
        this.tradeInBean = tradeInBean;
    }

    public String getTradeInPrice() {
        return tradeInPrice;
    }

    public void setTradeInPrice(String tradeInPrice) {
        this.tradeInPrice = tradeInPrice;
    }

    public String getTradeInSn() {
        return tradeInSn;
    }

    public void setTradeInSn(String tradeInSn) {
        this.tradeInSn = tradeInSn;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    //金豆消费  换购编号 会员账号 消费金豆 抵扣金额 消费类型
}

package com.mark.app.hjshop4a.uinew.userinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class UserCardInfo extends BaseModel {
    String realName;//string	真实姓名

    String idCard; //身份证号
    String idCardFront;//身份证正面

    String idCardBack;//身份证反面

    String failReason;//失败原因


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}

package com.mark.app.hjshop4a.uinew.bindinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class IDInfoParam extends BaseModel {
    String realName;//真实姓名

    String idCard;//身份证号

    String idCardFront;//身份证正面
    String idCardBack;//身份证反面

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
}

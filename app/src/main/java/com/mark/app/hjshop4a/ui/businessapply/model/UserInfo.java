package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class UserInfo extends BaseModel {
    private String userNick;//会员名称
    private String userRealName;//真实姓名
    private String cellphone;//联系方式
    private String email;//邮箱地址
    private String userIdCard;//身份证号
    private String userIdCardFront;//身份证正面
    private String userIdCardSide;//身份证反面

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserIdCardFront() {
        return userIdCardFront;
    }

    public void setUserIdCardFront(String userIdCardFront) {
        this.userIdCardFront = userIdCardFront;
    }

    public String getUserIdCardSide() {
        return userIdCardSide;
    }

    public void setUserIdCardSide(String userIdCardSide) {
        this.userIdCardSide = userIdCardSide;
    }
}

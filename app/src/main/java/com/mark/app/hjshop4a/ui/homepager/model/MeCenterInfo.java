package com.mark.app.hjshop4a.ui.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/24.
 */

public class MeCenterInfo extends BaseModel {
    private String cellPhone	;//电话
    private String integral;//积分
    private  String accountBlance;//账户余额
    private  String bean;//金豆
    private String userName;//用户名
    private String userPic;//用户头像
    private String shopName;//店铺名

    public String getMessageCount() {
        return cellPhone	;
    }

    public void setMessageCount(String messageCount) {
        this.cellPhone	 = messageCount;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getAccountBlance() {
        return accountBlance;
    }

    public void setAccountBlance(String accountBlance) {
        this.accountBlance = accountBlance;
    }

    public String getEvaDetailCount() {
        return bean;
    }

    public void setEvaDetailCount(String evaDetailCount) {
        this.bean = evaDetailCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}

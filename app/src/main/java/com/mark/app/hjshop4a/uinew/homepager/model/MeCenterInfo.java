package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/24.
 */

public class MeCenterInfo extends BaseModel {

    private String integral;//金币余额
    private String accountBlance;//账户余额

    private String userName;//用户名
    private String userPic;//用户头像



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

}

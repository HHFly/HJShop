package com.mark.app.hjshop4a.model.login;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;

/**
 * Created by pc on 2018/5/4.
 */

public class BusniessInfo extends BaseModel{
    private String HeadImg ;
    private String Number;
    private int  roleType;
    private String type;
    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
    public int getRoleType() {
        return RoleType.BUSINESS;
    }
    public String getType() {
        return "商家账号";
    }
}

package com.mark.app.hjshop4a.model.login.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.model.login.AreaAgentInfo;
import com.mark.app.hjshop4a.model.login.BusniessInfo;
import com.mark.app.hjshop4a.model.login.MemberInfo;
import com.mark.app.hjshop4a.model.login.ProvenceAgentInfo;

/**
 * Created by pc on 2018/4/16.
 */

public class LoginType  extends BaseModel{
   private String tpye;
    private String phone;
    private String HeadImg;
    private @RoleType int  roleType;
    public LoginType(BusniessInfo tpye) {
        this.tpye = tpye.getType();
        this.phone=tpye.getNumber();
        this.HeadImg =tpye.getHeadImg();
        this.roleType=tpye.getRoleType();
    }

    public LoginType(MemberInfo tpye) {
        this.tpye = tpye.getType();
        this.phone=tpye.getNumber();
        this.HeadImg =tpye.getHeadImg();
        this.roleType=tpye.getRoleType();
    }

    public LoginType(AreaAgentInfo tpye) {
        this.tpye = tpye.getType();
        this.phone=tpye.getNumber();
        this.HeadImg =tpye.getHeadImg();
        this.roleType=tpye.getRoleType();
    }
    public LoginType(ProvenceAgentInfo tpye) {
        this.tpye = tpye.getType();
        this.phone=tpye.getNumber();
        this.HeadImg =tpye.getHeadImg();
        this.roleType=tpye.getRoleType();
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public String getTpye() {
        return tpye;
    }

    public void setTpye(String tpye) {
        this.tpye = tpye;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}

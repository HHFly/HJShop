package com.mark.app.hjshop4a.model.login.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;

/**
 * Created by pc on 2018/4/16.
 */

public class LoginType  extends BaseModel{
   private String tpye;
    private String phone;

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    private @RoleType int  roleType;

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

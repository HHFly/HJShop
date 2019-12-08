package com.mark.app.hjshop4a.uinew.invitation.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class InvitePage extends BaseModel {
    int totalCount; //总计邀请注册人数
    int completeOrderCount;//完成订单人数
    String inviteUrl;//邀请地址
    double totalMoney;//总计获得赏金


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCompleteOrderCount() {
        return completeOrderCount;
    }

    public void setCompleteOrderCount(int completeOrderCount) {
        this.completeOrderCount = completeOrderCount;
    }

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}

package com.mark.app.hjshop4a.uinew.invitation.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class InviteBountyRecord extends BaseModel {
    String activityOrderSn;
    String showTime;
    double bounty;

    public String getActivityOrderSn() {
        return activityOrderSn;
    }

    public void setActivityOrderSn(String activityOrderSn) {
        this.activityOrderSn = activityOrderSn;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }
}

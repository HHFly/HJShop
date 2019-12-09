package com.mark.app.hjshop4a.uinew.integral.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class IntegralChange extends BaseModel {
    int type;//1增加 2减少
    int integral;//变更积分
    String reason;//变更原因
    long createTime;// 创建时间

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}

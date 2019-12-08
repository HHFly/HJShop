package com.mark.app.hjshop4a.uinew.invitation.param;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class InviteParam extends BaseModel {
    int pageNo;
    int pageSize= 10;
    long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

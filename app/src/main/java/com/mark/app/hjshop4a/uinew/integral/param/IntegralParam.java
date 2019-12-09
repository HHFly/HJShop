package com.mark.app.hjshop4a.uinew.integral.param;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class IntegralParam extends BaseModel {
    int pageNo;
    int pageSize =10;

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

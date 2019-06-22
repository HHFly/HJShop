package com.mark.app.hjshop4a.uinew.orderList;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class PageParam extends BaseModel {
    int orderStatus;
    int pageNo;
    int pageSize =10;

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

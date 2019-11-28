package com.mark.app.hjshop4a.uinew.bill.param;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class BillPageParam extends BaseModel {
    int pageNo;// 页码 是
    int pageSize=10;//条数 shi
    long startTime;//开始时间 fou
    String orderSn;//订单号 fou

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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
}

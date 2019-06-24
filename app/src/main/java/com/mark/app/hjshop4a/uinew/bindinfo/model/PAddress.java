package com.mark.app.hjshop4a.uinew.bindinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class PAddress extends BaseModel {
    long provinceId;
    String provinceName;
    private boolean status =false;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}

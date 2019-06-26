package com.mark.app.hjshop4a.uinew.bindinfo.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class AAddress extends BaseModel {
    String cityId;
    String cityName;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

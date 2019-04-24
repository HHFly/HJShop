package com.mark.app.hjshop4a.common.utils.location.listener;


import com.mark.app.hjshop4a.common.utils.location.model.LocationModel;

public abstract class DefLocationCallback implements LocationCallback  {
    boolean isLocationOne;

    public DefLocationCallback() {
        this(true);
    }

    public DefLocationCallback(boolean isLocationOne) {
        this.isLocationOne = isLocationOne;
    }

    public void getLastKnownLocation(LocationModel data) {
    }

    public boolean isLocationOne() {
        return this.isLocationOne;
    }
}

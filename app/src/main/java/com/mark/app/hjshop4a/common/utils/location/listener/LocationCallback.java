package com.mark.app.hjshop4a.common.utils.location.listener;


import com.mark.app.hjshop4a.common.utils.location.model.LocationModel;

public interface LocationCallback {
    void getLastKnownLocation(LocationModel var1);

    void onLocationChanged(LocationModel var1);

    boolean isLocationOne();
}

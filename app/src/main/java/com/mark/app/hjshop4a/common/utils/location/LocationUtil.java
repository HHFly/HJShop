package com.mark.app.hjshop4a.common.utils.location;

import android.app.Activity;
import android.support.annotation.NonNull;


import com.mark.app.hjshop4a.common.utils.location.listener.LocationCallback;
import com.mark.app.hjshop4a.common.utils.location.model.LocationModel;

import java.util.List;

public interface LocationUtil extends ILocationHandler {
    String TAG = "LocationUtil";
    String[] ARRAY_PERMISSION = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    List<LocationCallback> getLocationCallbackArray();

    void setNewestLocation(LocationModel var1);

    void onRequestPermissionsResult(Activity var1, int var2, @NonNull String[] var3, @NonNull int[] var4);
}

package com.mark.app.hjshop4a.ui.dialog.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2018/5/9.
 */

public class WheelData {
    private Map city;
    private Map district;
    private ArrayList province;

    public Map getCity() {
        return city;
    }

    public void setCity(Map city) {
        this.city = city;
    }

    public Map getDistrict() {
        return district;
    }

    public void setDistrict(Map district) {
        this.district = district;
    }

    public ArrayList getProvince() {
        return province;
    }

    public void setProvince(ArrayList province) {
        this.province = province;
    }
}

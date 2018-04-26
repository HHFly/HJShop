package com.mark.app.hjshop4a.ui.onlinerecharge.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/26.
 */

public class PayWayList extends BaseModel {
    private ArrayList<PayWay> payWayList;

    public ArrayList<PayWay> getPayWayList() {
        return payWayList;
    }

    public void setPayWayList(ArrayList<PayWay> payWayList) {
        this.payWayList = payWayList;
    }
}

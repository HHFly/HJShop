package com.mark.app.hjshop4a.ui.business.billrecord.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/20.
 */

public class BillsRecord extends BaseModel {
    private ArrayList<BillRecord> billRecords;

    public ArrayList<BillRecord> getBillRecords() {
        return billRecords;
    }

    public void setBillRecords(ArrayList<BillRecord> billRecords) {
        this.billRecords = billRecords;
    }
}

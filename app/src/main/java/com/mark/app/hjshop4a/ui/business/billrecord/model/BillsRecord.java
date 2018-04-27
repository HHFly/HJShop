package com.mark.app.hjshop4a.ui.business.billrecord.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/20.
 */

public class BillsRecord extends BaseModel {
    private List<Customs> customsList;//报单列表
    private int customsTotal;//总报单
    private  int customsYestoday;//昨日报单数量


    public List<Customs> getCustomsList() {
        return customsList;
    }

    public void setCustomsList(List<Customs> customsList) {
        this.customsList = customsList;
    }

    public int getCustomsTotal() {
        return customsTotal;
    }

    public void setCustomsTotal(int customsTotal) {
        this.customsTotal = customsTotal;
    }

    public int getCustomsYestoday() {
        return customsYestoday;
    }

    public void setCustomsYestoday(int customsYestoday) {
        this.customsYestoday = customsYestoday;
    }
}

package com.mark.app.hjshop4a.ui.business.billrecord.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/20.
 */

public class BillsRecord extends BaseModel {
    private List<Customs> customsList;//报单列表
    private String customsTotal;//总报单
    private  String customsYestoday;//昨日报单数量

    public void setCustomsTotal(String customsTotal) {
        this.customsTotal = customsTotal;
    }

    public void setCustomsYestoday(String customsYestoday) {
        this.customsYestoday = customsYestoday;
    }

    public List<Customs> getCustomsList() {
        return customsList;
    }

    public void setCustomsList(List<Customs> customsList) {
        this.customsList = customsList;
    }

    public String getCustomsTotal() {
        return String.valueOf(customsTotal);
    }



    public String getCustomsYestoday() {
        return String.valueOf(customsYestoday);
    }


}

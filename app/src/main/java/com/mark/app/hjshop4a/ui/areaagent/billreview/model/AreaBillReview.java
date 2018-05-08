package com.mark.app.hjshop4a.ui.areaagent.billreview.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBillReview extends BaseModel {
    private String customsTotal;//总报单
    private  String customsYestoday;//昨日报单数量
    private List<Customs> customsList; //	报单列表

    public String getCustomsTotal() {
        return customsTotal;
    }

    public void setCustomsTotal(String customsTotal) {
        this.customsTotal = customsTotal;
    }

    public String getCustomsYestoday() {
        return customsYestoday;
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
}

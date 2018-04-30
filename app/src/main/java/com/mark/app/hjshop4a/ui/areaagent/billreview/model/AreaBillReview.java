package com.mark.app.hjshop4a.ui.areaagent.billreview.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBillReview extends BaseModel {
    private int customsTotal;//总报单
    private  int customsYestoday;//昨日报单数量
    private List<Customs> customsList; //	报单列表

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

    public List<Customs> getCustomsList() {
        return customsList;
    }

    public void setCustomsList(List<Customs> customsList) {
        this.customsList = customsList;
    }
}

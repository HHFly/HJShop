package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/26.
 */

public class TopUpList extends BaseModel {
    private List<TopUp> topUpList;

    public List<TopUp> getTopUpList() {
        return topUpList;
    }

    public void setTopUpList(List<TopUp> topUpList) {
        this.topUpList = topUpList;
    }
}

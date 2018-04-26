package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/26.
 */

public class BalanceWithDrawList extends BaseModel {
    private List<BalanceWithDraw>balanceWithDrawList;

    public List<BalanceWithDraw> getBalanceWithDrawList() {
        return balanceWithDrawList;
    }

    public void setBalanceWithDrawList(List<BalanceWithDraw> balanceWithDrawList) {
        this.balanceWithDrawList = balanceWithDrawList;
    }
}

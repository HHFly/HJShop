package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/26.
 */

public class BalanceList extends BaseModel {
    private List<Balance> balanceList;

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }
}

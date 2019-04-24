package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/25.
 */

public class ConsumptionBills extends BaseModel {
    private List<Bean> beanList;
    private List<BalanceWithDraw> balanceWithDrawList;
    private  List<Balance> balanceList;
     private List<TopUp> topUpList;
     private List<BeanTradeIn> beanTradeInList;

    public List<Bean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<Bean> beanList) {
        this.beanList = beanList;
    }

    public List<BalanceWithDraw> getBalanceWithDrawList() {
        return balanceWithDrawList;
    }

    public void setBalanceWithDrawList(List<BalanceWithDraw> balanceWithDrawList) {
        this.balanceWithDrawList = balanceWithDrawList;
    }

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }

    public List<TopUp> getTopUpList() {
        return topUpList;
    }

    public void setTopUpList(List<TopUp> topUpList) {
        this.topUpList = topUpList;
    }

    public List<BeanTradeIn> getBeanTradeInList() {
        return beanTradeInList;
    }

    public void setBeanTradeInList(List<BeanTradeIn> beanTradeInList) {
        this.beanTradeInList = beanTradeInList;
    }
}

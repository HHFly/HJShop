package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/26.
 */

public class BeanTradeInList extends BaseModel {
    private List<BeanTradeIn> beanTradeInList;

    public List<BeanTradeIn> getBeanTradeInList() {
        return beanTradeInList;
    }

    public void setBeanTradeInList(List<BeanTradeIn> beanTradeInList) {
        this.beanTradeInList = beanTradeInList;
    }
}

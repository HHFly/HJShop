package com.mark.app.hjshop4a.ui.consumptionbill.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by pc on 2018/5/8.
 */

public class MemberTradeInList extends BaseModel {
    private ArrayList<MemberGoldBeanTradeIn> userBeanTradeInList;

    public ArrayList<MemberGoldBeanTradeIn> getMemberGoldBeanTradeIns() {
        return userBeanTradeInList;
    }

    public void setMemberGoldBeanTradeIns(ArrayList<MemberGoldBeanTradeIn> memberGoldBeanTradeIns) {
        this.userBeanTradeInList = memberGoldBeanTradeIns;
    }
}

package com.mark.app.hjshop4a.ui.areaagent.areaincome.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaIncome extends BaseModel {
    private String totalassets; //总资产
    private String incomeYestoday;//昨日收益
    private String balanceUsable;//可用余额
    private String withdrawFreeze;//提现冻结
    private String areaIncome;//区域业绩收益
    private String inviteIncome;//同级推荐收益
    private String addUpIncome;//累计收益（截止前一天计算）
    private List<Income> incomeList;//	收益数据


    public String getTotalassets() {
        return totalassets;
    }

    public void setTotalassets(String totalassets) {
        this.totalassets = totalassets;
    }

    public String getIncomeYestoday() {
        return incomeYestoday;
    }

    public void setIncomeYestoday(String incomeYestoday) {
        this.incomeYestoday = incomeYestoday;
    }

    public String getBalanceUsable() {
        return balanceUsable;
    }

    public void setBalanceUsable(String balanceUsable) {
        this.balanceUsable = balanceUsable;
    }

    public String getWithdrawFreeze() {
        return withdrawFreeze;
    }

    public void setWithdrawFreeze(String withdrawFreeze) {
        this.withdrawFreeze = withdrawFreeze;
    }

    public String getAreaIncome() {
        return areaIncome;
    }

    public void setAreaIncome(String areaIncome) {
        this.areaIncome = areaIncome;
    }

    public String getInviteIncome() {
        return inviteIncome;
    }

    public void setInviteIncome(String inviteIncome) {
        this.inviteIncome = inviteIncome;
    }

    public String getAddUpIncome() {
        return addUpIncome;
    }

    public void setAddUpIncome(String addUpIncome) {
        this.addUpIncome = addUpIncome;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}

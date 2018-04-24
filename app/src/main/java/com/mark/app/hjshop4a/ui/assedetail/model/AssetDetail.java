package com.mark.app.hjshop4a.ui.assedetail.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/24.
 */

public class AssetDetail extends BaseModel {
    private String accountBalance;//账户余额
    private String accountBalanceUsable;//	可用余额
    private String accountBalanceFreeze;//冻结余额
    private String invite;//	同级推荐收益
    private String beanUsable;//	账户可用金豆
    private String beanStayAway;//	待赠送金豆
    private String beanHasPresented;//	已赠送金豆
    private String treeHad;//已有摇钱树
    private String treeIncomeDay;//摇钱树每日收益
    private String customsTotal;//报单总收入

    private String customsYestoday;//报单昨日收入

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountBalanceUsable() {
        return accountBalanceUsable;
    }

    public void setAccountBalanceUsable(String accountBalanceUsable) {
        this.accountBalanceUsable = accountBalanceUsable;
    }

    public String getAccountBalanceFreeze() {
        return accountBalanceFreeze;
    }

    public void setAccountBalanceFreeze(String accountBalanceFreeze) {
        this.accountBalanceFreeze = accountBalanceFreeze;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getBeanUsable() {
        return beanUsable;
    }

    public void setBeanUsable(String beanUsable) {
        this.beanUsable = beanUsable;
    }

    public String getBeanStayAway() {
        return beanStayAway;
    }

    public void setBeanStayAway(String beanStayAway) {
        this.beanStayAway = beanStayAway;
    }

    public String getBeanHasPresented() {
        return beanHasPresented;
    }

    public void setBeanHasPresented(String beanHasPresented) {
        this.beanHasPresented = beanHasPresented;
    }

    public String getTreeHad() {
        return treeHad;
    }

    public void setTreeHad(String treeHad) {
        this.treeHad = treeHad;
    }

    public String getTreeIncomeDay() {
        return treeIncomeDay;
    }

    public void setTreeIncomeDay(String treeIncomeDay) {
        this.treeIncomeDay = treeIncomeDay;
    }

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
}

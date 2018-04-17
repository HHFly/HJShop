package com.mark.app.hjshop4a.model.consumptionbill;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/17.
 */

public class Bill {
    ArrayList<BalanceRepo> balanceRepos;
    ArrayList<GoldBeanRepo> goldBeanRepos;
    ArrayList<RechargeRepo> rechargeRepos;

    public ArrayList<BalanceRepo> getBalanceRepos() {
        return balanceRepos;
    }

    public void setBalanceRepos(ArrayList<BalanceRepo> balanceRepos) {
        this.balanceRepos = balanceRepos;
    }

    public ArrayList<GoldBeanRepo> getGoldBeanRepos() {
        return goldBeanRepos;
    }

    public void setGoldBeanRepos(ArrayList<GoldBeanRepo> goldBeanRepos) {
        this.goldBeanRepos = goldBeanRepos;
    }

    public ArrayList<RechargeRepo> getRechargeRepos() {
        return rechargeRepos;
    }

    public void setRechargeRepos(ArrayList<RechargeRepo> rechargeRepos) {
        this.rechargeRepos = rechargeRepos;
    }
}

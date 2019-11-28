package com.mark.app.hjshop4a.uinew.withdraw.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/5/3.
 */

public class WithdrawPage extends BaseModel {
    String balance;//货款
    String gold;//金币
    int bindBankFlage;//是否绑定银行卡 0否 1是
    String accountName;//开户人
    String accountNumber;//卡号
    double scale;//提现手续费

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public int getBindBankFlage() {
        return bindBankFlage;
    }

    public void setBindBankFlage(int bindBankFlage) {
        this.bindBankFlage = bindBankFlage;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}

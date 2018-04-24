package com.mark.app.hjshop4a.ui.bankcard.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/24.
 */

public class BankCards extends BaseModel {
    ArrayList<BankCard> bankCards;

    public ArrayList<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(ArrayList<BankCard> bankCards) {
        this.bankCards = bankCards;
    }
}

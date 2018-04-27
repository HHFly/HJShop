package com.mark.app.hjshop4a.ui.bankcard.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCard extends BaseModel {
   private String bankPic;
   private String bankName;
   private String bankType;
   private String bankNo;
    private boolean isSelect =false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getBankPic() {
        return bankPic;
    }

    public void setBankPic(String bankPic) {
        this.bankPic = bankPic;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
}

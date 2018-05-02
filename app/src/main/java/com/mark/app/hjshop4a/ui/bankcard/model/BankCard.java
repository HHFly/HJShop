package com.mark.app.hjshop4a.ui.bankcard.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/16.
 */

public class BankCard extends BaseModel {
    private long bankId;//银行卡ID
   private String bankPic;//银行卡图标
   private String bankName;//银行卡名称
   private String bankType;//银行卡类型 0 普通 1 信用卡
   private String bankNo;//银行卡号
    private boolean isSelect =false;

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

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

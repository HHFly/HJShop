package com.mark.app.hjshop4a.ui.bankcard.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/5/2.
 */

public class BankCategory extends BaseModel {
    private  long bankCategoryId;
    private String bankCategoryName;

    public long getBankCategoryId() {
        return bankCategoryId;
    }

    public void setBankCategoryId(long bankCategoryId) {
        this.bankCategoryId = bankCategoryId;
    }

    public String getBankCategoryName() {
        return bankCategoryName;
    }

    public void setBankCategoryName(String bankCategoryName) {
        this.bankCategoryName = bankCategoryName;
    }
}

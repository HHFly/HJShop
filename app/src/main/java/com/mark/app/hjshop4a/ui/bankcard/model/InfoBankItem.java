package com.mark.app.hjshop4a.ui.bankcard.model;


import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * 银行列表
 * Created by lenovo on 2017/10/7.
 */

public class InfoBankItem extends BaseModel {
    /**
     * 银行Id
     */
    private long bankTypeId;
    /**
     * 银行名
     */
    private String bankName;

    public long getBankId() {
        return bankTypeId;
    }

    public void setBankId(long bankId) {
        this.bankTypeId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

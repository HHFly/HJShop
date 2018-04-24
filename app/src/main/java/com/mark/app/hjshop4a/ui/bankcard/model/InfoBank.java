package com.mark.app.hjshop4a.ui.bankcard.model;


import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * 开户行设置数据
 * Created by lenovo on 2017/10/7.
 */

public class InfoBank extends BaseModel {
    /**
     * 银行id
     */
    private long bankId;
    /**
     * 开户银行
     */
    private List<InfoBankItem> bankTypes;
    /**
     * 开户人
     */
    private String accountHolder;
    /**
     * 开户账号
     */
    private String bankNo;
    /**
     * 开户行类型id
     */
    private long bankTypeId;


    /**
     * 新增
     */
    private String accountNumber;
    /**
     * 新增
     */
    private String sortCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public long getBankTypeId() {
        return bankTypeId;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public List<InfoBankItem> getBankTypes() {
        return bankTypes;
    }

    public void setBankTypes(List<InfoBankItem> bankTypes) {
        this.bankTypes = bankTypes;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankNo() {
        if ("0".equals(bankNo)) {
            return "";
        }
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
}

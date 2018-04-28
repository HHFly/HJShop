package com.mark.app.hjshop4a.ui.areaagent.areabusniess.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBusniess extends BaseModel {
    private String turnOverTotal; //辖区内总营业额
    private String turnOverMonth;   //辖区内(时间范围)营业额
    private List<ProxyData> proxyDataList; // 辖区代理商数据
    private List<MerchantData>  merchantDataList; //商家数据

    public String getTurnOverTotal() {
        return turnOverTotal;
    }

    public void setTurnOverTotal(String turnOverTotal) {
        this.turnOverTotal = turnOverTotal;
    }

    public String getTurnOverMonth() {
        return turnOverMonth;
    }

    public void setTurnOverMonth(String turnOverMonth) {
        this.turnOverMonth = turnOverMonth;
    }

    public List<ProxyData> getProxyDataList() {
        return proxyDataList;
    }

    public void setProxyDataList(List<ProxyData> proxyDataList) {
        this.proxyDataList = proxyDataList;
    }

    public List<MerchantData> getMerchantDataList() {
        return merchantDataList;
    }

    public void setMerchantDataList(List<MerchantData> merchantDataList) {
        this.merchantDataList = merchantDataList;
    }
}

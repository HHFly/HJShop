package com.mark.app.hjshop4a.ui.areaagent.agentperformance.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by hui on 2018/4/29.
 */

public class PerformanceProvinces extends BaseModel {
    private  String cityName;//城市
    private long cityId; // 城市ID
    private String newUserDay; //日新增会员数
    private String userTotal; //会员总数
    private String customsDay;//日报单金额
    private String beanIncomeDay;//日金豆收入
    private String customsMonth;//月报单金额
    private String beanIncomeMonth;// 月金豆收入
    private String turnOverMonth;//月营业额
    private String turnOverTotal;//总营业额

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getNewUserDay() {
        return newUserDay;
    }

    public void setNewUserDay(String newUserDay) {
        this.newUserDay = newUserDay;
    }

    public String getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(String userTotal) {
        this.userTotal = userTotal;
    }

    public String getCustomsDay() {
        return customsDay;
    }

    public void setCustomsDay(String customsDay) {
        this.customsDay = customsDay;
    }

    public String getBeanIncomeDay() {
        return beanIncomeDay;
    }

    public void setBeanIncomeDay(String beanIncomeDay) {
        this.beanIncomeDay = beanIncomeDay;
    }

    public String getCustomsMonth() {
        return customsMonth;
    }

    public void setCustomsMonth(String customsMonth) {
        this.customsMonth = customsMonth;
    }

    public String getBeanIncomeMonth() {
        return beanIncomeMonth;
    }

    public void setBeanIncomeMonth(String beanIncomeMonth) {
        this.beanIncomeMonth = beanIncomeMonth;
    }

    public String getTurnOverMonth() {
        return turnOverMonth;
    }

    public void setTurnOverMonth(String turnOverMonth) {
        this.turnOverMonth = turnOverMonth;
    }

    public String getTurnOverTotal() {
        return turnOverTotal;
    }

    public void setTurnOverTotal(String turnOverTotal) {
        this.turnOverTotal = turnOverTotal;
    }
}

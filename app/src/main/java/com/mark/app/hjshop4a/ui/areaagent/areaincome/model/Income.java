package com.mark.app.hjshop4a.ui.areaagent.areaincome.model;


import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;

/**
 * 收入统计-数据报表Item
 * Created by lenovo on 2017/10/11.
 */

public class Income extends BaseModel {

    private String income	;//收益金额

    private String month;//时间月份

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

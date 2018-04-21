package com.mark.app.hjshop4a.ui.areaagent.areaincome;


import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;

/**
 * 收入统计-数据报表Item
 * Created by lenovo on 2017/10/11.
 */

public class OrderItemBean extends BaseModel {
    /**
     * 天
     */
    private String day;
    /**
     * 收入
     */
    private String number;

    public void setDay(String day) {
        this.day = day;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDay() {
        return day;
    }


    public float getNumber() {
        return NumParseUtils.parseFloat(number);
    }

}

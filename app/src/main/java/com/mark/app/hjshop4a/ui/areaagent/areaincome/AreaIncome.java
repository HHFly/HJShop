package com.mark.app.hjshop4a.ui.areaagent.areaincome;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaIncome extends BaseModel {
    /**
     * 今日收入
     */
    private String orderToday;
    /**
     * 本周收入
     */
    private String orderWeek;
    /**
     * 本月收入
     */
    private String orderMonth;
    /**
     * 累计收入
     */
    private String orderTotal;
    /**
     * 周收入
     */
    private List<OrderItemBean> orderWeekList;
    /**
     * 月收入
     */
    private List<OrderItemBean> orderMonthList;

    public List<OrderItemBean> getOrderWeekList() {
        return orderWeekList;
    }

    public void setOrderWeekList(List<OrderItemBean> orderWeekList) {
        this.orderWeekList = orderWeekList;
    }

    public List<OrderItemBean> getOrderMonthList() {
        return orderMonthList;
    }

    public void setOrderMonthList(List<OrderItemBean> orderMonthList) {
        this.orderMonthList = orderMonthList;
    }

    public String getOrderToday() {
        return orderToday;
    }

    public void setOrderToday(String orderToday) {
        this.orderToday = orderToday;
    }

    public String getOrderWeek() {
        return orderWeek;
    }

    public void setOrderWeek(String orderWeek) {
        this.orderWeek = orderWeek;
    }

    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }
}

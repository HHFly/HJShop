package com.mark.app.hjshop4a.ui.areaagent.agentperformance.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by hui on 2018/4/29.
 */

public class PerformanceProvince  extends BaseModel{
    private  PerformanceProvinceDay performanceProvinceDay;
    private PerformanceProvinceMonth performanceProvinceMonth;
    private PerformanceProvinceTotal performanceProvinceTotal;

    public PerformanceProvinceDay getPerformanceProvinceDay() {
        return performanceProvinceDay;
    }

    public void setPerformanceProvinceDay(PerformanceProvinceDay performanceProvinceDay) {
        this.performanceProvinceDay = performanceProvinceDay;
    }

    public PerformanceProvinceMonth getPerformanceProvinceMonth() {
        return performanceProvinceMonth;
    }

    public void setPerformanceProvinceMonth(PerformanceProvinceMonth performanceProvinceMonth) {
        this.performanceProvinceMonth = performanceProvinceMonth;
    }

    public PerformanceProvinceTotal getPerformanceProvinceTotal() {
        return performanceProvinceTotal;
    }

    public void setPerformanceProvinceTotal(PerformanceProvinceTotal performanceProvinceTotal) {
        this.performanceProvinceTotal = performanceProvinceTotal;
    }
}

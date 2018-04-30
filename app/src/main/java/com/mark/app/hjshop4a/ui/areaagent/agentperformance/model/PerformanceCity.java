package com.mark.app.hjshop4a.ui.areaagent.agentperformance.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by hui on 2018/4/29.
 */

public class PerformanceCity extends BaseModel {
    private PerformanceCityDay performanceCityDay;
    private  PerformanceCityMonth performanceCityMonth;
    private PerformanceCityTotal performanceCityTotal;

    public PerformanceCityDay getPerformanceCityDay() {
        return performanceCityDay;
    }

    public void setPerformanceCityDay(PerformanceCityDay performanceCityDay) {
        this.performanceCityDay = performanceCityDay;
    }

    public PerformanceCityMonth getPerformanceCityMonth() {
        return performanceCityMonth;
    }

    public void setPerformanceCityMonth(PerformanceCityMonth performanceCityMonth) {
        this.performanceCityMonth = performanceCityMonth;
    }

    public PerformanceCityTotal getPerformanceCityTotal() {
        return performanceCityTotal;
    }

    public void setPerformanceCityTotal(PerformanceCityTotal performanceCityTotal) {
        this.performanceCityTotal = performanceCityTotal;
    }
}


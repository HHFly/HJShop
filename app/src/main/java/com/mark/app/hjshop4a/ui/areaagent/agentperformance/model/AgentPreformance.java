package com.mark.app.hjshop4a.ui.areaagent.agentperformance.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AgentPreformance extends BaseModel {
    private PerformanceProvince performanceProvince;//代理商业绩汇总(省)
    private PerformanceCity performanceCity;//代理商业绩汇总（市）
    private List<PerformanceProvinces>  performanceProvinceList;//辖区内代理商业绩(省)
    private List<PerformanceCitys> performanceCityList;//辖区内代理商商户数据（市）


    public PerformanceProvince getPerformanceProvince() {
        return performanceProvince;
    }

    public void setPerformanceProvince(PerformanceProvince performanceProvince) {
        this.performanceProvince = performanceProvince;
    }

    public PerformanceCity getPerformanceCity() {
        return performanceCity;
    }

    public void setPerformanceCity(PerformanceCity performanceCity) {
        this.performanceCity = performanceCity;
    }

    public List<PerformanceProvinces> getPerformanceProvinceList() {
        return performanceProvinceList;
    }

    public void setPerformanceProvinceList(List<PerformanceProvinces> performanceProvinceList) {
        this.performanceProvinceList = performanceProvinceList;
    }

    public List<PerformanceCitys> getPerformanceCityList() {
        return performanceCityList;
    }

    public void setPerformanceCityList(List<PerformanceCitys> performanceCityList) {
        this.performanceCityList = performanceCityList;
    }
}

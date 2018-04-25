package com.mark.app.hjshop4a.ui.business.goldbeanconsume.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/24.
 */

public class BusniessGoldBeanCS extends BaseModel {
    private String beanUsable;
    private  String disCounts;
    private String captCha;

    public String getBeanUsable() {
        return beanUsable;
    }

    public void setBeanUsable(String beanUsable) {
        this.beanUsable = beanUsable;
    }

    public String getDisCounts() {
        return disCounts;
    }

    public void setDisCounts(String disCounts) {
        this.disCounts = disCounts;
    }

    public String getCaptCha() {
        return captCha;
    }

    public void setCaptCha(String captCha) {
        this.captCha = captCha;
    }
}

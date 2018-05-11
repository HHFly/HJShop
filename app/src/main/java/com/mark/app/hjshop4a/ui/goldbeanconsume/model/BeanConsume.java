package com.mark.app.hjshop4a.ui.goldbeanconsume.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/24.
 */

public class BeanConsume extends BaseModel {
    private  String  beanNum;
    private  String beanRatio;
    private String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getBeanNum() {
        return beanNum;
    }

    public void setBeanNum(String beanNum) {
        this.beanNum = beanNum;
    }

    public String getBeanRatio() {
        return beanRatio;
    }

    public void setBeanRatio(String beanRatio) {
        this.beanRatio = beanRatio;
    }
}

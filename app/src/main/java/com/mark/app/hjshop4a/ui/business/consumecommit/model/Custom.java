package com.mark.app.hjshop4a.ui.business.consumecommit.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/27.
 */

public class Custom extends BaseModel {

    private String captcha;
    private List<Model> modelList;



    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }
}

package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

public class EvaluationInfo extends BaseModel {
    int evaluationType;//评论类型 1五星好评 2文字好评 3图文好评
    String evaluationContext;//评论内容

    List<String> evaluationPics;//商家提供评价图片（数量小于等于0）

    public int getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(int evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getEvaluationContext() {
        return evaluationContext;
    }

    public void setEvaluationContext(String evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

    public List<String> getEvaluationPics() {
        return evaluationPics;
    }

    public void setEvaluationPics(List<String> evaluationPics) {
        this.evaluationPics = evaluationPics;
    }
}

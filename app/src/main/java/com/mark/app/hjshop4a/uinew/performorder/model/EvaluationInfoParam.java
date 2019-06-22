package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class EvaluationInfoParam extends BaseModel {
    String subOrderSn;
    String evaluationPic;//评价图片

    public String getSubOrderSn() {
        return subOrderSn;
    }

    public void setSubOrderSn(String subOrderSn) {
        this.subOrderSn = subOrderSn;
    }

    public String getEvaluationPic() {
        return evaluationPic;
    }

    public void setEvaluationPic(String evaluationPic) {
        this.evaluationPic = evaluationPic;
    }
}
